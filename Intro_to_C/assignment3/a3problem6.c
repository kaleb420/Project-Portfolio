#include <stdio.h>
#define NUM_ROWS 256
#define NUM_COLS 256
void zero_blue(int img[NUM_ROWS][NUM_COLS][3]){
	for (int i=0; i<NUM_ROWS; i++){
		for (int j=0; j<NUM_COLS; j++){
			if (img[i][j][2]>0)
				img[i][j][2]-=img[i][j][2];
		}
	}
}
void invert(int img[NUM_ROWS][NUM_COLS][3]){
	for (int i=0; i<NUM_ROWS; i++){
		for (int j=0; j<NUM_COLS; j++){
			for (int k=0; k<3; k++){
			img[i][j][k]=255-img[i][j][k];
			}
		}
	}
}
void rotate_clockwise(int img[NUM_ROWS][NUM_COLS][3]){
	int newrow;
	int newcol;
	for (int row=0; row<NUM_ROWS; row++){
		for (int col=0; col<NUM_COLS; col++){
			newrow=col;
			newcol=NUM_ROWS-1-row;
			img[row][col][2]=img[newrow][newcol][2];
		}
	}
}	
