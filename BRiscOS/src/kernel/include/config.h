#ifndef H_CONFIG
#define H_CONFIG

/*  This file contains configuration variables that dictate the size and behavior of  *
 *  kernel constants, like the maximum number of threads and devices.                 */


/*  MS-1: Dictates the size of the 'thread_table' which stores metadata about each running thread   */
#define NUM_THREADS      16

/*  MS-1: Dictates the size of the 'device_table' which stores metadata about each attached device  */
#define NUM_DEVS          8

/*  MS-1: Dictates the size of the 'handle_table' which stores references to all open handles  */
#define NUM_HANDLES      128

/*  MS-6: Dictates the size of the 'futex_hashtab' hash table, which accelerates futex queue lookup */
#define NUM_HASHTAB_BINS 16


#endif
