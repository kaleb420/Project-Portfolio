OUTPUT_ARCH(riscv)
ENTRY(_boothdr)

/*
 *  This file tells the compiler what addresses in memory to place each function and global
 *  variable.  It also allows us to designate special memory addresses as symbol locations 
 *  for use in our code (see ld_* variables)
 */

/* ld_text_start - Colocated  with the first  instruction  of the  kernel *
 * ld_user_start - Colocated with the first instruction of the user  text *
 * ld_text_end   - Located at  the  address  after the  last  instruction *
 * ld_data_start - Colocated  with  the first  global or static  variable *
 * ld_bss_start  - Colocated with the first uninitialized global variable *
 * ld_bss_end    - Located at the address after  the last global variable *
 * ld_estack_top - Located at the address one  page above the bss segment *
 * ld_mem_start  - Colocated with the  first unused r/w address in memory *
 * ld_mem_end    - Located at the address  after the last r/w memory addr */


MEMORY {
       _mem : ORIGIN =0x80000000, LENGTH =0x7e00000
}

SECTIONS {
	 .text : {
	     PROVIDE(ld_text_start = ABSOLUTE(.));
	     *(.text.entry)
	     *(.text)
	     . = ALIGN(4096);
	     PROVIDE(ld_user_start = ABSOLUTE(.));
	     *(.text.user.entry)
	     *(.text.user .text.user.*)
	     PROVIDE(ld_text_end = ABSOLUTE(.));
	 } > _mem

	 .data : {
	     PROVIDE(ld_data_start = ABSOLUTE(.));
	     PROVIDE(ld_init_array_start = ABSOLUTE(.));
	     *(.init_array .init_array.*)
	     PROVIDE(ld_init_array_end = ABSOLUTE(.));
	     *(.sdata .sdata.*)
	     *(.data .data.*)
	 } > _mem

	 .bss : {
	     PROVIDE(ld_bss_start = ABSOLUTE(.));
	     *(.sbss .sbss.*)
	     *(.bss .bss.*)
	     PROVIDE(ld_bss_end = ABSOLUTE(.));
	 } > _mem

	 . = ALIGN(0x1000);
	 . += 0x1000;
	 PROVIDE(ld_estack_top = ABSOLUTE(.));
	 
	 PROVIDE(__global_pointer$ = ld_data_start + 0x800);
	 PROVIDE(ld_mem_start = ABSOLUTE(.));
	 PROVIDE(ld_mem_end = ORIGIN(_mem) + LENGTH(_mem));
}
