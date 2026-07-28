#ifndef H_DEVICES
#define H_DEVICES

#include <blib.h>

#define DF_BLOCK 0x1

typedef struct _device {
  u8* addr;                                                  /*                     */
  void (*handler)(struct _device*);                          /*                     */
  i64  (*open)(struct _device*, const char*, u64);           /*  Description of the */
  i64  (*close)(struct _device*, u64);                       /*  device driver and  */
  i64  (*read)(struct _device*, u64, char*, u64);            /*  associated funcs   */
  i64  (*write)(struct _device*, u64, const char*, u64);     /*                     */
  void* context;                                             /*                     */
  u32 interrupt;                                             /*                     */
  u32 flags;
  
  u64 delegate;                                              /*  Used in milestone-9 and 10 to allow drivers */
  struct _device* origin;                                    /*  to forward work along to a different driver */
} device_t;

i64 init_external(void);
i64 init_uart(const char*);
device_t* get_device_by_name(const char*);
device_t* get_device_by_handle(u64);
i64 register_device(const char*, device_t*);
i64 delegate_to_device(u64, u64);

u32 kprintf(const char*, ...);

#endif
