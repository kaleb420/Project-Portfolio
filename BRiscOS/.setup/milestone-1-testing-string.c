#include <blib.h>

static i32 sputs(char* buf, i32 len, const char* str) {
  i32 i=0;
  for (; str[i] != '\0' && --len > 0; i++)
    buf[i] = str[i];
  return i;
}

static i32 sputd(char* buf, i32 len, u64 val, u32 base) {
  i32 i=0;
  u8 rev[9] = { 0 };
  if (val == 0) {
    *buf++ = '0';
    return 1;
  }

  for (; val > 0; val = val / base)
    rev[i++] = (u8)(val % base);
  for (i32 w = i - 1; w >= 0 && --len > 0; w--)
    *buf++ = (u8)(rev[w] + (rev[w] < 10 ? '0' : ('a' - 10)));
  return i;
}

char* vstring_builder(char* buf, i32 len, const char* fmt, va_list* args) {
  len -= 1;
  char* ptr = buf;
  for (; *fmt != '\0' && len > 0; fmt++) {
    i32 diff;
    switch (*fmt) {
    case 's':
      diff = sputs(ptr, len, (char*)va_arg(*args, char*)); break;
    case 'd':
      i64 v = (i64)va_arg(*args, i64);
      diff = 0;
      if (v < 0) {
	diff = sputs(ptr, len, "-");
	v = 0 - v;
      }
      diff += sputd(ptr, len, (u64)v, 10); break;
    case 'c':
      diff = 1; *ptr = (char)va_arg(*args, int); break;
    case 'x':
      diff = sputs(ptr, len, "0x");
      diff += sputd(ptr+2, len-2, (u64)va_arg(*args, u64), 16); break;
    }
    ptr += diff;
    len -= diff; 
  }
  *ptr = '\0';
  return buf;
}

char* string_builder(char* buf, i32 len, const char* fmt, ...) {
  va_list args;
  va_start(args, fmt);
  buf = vstring_builder(buf, len, fmt, &args);
  va_end(args);
  return buf;
}

i32 test_strcmp(const char* a, const char* b) {
  if (a == NULL || b == NULL)
    return -1;
  for (; *a != '\0' && *a == *b; a++)
    b++;
  return *a - *b;
}

i32 test_strlen(const char* str) {
  i32 len, control=0, isctrl = 0;
  for (len = 0; str[len] != '\0'; len++) {
    if (str[len] == '\033' && str[len+1] == '[')
      isctrl = 1;
    if (isctrl) {
      control += 1;
      if (isctrl == 1 && str[len] == '[')
	isctrl = 2;
      else if (isctrl == 2 && str[len] >= 0x40 && str[len] < 0x7F)
	isctrl = 0;
    }
  }
  return len - control;
}

