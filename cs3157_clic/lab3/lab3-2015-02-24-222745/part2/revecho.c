#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <mylist.h>


static void printArg(void *p)
{
        printf("%s\n", (char *)p);
}


int main(int argc, char **argv) {

        // initialize list
        struct List list;
        initList(&list);
        
        //populate linked list with the command line args
        argv++;
        while(*argv) {
               addFront(&list, *argv++);
        }

        traverseList(&list, &printArg);
        printf("\n");
        void *dataSought = "dude";
        if(findNode(&list, dataSought, (int (*)(const void *, const void *))(strcmp))) {
                printf("dude found\n\n");
        }
        else {
                printf("dude not found\n\n");
        }
        removeAllNodes(&list);
        return 0;
}






