#include <math.h>
double getTurnsRatio(int Rs, int R0, int Vs){
	float n=.01;
	double Ps;
	double max=0;
	while (n<=2){
		Ps=Rs*pow((n*Vs)/(n*n*R0+Rs),2);
		if (Ps>max) // establish if the new value of Ps is more than the previous max value, if so set that value of Ps to the max value
			max=Ps;
		n+=.01;
	}
	return max;
}
