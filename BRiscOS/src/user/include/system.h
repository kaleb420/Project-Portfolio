#ifndef H_USER_SYSCALL
#define H_USER_SYSCALL

#include <syscall.h>

/*
 *  This file gives user programs access to the kernel's syscall interface.
 *  Making a call to the `syscall` function passes an indirect request to
 *  the kernel to service a specific system call.  These system calls are
 *  enumerated in the SYSCALL_LIST macro in the `shared/include/syscall.h`
 *  file.
 */

/*  `syscall` takes between 2 and 5 arguments            */
/*      arg0  - The syscall IDENTIFIER number            */
/*      arg1+ - arguments 0-3 of the underlying syscall  */
i64 syscall(syscall_e, ...);

#endif
