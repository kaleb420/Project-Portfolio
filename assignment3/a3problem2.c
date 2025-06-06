#include <stdio.h>
#define SIZE 2
void initializeBoard(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE; i++){
		for (int j=0; j<=SIZE; j++){
			board[i][j]=' '; // loop through every combination of rows and columns to fill the board with blank spaces
		}
	}
}
void printBoard(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE; i++){
		for (int j=0; j<=SIZE; j++){
			if (j==SIZE)
				printf("%c\n", board[i][j]); // similar to the last comment, loop through every combination of rows and columns to print if the space is empty or filled, j==SIZED is used to print a new line if the function is at the end of the column for ease of reading
			else
				printf("%c", board[i][j]);
		}
	}
}
int checkWin(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE-2; i++){
		for (int j=0; j<=SIZE-2; j++){ // similar to the previous comments, loop through all rows and columns to check the values
			if (board[i][j]=='X' && board[i+1][j]=='X' && board[i+2][j]=='X') // this and the next else if checks for horizontal wins
				return 1;
			else if (board[i][j]=='O' && board[i+1][j]=='O' && board[i+2][j]=='O')
				return 1;
			else if (board[i][j]=='X' && board[i][j+1]=='X' && board[i][j+2]=='X') // this and the next else if checks for vertical wins 
				return 1;
			else if (board[i][j]=='O' && board[i][j+1]=='O' && board[i][j+2]=='O')
                                return 1;
			else if (board[i][j]=='X' && board[i+1][j+1]=='X' && board[i+2][j+2]=='X') // this and the next else if checks for top left to bottom right diagonal wins
				return 1;
			else if (board[i][j]=='O' && board[i+1][j+1]=='O' && board[i+2][j+2]=='O') 
				return 1;
			else if (board[i][j]=='X' && board[i-1][j-1]=='X' && board[i-2][j-2]=='X') // this and the next else if checks for bottom left to top right diagonal wins
				return 1;
			else if (board[i][j]=='O' && board[i-1][j-1]=='O' && board[i-2][j-2]=='O')
				return 1;
		}
	}
	return 0;
}
int isBoardFull(char board[SIZE][SIZE]){
	for (int i=0; i<=SIZE; i++){
		for (int j=0; j<=SIZE; j++){
			if (board[i][j]==' ') // loop through all columns and rows and if all of the spots are filled declare a draw
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
	scanf("%d", &column); // scan for player's inputs
	if (board[row][column]==' '){
		if (playerNum==1){ // x starts so it can be assumed they inputted the rows and columns, afterwards set the playernumber to 0 so O goes next
			board[row][column]='X'; 
			playerNum=0;
		}
		else if(playerNum==0){ // same as last but replace the assumption with O
			board[row][column]='O';
			playerNum=1;
		}
	}
}
