#include <blib.h>
#include <guard.h>

guard_t guard_point;

[[gnu::naked]]
void restore_guard([[gnu::unused]] i64 ret) {
  asm volatile("la a1, guard_point\n"
	       "sd a0, 80(a1)\n"
	       "li a0, 0\n"
	       "sd a0, 256(a1)\n"
	       "mv a0, a1\n"
	       "ld a1,  0(a0)\n"
	       "jr  a1");
}

[[gnu::naked]]
i64 set_guard(void) {
  asm volatile("la  a0, guard_point\n"
	       "sd  x1,   8(a0)\n"
	       "sd  x2,  16(a0)\n"
	       "sd  x3,  24(a0)\n"
	       "sd  x4,  32(a0)\n"
	       "sd  x5,  40(a0)\n"
	       "sd  x6,  48(a0)\n"
	       "sd  x7,  56(a0)\n"
	       "sd  x8,  64(a0)\n"
	       "sd  x9,  72(a0)\n"
	       "sd x11,  88(a0)\n"
	       "sd x12,  96(a0)\n"
	       "sd x13, 104(a0)\n"
	       "sd x14, 112(a0)\n"
	       "sd x15, 120(a0)\n"
	       "sd x16, 128(a0)\n"
	       "sd x17, 136(a0)\n"
	       "sd x18, 144(a0)\n"
	       "sd x19, 152(a0)\n"
	       "sd x20, 160(a0)\n"
	       "sd x21, 168(a0)\n"
	       "sd x22, 176(a0)\n"
	       "sd x23, 184(a0)\n"
	       "sd x24, 192(a0)\n"
	       "sd x25, 200(a0)\n"
	       "sd x26, 208(a0)\n"
	       "sd x27, 216(a0)\n"
	       "sd x28, 224(a0)\n"
	       "sd x29, 232(a0)\n"
	       "sd x30, 240(a0)\n"
	       "sd x31, 248(a0)\n"
	       "li a1, 1\n"
	       "sd a1, 256(a0)\n"
	       "li a1, 0\n"
	       "sd a1, 80(a0)\n"
	       "auipc a1, 0\n"
	       "addi a1, a1, 8\n"
	       "sd a1, 0(a0)\n"
	       "ld  x1,   8(a0)\n"
	       "ld  x2,  16(a0)\n"
	       "ld  x3,  24(a0)\n"
	       "ld  x4,  32(a0)\n"
	       "ld  x5,  40(a0)\n"
	       "ld  x6,  48(a0)\n"
	       "ld  x7,  56(a0)\n"
	       "ld  x8,  64(a0)\n"
	       "ld  x9,  72(a0)\n"
	       "ld x11,  88(a0)\n"
	       "ld x12,  96(a0)\n"
	       "ld x13, 104(a0)\n"
	       "ld x14, 112(a0)\n"
	       "ld x15, 120(a0)\n"
	       "ld x16, 128(a0)\n"
	       "ld x17, 136(a0)\n"
	       "ld x18, 144(a0)\n"
	       "ld x19, 152(a0)\n"
	       "ld x20, 160(a0)\n"
	       "ld x21, 168(a0)\n"
	       "ld x22, 176(a0)\n"
	       "ld x23, 184(a0)\n"
	       "ld x24, 192(a0)\n"
	       "ld x25, 200(a0)\n"
	       "ld x26, 208(a0)\n"
	       "ld x27, 216(a0)\n"
	       "ld x28, 224(a0)\n"
	       "ld x29, 232(a0)\n"
	       "ld x30, 240(a0)\n"
	       "ld x31, 248(a0)\n"
	       "ld  a0,  80(a0)\n"
	       "ret");
  return 0;
}

void clear_guard(void) {
    guard_point.registered = 0;
}
