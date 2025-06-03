#include <stdio.h>
#include "a3problem2.h"
#define SIZE 2
int main(void){
	int playerNum;
	int counter;
	char board[SIZE][SIZE];
	initializeBoard(board);
	while (checkWin(board)!=1){
		if (isBoardFull(board)==1)
			break;
		printBoard(board);
		if (counter%2==0){
			playerNum=1;
			counter++;
		}
		else if (counter%2==1){
			playerNum=0;
			counter++;
		}
		getPlayerMove(board, playerNum);
	}
	if (isBoardFull(board)==1)
		printf("Draw");
	else if (playerNum==1)
		printf("Player O Wins");
	else if (playerNum==0)
		printf("Player X Wins");
}
