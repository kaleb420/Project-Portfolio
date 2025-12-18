#include <stdio.h>
#include <stdlib.h>
#include "a4problem4.h"
int main(void){
	char *s="<Book>The Restaurant at the End of the Universe</Book>";
	char **string=getXMLTagAndContent(s);
	for (int i=0; i<3; i++){
		printf("%s\n", string[i]);
	}
	free(string);
}
