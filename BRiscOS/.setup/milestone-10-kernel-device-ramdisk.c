#include <blib.h>
#include <fs.h>
#include <devices.h>

void* kmalloc(u64);


/*  bs_read - This function reads an array of bytes from a block device.  The array  *
 *            must be entirely contained within a single block.                      *
 *  @dev    - block_device_t*  The device from which the block should be read.       *
 *  @block  - u64              The block index from which the data should be read.   *
 *  @offset - u64              How many bytes into the  block should the read begin  *
 *                             from.                                                 *
 *  @buf    - void*            The buffer into which the data is written.            *
 *  @len    - len              The number of bytes read from the block.              *
 *  @return - i64              The number of bytes read or -1 on failure             */
static i64 bs_read(block_device_t* dev, u64 block, u64 offset, void* buf, u64 len) {
  bdev_t* ramdisk = (bdev_t*)dev->context;
  if (offset + len > ramdisk->blocksz || block >= ramdisk->nblocks || dev->addr == NULL)
    return -1;
  memcpy(buf, &(dev->addr[block * ramdisk->blocksz]) + offset, len);
  return (i64)len;
}


/*  bs_read - This function writes an array of bytes to a block device.   The array  *
 *            must be entirely contained within a single block.                      *
 *  @dev    - block_device_t*  The device from which the block should be read.       *
 *  @block  - u64              The block index into which the data should be write.  *
 *  @offset - u64              How many bytes into the block should the write begin  *
 *                             from.                                                 *
 *  @buf    - void*            The buffer from which the data is written.            *
 *  @len    - len              The number of bytes to write into the block.          *
 *  @return - i64              The number of bytes written or -1 on failure          */
static i64 bs_write(block_device_t* dev, u64 block, u64 offset, const void* buf, u64 len) {
  bdev_t* ramdisk = (bdev_t*)dev->context;
  if (offset + len > ramdisk->blocksz || block >= ramdisk->nblocks || dev->addr == NULL)
    return -1;
  memcpy(&(dev->addr[block * ramdisk->blocksz]) + offset, buf, len);
  return (i64)len;
}

/*  init_ramdisk - Initialize the ramdisk by allocating a requested number of blocks from memory  *
 *  @namespace - char*  The unique name to identify this device on the system.                    *
 *  @numblocks - u32    The number of blocks to allocate.                                         *
 *  @return    - i64    The handle for this device.                                               */
i64 init_ramdisk(const char* namespace, u32 numblocks) {
  u8* disk;
  bdev_t* ramdisk = kmalloc(sizeof(bdev_t));
  numblocks = (numblocks == NULL ? MDEV_NUM_BLOCKS : numblocks);

  if (!(ramdisk = kmalloc(sizeof(bdev_t))) || !(disk = kmalloc(numblocks * MDEV_BLOCK_SIZE)))
    return -1;

  ramdisk->blocksz = MDEV_BLOCK_SIZE;
  ramdisk->nblocks = numblocks;
  return register_device(namespace, (device_t*)&(block_device_t) {
      .addr    = disk,
      .context = ramdisk,
      .read  = bs_read,
      .write = bs_write,
      .flags = DF_BLOCK
    });
}
