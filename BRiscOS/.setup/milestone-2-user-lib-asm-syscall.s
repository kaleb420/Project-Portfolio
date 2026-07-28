
/*  syscall - This function  triggers an  `environment exception`.    *
 *            Such an exception  can be detected by the kernel and    *
 *            is treated as an indirect request to run an internal    *
 *            kernel function.                                        *
 *  @arg0   - i64  The numeric value corresponding with the requested *
 *            function. (see `shared/include/syscall.h`)              *
 *  @arg1+  - Any  Additional arguments to forward to the syscall.    *
 *  @return - i64  Status code response to the requested system call. */
.globl syscall
syscall:
	ecall
	ret
