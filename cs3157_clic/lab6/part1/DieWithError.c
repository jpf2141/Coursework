#include <stdio.h>  /* for perror() */
#include <stdlib.h> /* for exit() */

void DieWithError(char *errorMessage)
{
    perror(errorMessage);
    exit(1);
}

void die(char *errorMessage){
        fprintf(stderr, "%s\n", errorMessage);
        exit(1); 
}  /* Error handling function */
