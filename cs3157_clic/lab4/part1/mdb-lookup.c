


#include "mdb.h"

int findSize(FILE *fp) { 
        char tempBuf[40];
        int size = 0;
        while(fread(tempBuf, 40, 1, fp) != 0) {
                size++;
                }
        fseek(fp, 0, SEEK_SET); //reset the file pointer to the beginning of the file

        return size;
}

void freeBufs(struct MdbRec **heapPtrArr, int size) {
        int i;
        for(i = 0; i < size; i++) { 
                struct MdbRec *currentPtr = heapPtrArr[i];
                free(currentPtr);
        }
}

void parseFile(FILE *fp, char *filename) {
            
        struct List list;
        initList(&list);
        struct Node *node = NULL;       //initialize to NULL
        //necessary to keep track of the pointers we allocate, so they can be freed later        
        int size = findSize(fp) + 1;    //add the extra 1, because an extra nameBuf is allocated before the while loop exits
        int ptrIndex = 0;
        struct MdbRec *heapPtrArr[size];      

        struct MdbRec *mdb = malloc(sizeof(struct MdbRec));
        if (mdb == NULL) {
                perror(filename);
                exit(1);
                }
        heapPtrArr[ptrIndex++] = mdb;
        while(fread(mdb->name, 16, 1, fp) != 0) { //while the program is still reading successfully
                fread(mdb->msg, 24, 1, fp);
                node = addAfter(&list, node, mdb);

                mdb = malloc(40);
                if (mdb == NULL) {
                        perror(filename);
                        exit(1);
                }
                heapPtrArr[ptrIndex++] = mdb;
        }

        char input[1000];
        char shortInput[6];
        printf("%s", "lookup: ");
        fflush(stdout); 
        while(fgets(input, sizeof(input), stdin) != NULL) {        //returns NULL on EOF 
                strncpy(shortInput, input, 5);
                shortInput[5] = '\0';   //Null terminate the string
              
                int length = strlen(shortInput);
                if(shortInput[length-1] == '\n') {        //if th string is less than 5 characters
                        shortInput[length-1] = '\0';
                        }
                
                int recordNum = 1;
                struct Node *mdbNode = list.head; 
                while(mdbNode) { 
                        struct MdbRec *mdb = (struct MdbRec *)mdbNode->data;
                        if (strstr(mdb->name, shortInput) || strstr(mdb->msg, shortInput)) {
                                printf("%4d: {%s} said {%s}\n", recordNum, mdb->name, mdb->msg);
                        }
                        recordNum++;
                        mdbNode = mdbNode->next;
                }
                printf("\n%s", "Lookup: ");
                fflush(stdout);
        }

        freeBufs(heapPtrArr, size);
        removeAllNodes(&list);

        if(ferror(fp)) { 
                perror(filename);
                exit(1);        
        } 
        fclose(fp);
        exit(0);
}


int main(int argc, char **argv) { 

        if(argc != 2) { 
                fprintf(stderr, "%s\n", "usage: mdb-lookup <file_name>");
                exit(1);
        }

        char *filename = argv[1];
        FILE *fp = fopen(filename, "rb");
        if(fp == NULL) { 
                perror(filename);
                exit(1);
        }

        parseFile(fp, filename);

        return 0;

}
