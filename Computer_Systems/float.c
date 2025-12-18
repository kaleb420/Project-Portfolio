#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <math.h>

typedef union{
	float f;
	uint32_t i;
} data;

data constructor(void){
	data d;
	d.f=0;
	d.i=0;
	return d;
}

float math_pow(int base, int power){
        float total=1.0;
        if (power>=0){
                for (int i=0; i<power; i++){
                        total*=base;
                }
        }
        else {  
                for (int i=power; i<0; i++){
                        total*=base;
                }
                return 1/total;
        }
        return total;
}

uint32_t mask_exponent(uint32_t value){
	int mask=255;
	mask=mask << 23;
	return (mask & value) >> 23;
}

float mask_mantissa(uint32_t value){
	int mask=8388607;
	data d=constructor();
	d.i=mask & value;
	d.i= d.i | (127 << 23);
	return d.f;
}

void binary_constructor(uint32_t value, char *output){
	for (int i=0; i<32; i++){
		if ((value >> (31-i)) & 1)
			output[i]='1';
		else 
			output[i]='0';
	}
	output[32]='\0';
}

void substring(char *str, int start, int end, char *output){
	for (int i=start; i<end; i++){
		output[i-start]=str[i];
	}
}

int binary_to_exponent(char *value){
	int temp=0;
	for (int i=0; i<8; i++){
		temp+=(value[i]-48)*math_pow(2,7-i);
	}
	return temp-127;
}

float binary_to_mantissa(char *value){
	float temp=0.0;
	for (int i=0; i<23; i++){
                temp+=(value[i]-48)*math_pow(2,-i-1);
	}
	return temp+1;
}

int main(int argc, char *argv[]){
	data d=constructor();
	char binary[33];
	int sign=1; 
	int exponent=0;
	char c_exponent[9];
	char c_mantissa[23];
	float mantissa=0.0; 
	float value=0.0; 
	if ((argv[1][1]=='b' && argv[2][0]=='1') || argv[2][0]=='-')
		sign=-1;
	if (argv[1][1]=='b'){
		substring(argv[2], 1, 9, c_exponent);
		substring(argv[2], 9, 32, c_mantissa);
		exponent=binary_to_exponent(c_exponent);
		mantissa=binary_to_mantissa(c_mantissa);
	        value=sign*mantissa*math_pow(2,exponent);
		if (exponent==-127 && mantissa==1){
			exponent=-126;
			mantissa=0;
			value=0;
		}
		else if (exponent==128 && mantissa==1) // infinity
			value=1.0/0.0;
		else if (exponent==128 && mantissa!=0){ // nan
			if (sign==1)
				value=NAN; // not sure if we were allowed to use this from math.h but couldn't figure out how else to set the number to positive nan
			else 
				value=-NAN;
		}
		else if (exponent==-127 && mantissa!=0){ // denormalized
			exponent=-126;
			mantissa-=1;
			value=sign*mantissa*math_pow(2,exponent);
		}
		printf("Binary: %s\n", argv[2]);
	}
	else{
		value=strtof(argv[2], NULL);
		d.f=value;
		exponent=mask_exponent(d.i)-127;
		mantissa=mask_mantissa(d.i);
		binary_constructor(d.i, binary);
		if (exponent==-127){ // denormalized
			exponent=-126;
			mantissa-=1;
		}
		if (exponent==0 && mantissa!=0) // zero
			exponent=-126;
		printf("Binary: %s\n", binary);
	}
	if (sign==1)
		sign=0;
	else if (sign==-1)
		sign=1;
	printf("Sign: %d\n", sign);
	printf("Exponent: %d\n", exponent);
	printf("Mantissa: %.7g\n", mantissa);
	printf("Value: %.7g\n", value);
}
