#include <stdio.h>
#define SIZE 2
void initializeBoard(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE; i++){
		for (int j=0; j<=SIZE; j++){
			board[i][j]=' ';
		}
	}
}
void printBoard(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE; i++){
		for (int j=0; j<=SIZE; j++){
			if (i==SIZE)
				printf("%c\n", board[i][j]);
			else
				printf("%c", board[i][j]);
		}
	}
}
int checkWin(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE-2; i++){
		for (int j=0; j<=SIZE-2; j++){
			if (board[i][j]=='X' && board[i+1][j]=='X' && board[i+2][j]=='X')
				return 1;
			else if (board[i][j]=='O' && board[i+1][j]=='O' && board[i+2][j]=='O')
				return 1;
			else if (board[i][j]=='X' && board[i][j+1]=='X' && board[i][j+2]=='X')
				return 1;
			else if (board[i][j]=='O' && board[i][j+1]=='O' && board[i][j+2]=='O')
                                return 1;
			else if (board[i][j]=='X' && board[i+1][j+1]=='X' && board[i+2][j+2]=='X')
				return 1;
			else if (board[i][j]=='O' && board[i+1][j+1]=='O' && board[i+2][j+2]=='O')
				return 1;
		}
	}
	return 0;
}
int isBoardFull(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE; i++){
		for (int j=0; j<=SIZE; j++){
			if (board[i][j]==' ')
				return 0;
		}
	}
	return 1;
}
void getPlayerMove(char board[SIZE][SIZE], int playerNum){
	int row, column;
	printf("input row");
	scanf("%d", &row);
	printf("input column");
	scanf("%d", &column);
	if (board[row][column]==' '){
		if (playerNum==1){
			board[row][column]='X';
			playerNum=0;
		}
		else if(playerNum==0){
			board[row][column]='O';
			playerNum=1;
		}
	}
}
