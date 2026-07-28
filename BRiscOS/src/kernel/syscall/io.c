#include <blib.h>
#include <devices.h>
#include <interrupts.h>
#include <config.h>
#include <syscall.h>

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
	u64 bounds=NUM_HANDLES;
	for (u64 i=0; i<NUM_HANDLES; i++){
		if (handle_table[i].used==0){
			bounds=i;
			break;
		}
	}
	if (bounds==NUM_HANDLES)
		return -1; 
	handle_table[bounds].device=get_device_by_name(path);
	if (handle_table[bounds].device==NULL)
		return -1;
	handle_table[bounds].used=1;
	handle_table[bounds].handle=bounds;
	i64 temp=(i64) bounds;
	if (handle_table[bounds].device->open!=NULL)
		handle_table[bounds].handle=(u64) handle_table[bounds].device->open(handle_table[bounds].device, path, len);
	return temp; 
}


/*  This function calls the relevent close function for the requested handle,  *
 *  then removes the corresponding entry from the handle table.                */
syscall_t close(u64 handle) {
	if (handle_table[handle].used==1){
		handle_table[handle].used=0;
		if (handle_table[handle].device->close==NULL)
			return 0; 
		return handle_table[handle].device->close(handle_table[handle].device, handle_table[handle].handle);
	} 
	return -1;
}


/*  This function calls the relevent write function for the requested handle  *
 *  and passes along the result back to user mode.                            */
syscall_t write(u64 handle, const char* buffer, u64 len) {
	if (handle_table[handle].device==NULL || handle_table[handle].used==0 || handle_table[handle].device->write==NULL)
		return -1;
	return handle_table[handle].device->write(handle_table[handle].device, handle_table[handle].handle, buffer, len);
}


/*  This function calls the relevent read function for the requested handle  *
 *  and passes along the result back to user mode.                           */
syscall_t read(u64 handle, char* buffer, u64 len) {
	if (handle_table[handle].device==NULL || handle_table[handle].used==0 || handle_table[handle].device->read==NULL)
                return -1;
        return handle_table[handle].device->read(handle_table[handle].device, handle_table[handle].handle, buffer, len);
}


#include <io.ut>
