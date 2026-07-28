#ifndef H_FS
#define H_FS

#include <blib.h>
#include <devices.h>

#define SB_BIT 0
#define BM_BIT 1

#define FILENAME_LEN 16
#define DIR_SIZE     16
#define INODE_BLOCKS 12
#define MDEV_BLOCK_SIZE 512
#define MDEV_NUM_BLOCKS 512

#define FSTATE_CLOSED 0
#define FSTATE_OPEN   1
#define NUM_FD       10

typedef struct {
  u32 id;
  u32 size;
  u32 blocks[INODE_BLOCKS];
} inode_t;

typedef struct {
  u32 inode_block;
  char name[FILENAME_LEN];
} dirent_t;

typedef struct {
  u32 numentries;
  dirent_t entry[DIR_SIZE];
} directory_t;

typedef struct {
  u32 nblocks;
  u32 blocksz;
} bdev_t;

typedef struct {
  bdev_t device;
  u32 freemasksz;
  directory_t root_dir;
  u8* freemask;
} fsystem_t;

typedef struct {
  inode_t inode;
  u32 head;
  u32 direntry;
  u8 state;
} filetable_t;

/*  Duplicate of the device_t struct (see `kernel/include/devices.h`) specialized for block  *
 *  devices instead of character devices.                                                    */
typedef struct _bdevice {
  u8* addr;                                                     /*                     */
  void (*handler)(struct _bdevice*);                            /*                     */
  i64  (*open)(struct _bdevice*, const char*, u64);             /*  Description of the */
  i64  (*close)(struct _bdevice*, u64);                         /*  device driver and  */
  i64  (*read)(struct _bdevice*,u64, u64, void*, u64);          /*  associated funcs   */
  i64  (*write)(struct _bdevice*, u64, u64, const void*, u64);  /*                     */
  void* context;                                                /*                     */
  u32 interrupt;                                                /*                     */
  u32 flags;
  
  u64 delegate;                                              /*  Used in milestone-9 and 10 to allow drivers */
  struct _device* origin;                                    /*  to forward work along to a different driver */
} block_device_t;


i64 init_ramdisk(const char*, u32);
i64 init_fs(const char*, u64);

#endif
