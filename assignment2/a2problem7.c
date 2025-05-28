#include <math.h>
double getTurnsRatio(int Rs, int R0, int Vs){
	float n=.01;
	double Ps;
	double max;
	while (n<=2.0){
		Ps=Rs*pow((n*Vs)/(n*n*R0+Rs),2);
		if (Ps>max)
			max=Ps;
		n+=.01;
	}
	return max;
}
