/*
 * twecho
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
char * capWord(char * currentArg);


static char **duplicateArgs(int argc, char **argv)
{
        int memorySize = argc*sizeof(char*) + 8;
        char **p;
        p = malloc(memorySize);         //allocate space for the array of char pointers
                if(p == NULL) {         //(the array that contains the pointers to each string)
                        perror("malloc returned NULL");
                        exit(1);
                }

        int k; 
        for(k = 0; k < argc; k++) {
                char * word = capWord(argv[k]);
                p[k] = word;
        }
        p[argc] = '\0';
        return p;
}



static void freeDuplicatedArgs(char **copy)
{
        int i = 0; 
        while(copy[i] != '\0') {
                free(copy[i]);
                i++;
        }
        free(copy);
}

char * capWord(char * currentArg) {
        int i;
        unsigned long charArrayLength = strlen(currentArg) + 1;
        int memorySize = charArrayLength *sizeof(char) + 1; 
        char *charArray; 
        charArray = malloc(memorySize);
        if(charArray == NULL) {                
                perror("malloc returned NULL");
                exit(1);
        }
        for(i = 0; i < charArrayLength; i++) { 
                charArray[i] = toupper(currentArg[i]);    
        }
      
        return charArray;
}
/*
 * DO NOT MODIFY main().
 */
int main(int argc, char **argv)
{
    if (argc <= 1)
        return 1;

    char **copy = duplicateArgs(argc, argv);
    char **p = copy;

    argv++;
    p++;
    while (*argv) {
        printf("%s %s\n", *argv++, *p++);
    }

    freeDuplicatedArgs(copy);

    return 0;
}
