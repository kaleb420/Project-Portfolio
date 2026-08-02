## Introduction

Computer systems is a intermediate level class based upon the C programming language. This class taught the core concepts of computer architecture and software design methodology on a low-level basis. The following projects were based upon these teachings, but is not the full extent of content we covered in class. More nuanced topics, like creating threads and file IO, were replicated in the operating systems course, or BRiscOS in this portfolio. One project that couldn't be represented in this repository is discussed first, while everything else will have an associated header. 

## Reverse Enginneering

This assignment directed us to break passwords, giving us assembly code and the responsibility to find a correlated password that would work. Some were easy, such as the password being stored in a string where it could be visibly seen. Others were more challenging, such as a characters being stored in ASCII, then needing to be translated and reversed before being cracked. And the most challenging one was a linked list structure, where the node of the linked list preset had to correlate to the node of the linked list input, and using a for loop to change what position in the linked list each node was at (like a safe lock). This project gave me skills in understanding assembly code to know how programs work under-the-hood.

## base_calculator (not finished)

This function is given two numbers in base 2, 10, or 16 followed by an instruction to do some arithmetic to those numbers (0b10 0b1 subtract would be the input, representing 2-1, and would result in 1). First the base number must be established, which was accomplished my a simple if statement checking if index 1 of the string is a b, h, or represents a base 10 number. Then both numbers must be decoded into a base 10 number, accomplished by sending that number into the appropriate decode function, and the result stored into seperate interger variables. Afterwards the basic arithmetic is applied and the result is printed onto the terminal. While simple this project gave fundamental understandings of how computers can only understand binary numbers. 

## find

Finds determines how many instances of a certain string appears within a text. Fork is used here as an introductory function, it doesn't have any real purpose outside of calling wait and waiting for the other forked version of the program to finish, and then returning the number of times a string appears within a text. Otherwise, this text has to be read in bytes because its too big to be read all at once, meaning this program has to account for the possibility a word starts at the end of a segment and isn't finished until the next segment. Then ensuring that a word is not counted twice. This assignment is a pre-cursor for threadedFind, and exemplifies the speed of a process without using threads. 

## float

Float is given a number (either integer or binary) and this program determines how that number is represented within the internal hardware of the computer. So, this program must establish the exponent value, the mantissa, the sign, and the actual value as an integer. This was accomplished by using a union with uint32_t to determine the decimal value of a number, the integer value of a number, and given that information, how to determine the mantissa and exponent value of the number being represented. This assignment gave me a better understanding of how to represent a floating point number within an operating system.

