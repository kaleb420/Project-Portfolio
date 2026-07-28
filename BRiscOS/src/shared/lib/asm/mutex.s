
/*  create_mutex - Sets up a mutex in memory to be 0.  In most cases, this  *
 *                 can be done manually with atomaticity, but create_mutex  *
 *                 ensures consistency between all mutex related calls.     *
 *  @arg0   - void*  Address of the value to be initialized as a mutex.     *
 *  @return - void*  Same address passed in as arg0.                        */
.globl create_mutex
create_mutex:
	amoswap.w.rl zero, zero, (a0)
	ret


/*  lock_mutex - Atomically sets the mutex to 0, if the mutex is already 0  *
 *               lock will block until the mutex is available.              *
 *  @arg0   - void*  Address of the value to be used as a mutex.            *
 *  @return - void*  Same address passed in as arg0.                        */
.globl lock_mutex
lock_mutex:
	li t0, 1 
1:
	lw t1, (a0)                   # -.  If the mutex is currently locked
	bnez t1, 1b                   # -'  loop until it is marked as released
	amoswap.w.aq t1, t0, (a0)     # --  Atomically lock the mutex
	bnez t1, 1b                   # --  Loop if it failed to acquire the lock
	ret

/*  release_mutex - Atomically release the mutex.                 *
 *  @arg0   - void*  Address of the value to be used as a mutex.  *
 *  @return - void*  Same address passed in as arg0.              */
.globl release_mutex
release_mutex:
	amoswap.w.rl zero, zero, (a0)
	ret
