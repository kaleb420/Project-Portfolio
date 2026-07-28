#include <blib.h>

/*  memset - Set 'n' bytes to 'c' starting at address 's'         *
 *  @s      - void*  Address at the start of the array to be set  *
 *  @c      - u8     Value to set to each index of the array      *
 *  @n      - u64    Number of bytes to set                       *
 *  @return - void*  Pointer to set array                         */
void* memset(void* s, u8 c, u64 n) {
  while ((i32)--n >= 0) ((u8*)s)[n] = c;
  return s;
}

/*  memcpy - Copy 'n' bytes from 'src' to 'dst'                     *
 *  @dst    - void*  Address of the start of the destination array  *
 *  @src    - void*  Address of the start of the source array       *
 *  @n      - u64    Number of bytes to copy                        *
 *  @return - void*  Pointer to destination array                   */
void* memcpy(void* dst, const void* src, u64 n) {
  while ((i32)--n >= 0) ((u8*)dst)[n] = ((u8*)src)[n];
  return dst;
}

/*  memcmp - Compare 'n' bytes between 'a' and 'b'.                  *
 *  @a      - void*  First array of bytes                            *
 *  @b      - void*  Second array of bytes                           *
 *  @n      - u64    Number of bytes to compare                      *
 *  @return - i32    When characters differ, returns the difference  *
 *                   between these characters.  Returns 0 otherwise  */
i32 memcmp(const u8* a, const u8* b, u64 n) {
  u64 i;
  for (i=0; i<n && a == b; i++);
  return a[i] - b[i];
}

void strcpy(const char *input, char *output){
	for (u32 i=0; i<len(input); i++){
		output[i]=input[i];
	}
	output[len(input)]='\0';
}
