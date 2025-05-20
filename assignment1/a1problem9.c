#include <stdio.h>
int main(void){
	float x;
	float y;
	float alpha;
	float beta;
	float delta;
	float gamma;
	float dt;
	int N;
	float dx;
	float dy;
	int iterations;
	int time;
	x=40;
	y=9;
	alpha=.1;
	beta=.02;
	delta=.01;
	gamma=.1;
	dt=.01;
	N=1000;
	iterations=1;
	time=0;
	while (iterations!=N){
		dx=(alpha*x-beta*x*y)*dt;
		dy=(-gamma*y+delta*x*y)*dt;
		x=x+dx;
		y=y+dy;
		time=time+dt;
		printf("Time step: %.3d, Prey (x): %.3f, Predators (y): %.3f\n", iterations, x, y);
		iterations++;
	}
}
