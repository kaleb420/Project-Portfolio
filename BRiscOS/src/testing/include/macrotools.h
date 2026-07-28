#ifndef MACROTOOLS_H
#define MACROTOOLS_H

#define EVAL(...) __VA_ARGS__
#define NARGS(...) EVAL(NARGSi(__VA_ARGS__ __VA_OPT__(,) 8, 7, 6, 5, 4, 3, 2, 1, 0,))
#define NARGSi(_, _8, _7, _6, _5, _4, _3, _2, X_, ...) X_
#define CAT(x, y) CATi(x, y)
#define CATi(x, y) x##y

#define LIST_HEAD(...) EVAL(LIST_HEADi(__VA_ARGS__))
#define LIST_HEADi(head, ...) head
#define LIST_TAIL(...) EVAL(LIST_TAILi(__VA_ARGS__))
#define LIST_TAILi(head, ...) (__VA_ARGS__)
#define APPEND_LIST(new, ls) APPEND_LISTi(new, EVAL ls)
#define APPEND_LISTi(new, ...) (new  __VA_OPT__(,)  __VA_ARGS__)

#define EXPAND(macro, delim, tys, names) CAT(EXPAND_, NARGS tys)(macro, delim, tys, names)
#define EXPAND_0(macro, delim, tys, names)
#define EXPAND_1(macro, delim, tys, names) macro(tys, names)
#define EXPAND_2(macro, delim, tys, names) delim(macro(tys, names), EXPAND_1(macro, delim, LIST_TAIL tys, LIST_TAIL names))
#define EXPAND_3(macro, delim, tys, names) delim(macro(tys, names), EXPAND_2(macro, delim, LIST_TAIL tys, LIST_TAIL names))
#define EXPAND_4(macro, delim, tys, names) delim(macro(tys, names), EXPAND_3(macro, delim, LIST_TAIL tys, LIST_TAIL names))
#define EXPAND_5(macro, delim, tys, names) delim(macro(tys, names), EXPAND_4(macro, delim, LIST_TAIL tys, LIST_TAIL names))
#define EXPAND_6(macro, delim, tys, names) delim(macro(tys, names), EXPAND_5(macro, delim, LIST_TAIL tys, LIST_TAIL names))
#define EXPAND_7(macro, delim, tys, names) delim(macro(tys, names), EXPAND_6(macro, delim, LIST_TAIL tys, LIST_TAIL names))
#define EXPAND_8(macro, delim, tys, names) delim(macro(tys, names), EXPAND_7(macro, delim, LIST_TAIL tys, LIST_TAIL names)

#endif
