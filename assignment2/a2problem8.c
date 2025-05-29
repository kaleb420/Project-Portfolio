#include <stdio.h>
#include <stdlib.h>
int get_current_cap(void){
	static int current; // create a static value to establish the current number of guests 
	return current;
}
int get_max_cap(void){
	static int max; // create a static value to establish the maximum number of guests 
	return max;
}
int enter(int n){
	if (n+get_current_cap()<=get_max_cap()) // add the number of guests to the current amount and check if it goes over the limit 
		return 0;
	else
		return 1;
}
void leave(int n){
	get_current_cap()-n; // subtract the guests leaving the restaurant 
	return;
}
void restaurantSim(int C, int N, int B, int T){
	int time_passed=0;
	int group_size;
	int random_time_interval;
	get_current_cap(); // run functions to establish a baseline, but I honestly don't know if this does anything
	get_max_cap();
	while (time_passed<=T){ // establish a loop to check if the time has passed T
		group_size=rand()%N;
		if (get_current_cap()==C) // if the restaurant is at max capacity ensure the random number generated will be for guests leaving
			return leave(group_size); 
		else if (rand()%2==0) // random number generator to establish if the guests should leave 
			return leave(group_size);
		else if (rand()%2==1) // random number generator to establish if the guests should enter
			return;
		random_time_interval=rand()%B+1; // establish a random time interval
		time_passed+=random_time_interval; 
	}
	return;
}
