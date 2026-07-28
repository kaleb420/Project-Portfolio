/*
 *  This file contains the code that is executed when the
 *  system boots.  The .text.entry function '_boothdr' allows
 *  this code - when compiled - to be loaded by a uefi compatible
 *  bootloader.
 *
 *  Once loaded, the '_boothdr' calls the OS boot function:
 *  '_start', which represents the point where the OS takes
 *  control.
 */

	.file "bootstrap.s" /* Filename for internal documentation            */
	.option norelax     /* Don't automatically use gp in this file        */
	.option norvc       /* Don't use compressed instructions in this file */

.section .rodata
clint_timer_addr: 	.dword 0x2004000   # -.
clint_timer_base: 	.dword 0x200bff8   #  |  Setup Hardware Clock (base addr, threshold addr, interval)
timer_interval:		.dword 100000      # -'

.section .text.entry
.globl _boothdr
_boothdr:
        j 	_start                      # Ex 0              /*                                    */
        nop                                 # Ex 1              /*  This block  of data  is expected  */
        .dword 	0x0                         # text_offset       /*  by a bootloader to  identify the  */
        .dword 	ld_bss_end - ld_text_start  # image_size        /*  following  data  as  a  loadable  */
        .dword 	0x0                         # Flags             /*  Operating System.                 */
        .word  	0x00000002                  # Version           /*                                    */
        .word  	0x0                         # -Reserved-        /*                                    */
        .dword 	0x0                         # -Reserved-        /*  The first two lines are executed  */
        .dword 	0x5643534952                # Magic 0           /*  by the bootloader once the block  */
        .word  	0x05435352                  # Magic 1           /*  is identified as an OS.           */
        .word  	0x0                         # -Reserved-        /*                                    */

/*  This  function setups up the  machine-mode  only registers to sane,  *
*  initial values before handing control to the OS in supervisor mode.  */
_start:
	csrr 	t0, mhartid                   # -.
	slli 	t0, t0, 10                    #  |
	la   	t1, ld_estack_top             #  |  Run the kernel setup only on hart 0.
	add  	t1, t0, t1                    #  |  Set the exception stack based on the hart id.
	csrw 	mscratch, t1                  #  |
	addi 	t1, t1, -128                  #  |  Set the supervisor stack to be 128 bytes above
	csrw 	sscratch, t1                  #  |  the machine stack
	bnez 	t0, await_smp                 # -'  Send all other harts to an idle loop

	la 	t0, 0x880                     # -.
	csrw 	mstatus, t0                   #  |  Set mstatus.MPP == Supervisor and mstatus.MPIE == On
	li 	t0, 0x100                     #  |  
	csrs 	medeleg, t0                   #  |  Set ECALLs from User mode to run in Supervisor mode
	li 	t0, 0x222                     #  |
	csrs 	mideleg, t0                   # -'  Setup Supervisor interrupts to run in Supervisor mode

	la 	t0, machine_traps             # -.  Setup remaining machine interrupt and exception handler
	csrw 	mtvec, t0                     # -'  to the `machine_traps` function

	li 	t0, 0x0f                      # -.  Set memory protection to allow RWX on all addressed
	li 	t1, 0x22000000                #  |  from 0x0 to 0x88000000
	csrw	pmpcfg0, t0                   #  |  pmpaddr is naturally shifted by 2, so value is <addr>/4
	csrw	pmpaddr0, t1                  # -'

	la 	gp, __global_pointer$         # -.  Set the gp to an address generated at compile time
	csrr 	sp, mscratch                  #  |  Set initial sp to a small stack reserved for kernel use
	addi 	sp, sp, -128                  #  |  Offset the sp to ensure room exists for timer interrupts
	la 	t0, supervisor_start          #  |  Setup the initial kernel start point in Supervisor
	csrw 	mepc, t0                      # -'  mode to jump to `supervisor_start`
	
	la 	t1, clint_timer_base          # -.
	la 	t0, clint_timer_addr          #  |  Setup  the Machine  mode  timer  to trigger  every 10ms
	ld 	t1, (t1)                      #  |  starting as soon as the  system  enters Supervisor mode
 	ld 	t0, (t0)                      #  |  This  initializes the timer to the current  cycle count
	ld	t1, (t1)                      #  |  to avoid extra interrupts.
	sw 	t1, (t0)                      # -'

	li 	t0, 0x80                      # -.  Enable Machine timer interrupts
	csrs 	mie, t0                       # -'
	mret                                  # --  Return control to Supervisor mode (`supervisor_start`)


/*  This function acts as  the initial landing  point for when the  system  *
 *  enters the "user" privilage mode.  When invoked, it implies the system  *
 *  is ready to begin executing user code and applications.                 *
 *                                                                          *
 * @arg0: A pointer to the function that should be started in user mode     *
 * @return: -no return-                                                     */
.globl user_start
user_start:
	la 	t0, ld_mem_end                # -.
	addi	t0, t0, -0x8                  #  |  Set the kernel stack pointer to the thread 0 stack top
	csrw	sscratch, t0                  # -'
	li 	t1, -0x1000                   # -.
	add 	sp, t0, t1                    # -'  Set the user stack pointer to the thread 0 stack top plus 4K
	mv 	tp, zero                      # --  Set the initial thread id to 0
	csrw 	sepc, a0                      # --  Set the return point to the passed in address
	sret                                  # --  Jump to the address at sepc in "user mode"

/*  This function blocks forever.  In a multi-hart OS, this would be replaced  *
 *  with a function  that waits for a signal from hart0 indicating  it should  *
 *  select a thread from the scheduler and begin running.                      */
await_smp:
	wfi                                   # --  Block until an interrupt occurs
	j	await_smp                     # --  loop


/*  This function checks if the  cause of the most  recent exception was a ecall  *
 *  and if so, clears the interrupt pending bit for the supervisor timer.   This  *
 *  allows the supervisor to request a timer reset by using an ecall instruction  */
.globl reset_timer
reset_timer:
	li	a0, 0x20                      # -.  Clear the supervisor timer pending interrupt bit
	csrc	mip, a0                       # -'
	csrr	a0, mcause                    # -.
	li	t0, 0x9                       #  |  Check if the current exception was an ecall
	beq	a0, t0, .L_no_timer_reset     # .-
	mv	a0, zero                      # --  If so, return the exception, if not return 0
.L_no_timer_reset:
	ret

/*  This function captures exceptional behavior that is not handled by the kernel  *
 *  If the exception was caused by a fault, the system invokes a kernel panic and  *
 *  dies.  Otherwise, it triggers the regular machine timer handler.               */
	.align 2
machine_traps:
	csrrw 	sp, mscratch, sp
	sd 	a0, -0x8(sp)                 # --  Store the current value of t0 below the current stack
	csrr 	a0, mcause                   # -.  Check if the triggering cause was an
	srli	a0, a0, 4                    #  |  interrupt or exception
	beqz 	a0, .L_exception             # -'
	li 	a0, 0x20                     # -.
	csrs 	mip, a0                      # -'  Set the supervisor timer to notify superviser when returning to supervisor mode
	ld	a0, -0x8(sp)
	csrrw	sp, mscratch, sp
	j 	delegate_clk                 # --  Trigger was interrupt, call `delegate_clk` handler
.L_exception:
	csrr	a0, mepc
	addi	a0, a0, 0x4
	csrw	mepc, a0
	csrr	a0, mcause
	csrrw	sp, mscratch, sp
	j 	handle_exception             # --  Trigger was exception, call `handle_exception` handler
