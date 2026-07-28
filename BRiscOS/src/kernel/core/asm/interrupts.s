	
/*  set_interrupt_handler - Sets the address that the kernel should jump to in *
 *  	the case of an exception or interrupt                                  *
 *  @arg0   - (void (handler*)(void))  Handler to assign to the kernel         *
 *  @return - void                                                             */
.globl set_interrupt_handler
set_interrupt_handler:
	csrw stvec, a0                 # -- Set the supervisor trap handler
	ret

/*  set_interrupt - Enable the specified interrupts                            *
 *  @arg0   - u64   Vector of bits corresponding to enabled interrupts         *
 *  @return - u64   Vector corresponding to the successfully enabled interruts */
.globl set_interrupt
set_interrupt:
	csrrs a0, sie, a0             # -- Enable the requested interrupts
	ret

/*  acknowledge_interrupt - Clear the specified pedning interrupts.            *
 *  @arg0   - u64  Vector of bits corresponding to disable interrupts          *
 *  @return - u64  Vector corresponding to the successfully disabled interruts */
.globl acknowledge_interrupt
acknowledge_interrupt:
	ecall             # -- Disable the requested interrupts
	ret

/*  enable_interrupts - Request for the hardware to begin servicing any        *
 *	previously enabled interrupts                                          *
 *  @return - void                                                             */
.globl enable_interrupts
enable_interrupts:
	li    t0, 0x20                # -.  Set the sstatus.SIE
	csrs sstatus, t0              # -'  (supervisor interrupt enabled) bit
	ret

/*  supervisor_traps - Interrupt delegator function for supervisor interrupts  *
 *	This function is invoked automatically when an interrupt or exception  *
 *	occurs.   It stores the  current  context, switching  to the thread's  *
 *	kernel  context, calls the  proper handler, then  restores the thread  *
 *      context before finally resuming normal execution.                      */
	.align 2
	.equ REGSZ, 8
	.equ STASH_SIZE, 17
.globl context_stash_size
context_stash_size:
	.word STASH_SIZE
.globl fresh_thread_entry
.globl supervisor_traps
supervisor_traps:
	addi	sp, sp, -STASH_SIZE*REGSZ  # -.
	sd	ra,  1*REGSZ(sp)           #  |
	sd	a1,  2*REGSZ(sp)           #  |
	sd	a2,  3*REGSZ(sp)           #  |
	sd	a3,  4*REGSZ(sp)           #  |
	sd	a4,  5*REGSZ(sp)           #  |
	sd	a5,  6*REGSZ(sp)           #  |
	sd	a6,  7*REGSZ(sp)           #  |  Store the current context onto the thread stack
	sd     	a7,  8*REGSZ(sp)           #  |
	sd	t0,  9*REGSZ(sp)           #  |
	sd	t1, 10*REGSZ(sp)           #  |
	sd	t2, 11*REGSZ(sp)           #  |
	sd	t3, 12*REGSZ(sp)           #  |
	sd	t4, 13*REGSZ(sp)           #  |
	sd	t5, 14*REGSZ(sp)           #  |
	sd	t6, 15*REGSZ(sp)           #  |
	sd	s0, 16*REGSZ(sp)           # -'
	la	t0, handle_interrupt       # --  Set the handler function to `interrupt` mode
	csrr	t1, sepc                   # -.  Read the current return point and interrupt/exception cause
	csrr	t2, scause                 # -'
	bltz	t2, .L_interrupt           # -.  If the cause was an exception, change the handler to `syscall` mode
	la	t0, handle_syscall         #  |  and adjust the return point to 1 instruction ahead
	addi	t1, t1, 0x4                # -'
	j	.L_handle                  # --  Go to interrupt handling
.L_interrupt:
	mv	a1, t2                     # --  If the cause was an interrupt, pass the cause in as the second argument
.L_handle:
	sd	t1,  0*REGSZ(sp)           # -.
	csrrw	sp, sscratch, sp           #  |  Swap from the previous user stack to the kernel stack
	jalr	t0                         # -'  Execute the requested handler
fresh_thread_entry:
	csrrw	sp, sscratch, sp           # --  Swap back from the kernel stack to the next user stack
	ld	t0,  0*REGSZ(sp)           # -.
	csrw    sepc, t0                   # -'  Load the return point from the stack
	ld	ra,  1*REGSZ(sp)           #  |
	ld	a1,  2*REGSZ(sp)           #  |
	ld	a2,  3*REGSZ(sp)           #  |
	ld	a3,  4*REGSZ(sp)           #  |
	ld	a4,  5*REGSZ(sp)           #  |
	ld	a5,  6*REGSZ(sp)           #  |
	ld	a6,  7*REGSZ(sp)           #  |
	ld	a7,  8*REGSZ(sp)           #  |  Restore the context from the thread stack
	ld	t0,  9*REGSZ(sp)           #  |
	ld	t1, 10*REGSZ(sp)           #  |
	ld	t2, 11*REGSZ(sp)           #  |
	ld	t3, 12*REGSZ(sp)           #  |
	ld	t4, 13*REGSZ(sp)           #  |
	ld	t5, 14*REGSZ(sp)           #  |
	ld	t6, 15*REGSZ(sp)           #  |
	ld	s0, 16*REGSZ(sp)           #  |
	addi	sp, sp, STASH_SIZE*REGSZ   # -'
	sret
