

/* ctxsw - This function switches the kernel's stack from the  *
 *         currently  running  thread to a  different thread.  *
 *         Additionally, it  sets the user  stack to the  new  *
 *         thread's user stack.                                *
 * @arg0 - thread_mem_t*  { u8* kernel_stack, u8* user_stack } *
 * @arg1 - thread_mem_t* { u8* kernel_stack, u8* user_stack }  */
.globl ctxsw
ctxsw:
	addi	sp, sp, -0x8
	sd	ra, 0(sp)
	sd	sp, 0(a1)          # -- old->kernel_stack = sp
	ld	sp, 0(a0)          # -- sp = new->kernel_stack
	ld	t0, 8(a0)          # -- v = new->user_stack
	csrrw 	t0, sscratch, t0   # -- v = user_stack && user_stack = v
	sd	t0, 8(a1)          # -- old->user_stack = v
	ld	ra, 0(sp)
	addi	sp, sp, 0x8
	ret

/* get_current_thread - Returns the thread id of the thread *
 *                      currently resident on this hart.    *
 * @return - u32  Current thread id.                        */
.globl get_current_thread
get_current_thread:
	mv a0, tp
	ret

/* set_current_thread - Sets the thread id of the thread  *
 *                      currently resident  on this hart  *
 *                      while returning the previous id.  *
 * @arg0   - u32  Current thread id.                      *
 * @return - u32  Previous thread id.                     */
.globl set_current_thread
set_current_thread:
	mv t0, tp
	mv tp, a0
	mv a0, t0
	ret
