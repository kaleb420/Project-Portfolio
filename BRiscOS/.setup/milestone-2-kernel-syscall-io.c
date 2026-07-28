#include <blib.h>
#include <devices.h>
#include <interrupts.h>
#include <config.h>

/*
 *  This file contains generic delegator functions that are called when a
 *  user thread requests an input/output operation. 
 *    (see `kernel/core/interrupts.c`, `shared/include/syscall.h` and `kernel/device/external.c`)
 *  Each function determines which device should service the request, then
 *  calls the handler associated with the type of input/output requested
 *  on that device.
 */
                     /*                                    */
typedef struct {     /* This struct is used exclusively in */
  u64 handle;        /* this file to keep track of which   */
  device_t* device;  /* device created each "handle" or    */
  u8  used;          /* input/output interface             */
} handle_entry_t;    /*                                    */

static handle_entry_t handle_table[NUM_HANDLES] = {0};    /*  The map of handles to devices  */


/*  This function checks if there is an available handle in the handle_table.  *
 *  If  there is,  it gets the  device reference  at the requested  path with  *
 *  `get_device_by_name` then uses the associated open function to attempt to  *
 *  open the requested file or device.  It then creates a corresponding entry  *
 *  in the handle_table.                                                       */
syscall_t open(const char* path, u64 len) {


  
  return 0;
}


/*  This function calls the relevent close function for the requested handle,  *
 *  then removes the corresponding entry from the handle table.                */
syscall_t close(u64 handle) {



  return 0;
}


/*  This function calls the relevent write function for the requested handle  *
 *  and passes along the result back to user mode.                            */
syscall_t write(u64 handle, const char* buffer, u64 len) {



  return 0;
}


/*  This function calls the relevent read function for the requested handle  *
 *  and passes along the result back to user mode.                           */
syscall_t read(u64 handle, char* buffer, u64 len) {



  return 0;
}


#include <io.ut>
