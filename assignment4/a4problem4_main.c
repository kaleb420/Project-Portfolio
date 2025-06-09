#include <stdio.h>
#include "a4problem4.h"
int main(void){
	char *s="<aaa>bbb</aaa>";
	char **strings=getXMLTagAndContent(s);
	for (int i=0; i<3; i++){
		printf("%s", strings[i]);
	}
}
