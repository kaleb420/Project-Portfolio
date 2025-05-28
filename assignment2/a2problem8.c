#include <stdio.h>
#include <stdlib.h>
int get_current_cap(void){
	static int current;
	return current;
}
int get_max_cap(void){
	static int max;
	return max;
}
int enter(int n){
	if (n+get_current_cap()<=get_max_cap())
		return 0;
	else
		return 1;
}
void leave(int n){
	if (get_current_cap()-n>=0)
		return;
	else if (get_current_cap()-n<0)
		return;
}
void restaurantSim(int C, int N, int B, int T){
	int time_passed=0;
	int group_size;
	int random_time_interval;
	get_current_cap();
	get_max_cap();
	while (time_passed<=T){
		group_size=rand()%N;
		if (rand()%2==0)
			return leave(group_size);
		else if (rand()%2==1)
			return;
		random_time_interval=rand()%B+1;
		time_passed+=random_time_interval;
	}
	return;
}
