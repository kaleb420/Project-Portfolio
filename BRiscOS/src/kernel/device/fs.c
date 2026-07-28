#include <blib.h>
#include <fs.h>
#include <heap.h>

static filetable_t oft[NUM_FD];
static void set_usedblock_fs(fsystem_t*, u32, u8);
static i8   get_usedblock_fs(fsystem_t*, u32);





static i64 fs_create(device_t *dev, const char *path){
	fsystem_t *fsd= (fsystem_t *) dev->context;
	block_device_t* disk = (block_device_t*)dev->origin;
	u32 oi=0;
	int check=0;
	u32 di=fsd->root_dir.numentries;
	for (u32 i=0; i<fsd->root_dir.numentries; i++){
		if (strcmp(path, fsd->root_dir.entry[i].name)==0)
			return -1;
	}
	for (u32 i=0; i<NUM_FD; i++){
		if (oft[i].state==FSTATE_CLOSED && check==0){
			oi=i;
			check=1;
		}
		if (NUM_FD-1==i && check==0)
			return -1;
	}
	for (u32 i=0; i<MDEV_NUM_BLOCKS; i++){
		if (get_usedblock_fs(fsd, i)==0){
			set_usedblock_fs(fsd, i, 1);
			fsd->root_dir.numentries++;
			fsd->root_dir.entry[di].inode_block=i;
			oft[oi].inode.id=i;
			oft[oi].inode.size=0;
			oft[oi].direntry=di;
			oft[oi].head=0; 
			for (u32 j=0; j<sizeof(oft[oi].inode.blocks)/sizeof(oft[oi].inode.blocks[0]); j++){
				oft[oi].inode.blocks[j]=0;
			} 
			strcpy(path, fsd->root_dir.entry[di].name);
			break;
		}
		if (MDEV_NUM_BLOCKS-1==i)
			return -1;
	}
	disk->write(disk, fsd->root_dir.entry[di].inode_block, 0, &fsd->root_dir.entry[di], sizeof(inode_t));
	disk->write(disk, BM_BIT, 0, fsd->freemask, fsd->freemasksz);
	disk->write(disk, SB_BIT, 0, fsd, sizeof(fsystem_t));
	return fsd->root_dir.entry[di].inode_block;
}

/*  fs_open - The filesystem  driver  function  for  opening/creating a file.  *
 *            This  function is responsible for  checking for the  filename's  *
 *            existence  in the root  directory.   If the file  doesn't exist  *
 *            create a fresh  file with that name.   If it does, the function  *
 *            checks if the  file is already open,  and adds it to the oft if  *
 *            is not.                                                          *
 *  @dev     - device_t*  The device this file should be saved to.             *
 *  @path    - char*  The name of the file.                                    *
 *  @pathlen - u64    The length of the file name.                             *
 *  @return  - i64    Returns the index of the  newly opened file in the open  *
 *                    file table on success, -1 on failure.                    */
static i64 fs_open(device_t* dev, const char* path, [[gnu::unused]] u64 pathlen) {
	fsystem_t*       fsd = (fsystem_t*)dev->context;
	block_device_t* disk = (block_device_t*)dev->origin;
	
	i64 inode=-1;
	u32 di=0;
	for (u32 i=0; i<fsd->root_dir.numentries; i++){
		if (strcmp(path, fsd->root_dir.entry[i].name)==0){
			di=i;
			inode=fsd->root_dir.entry[i].inode_block;
			break;
		}
		if (fsd->root_dir.numentries-1==i){
			inode=fs_create(dev, path);
			di=i;
		}
	}
	for (u32 i=0; i<NUM_FD; i++){
		if (oft[i].state==FSTATE_OPEN && oft[i].inode.id==inode)
			return -1;
	}
	if (inode==-1)
		return -1;
	for (int i=0; i<NUM_FD; i++){
		if (oft[i].state==FSTATE_CLOSED){
			oft[i].inode.id= (u32) inode;
			oft[i].state=FSTATE_OPEN;
			oft[i].head=0;
			oft[i].direntry=di;
			disk->read(disk, oft[i].inode.id, 0, &oft[i].inode, sizeof(inode_t));
			return i;
		}
	}
	return -1;
} 


/*  fs_close - The filesystem driver function for closing a file.  *
 *             This  function is responsible for  removing a file  *
 *             descriptor from the open file table.                *
 *  @dev    - device_t*  The device this file is located on.       *
 *  @fd     - u64        The index of the  file descriptor in the  *
 *                       open file table.                          *
 *  @return - i64        Returns 0 on success, -1 on failure.      */
static i64 fs_close(device_t* dev, u64 fd) {
	block_device_t* disk = (block_device_t*)dev->origin;
	if (oft[fd].state==FSTATE_CLOSED)
		return -1;
	disk->write(disk, oft[fd].inode.id, 0, &oft[fd].inode, sizeof(inode_t));
	oft[fd].state=FSTATE_CLOSED;
	return 0;
}



/*  fs_read - The  filesystem driver function  responsible for reading a  file.  *
 *            This function is responsible for taking a descriptor and fetching  *
 *            the relevent blocks of data from the block device.   This data is  *
 *            copied into the provided user buffer.                              *
 *  @dev    - device_t*  The device from which this file should be read.         *
 *  @fd     - u64        The open file  table index holding the  descriptor for  *
 *                       the requested file.                                     *
 *  @buffer - char*      The buffer into which data from the file is written.    *
 *  @len    - u64        The maxinum number of characters read into the buffer.  *
 *  @return - i64        The len or the number of  characters read whichever is  *
 *                       smaller or -1 on failure.                               */
static i64 fs_read([[gnu::unused]] device_t* dev, [[gnu::unused]] u64 fd,
		   [[gnu::unused]] char* buffer,  [[gnu::unused]] u64 len) {
  [[gnu::unused]] fsystem_t*       fsd = (fsystem_t*)dev->context;
  [[gnu::unused]] block_device_t* disk = (block_device_t*)dev->origin;



  return 0;
}


/*  fs_write - The filesystem  driver  function  reponsible  for writing to a file  *
 *             This function is responsible for taking the desciptor  and writting  *
 *             data to the file blocks.  This may require adding new blocks to the  *
 *             file to make room for the data.                                      *
 *  @dev    - device_t*  The device to which this file should be written.           *
 *  @fd     - u64        The index into the open file table holding the descriptor  *
 *                       for the requested file.                                    *
 *  @buffer - char*      The buffer containing data to write to the file blocks.    *
 *  @len    - u64        The number of characters held in the buffer.               *
 *  @return - i64        Returns the number  of characters  written to the file or  *
 *                       -1 on failure.                                             */
static i64 fs_write([[gnu::unused]] device_t* dev,      [[gnu::unused]] u64 fd,
		    [[gnu::unused]] const char* buffer, [[gnu::unused]] u64 len) {
  [[gnu::unused]] fsystem_t*       fsd = (fsystem_t*)dev->context;
  [[gnu::unused]] block_device_t* disk = (block_device_t*)dev->origin;



  return 0;
}



/* ----------------------- Utility Funcs ------------------------------ */



/*  set_usedblock_fs - Toggle the used bit for the requested block in the provided  *
 *                     filesystem's used block bitmap.                              *
 *  @fsd    - fsystem_t*  Pointer to the filesystem to modify.                      *
 *  @idx    - u32         The block to set or clear in the bitmap.                  *
 *  @isused - u8          The state of the block 0 for unused, 1 for used.          *
 *  @return - void                                                                  */
static void set_usedblock_fs(fsystem_t* fsd, u32 idx, u8 isused) {
  if (fsd == NULL) return;
  fsd->freemask[idx / 8] = (u8)(fsd->freemask[idx / 8] & ~(0x1 << (idx % 8))) | isused << (idx % 8);
}


/*  get_usedblock_fs - Get the current state of the requested block index.  *
 *  @fsd    - fsystem_t*  Pointer to the filesystem containing the block.   *
 *  @idx    - u32         The block to query.                               *
 *  @return - i8          Returns 0 if block is unused, 1 if used.          */
static i8 get_usedblock_fs(fsystem_t* fsd, u32 idx) {
  if (fsd == NULL) return -1;
  return (fsd->freemask[idx / 8] >> (idx % 8)) & 0x1;
}


/*  init_ramdisk - Initialize the filesystem by creating the open file table and superblock  *
 *  @namespace - char*  The unique name to identify this device on the system.               *
 *  @numblocks - u32    The number of blocks to allocate.                                    *
 *  @return    - i64    The handle for this device.                                          */
i64 init_fs(const char* namespace, u64 diskhandle) {
  fsystem_t* fs;
  block_device_t* disk = (block_device_t*)get_device_by_handle(diskhandle);
  bdev_t disk_desc = *(bdev_t*)disk->context;
  u32 masksize = (disk_desc.nblocks / 8) + (disk_desc.nblocks % 8 > 0);
  if (!(fs = (fsystem_t*)kmalloc(sizeof(fsystem_t))) || !(fs->freemask = kmalloc(masksize)))
    return -1;

  memcpy(&fs->device, &disk_desc, sizeof(bdev_t));
  fs->freemasksz = masksize;
  memset(&fs->root_dir, 0, sizeof(directory_t));
  memset(fs->freemask, 0, masksize);
  
  for (u32 i=0; i<DIR_SIZE; i++)
    memset(fs->root_dir.entry[i].name, 0, FILENAME_LEN);
  fs->freemask[0] = 0x3;

  disk->write(disk, SB_BIT, 0, fs, sizeof(fsystem_t));
  disk->write(disk, BM_BIT, 0, fs->freemask, fs->freemasksz);

  for (u32 i=0; i<NUM_FD; i++)
    oft[i] = (filetable_t) { .state = FSTATE_CLOSED, .head = 0, .direntry = 0 };

  i64 fsdev = register_device(namespace, &(device_t) {
      .open = fs_open, .close = fs_close,
      .read = fs_read, .write = fs_write,
      .context = fs,
    });
  delegate_to_device(diskhandle, (u64)fsdev);
  return fsdev;
}



#include <fs.ut>
#include <fs_io.ut>
