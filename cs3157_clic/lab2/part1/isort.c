#include <stdio.h>
#include <stdlib.h>
#include <time.h>



int * fillArray(int arraySize);
int * ascSort(int arraySize, int * pointer);
int * descSort(int arraySize, int * pointer);
void printArray(int arraySize, int * pointer);
void sort_integer_array(int * begin, int * end, int ascending);


int main() {
        
        
        printf("%s", "Please enter the array size: \n");
        int input;      //input variable
        scanf("%d", &input);    //scan for user input

        int arraySize = input;
        
        int * randArrayPointer = fillArray(arraySize); 
        printf("Original Array:\n");
        printArray(arraySize, randArrayPointer);        
        int * ascArrayPointer = ascSort(arraySize, randArrayPointer);
        int * descArrayPointer = descSort(arraySize, randArrayPointer);

        //printArray(arraySize, ascArrayPointer);
        //printArray(arraySize, descArrayPointer);
        
        free(randArrayPointer);
        free(ascArrayPointer);
        free(descArrayPointer);
        return 0;
}


int * fillArray(int arraySize) {

        int memorySize = arraySize*sizeof(int); //number of bytes to allocate for array  
        int *p;         //pointer to array start
        p = malloc(memorySize);
                if (p == NULL) {
                        perror("malloc returned NULL");
                        exit(1); 
                }
                
        int i;
        srandom(time(NULL));     //seed the random number generator   
        for(i = 0; i < arraySize; i++) {
                p[i] = random() % 100;
        }
        return p;
}


int * ascSort(int arraySize, int * randArrayPointer) { 
    
        int memorySize = arraySize*sizeof(int);

        int *p;
        p = malloc(memorySize);
                if (p == NULL) {
                        perror("malloc returned NULL");
                        exit(1); 
                }
        
        //copy array
        int i; 
        for(i = 0; i < arraySize; i++) { 
            p[i] = randArrayPointer[i];
        }

        sort_integer_array(p, p + arraySize, 1);
        return p;
}


int * descSort(int arraySize, int * randArrayPointer) { 
    
        int memorySize = arraySize*sizeof(int);

        int *p;
        p = malloc(memorySize);
                if (p == NULL) {
                        perror("malloc returned NULL");
                        exit(1); 
                }
        
        //copy array
        int i; 
        for(i = 0; i < arraySize; i++) { 
            p[i] = randArrayPointer[i];
        }
        
        sort_integer_array(p, p + arraySize, 0);
        return p;
}

/* This function sorts an integer array.
 * begin points to the 1st element of the array.
 * end points to ONE PAST the last element of the array.
 * If ascending is 1, the array will be sorted in ascending order.
 * If ascending is 0, the array will be sorted in descending order.
 */
void sort_integer_array(int * begin, int * end, int ascending)
{
/* In here, you will call your real sorting function (your own
* or the qsort()).  Basically, I want to make sure that you
* know how to translate the begin/end parameter to whatever
* is required for your sorting function.
* bubblesort code is taken from wikipedia; SOURCE:
* http://en.wikibooks.org/wiki/Algorithm_Implementation/Sorting/Bubble_sort#C_1
*/      
        
        //take the difference in pointer locations to find the 
        //length of the array
        int length = end - begin; 
     
        int i;
        int j;
        int tmp;

        if(ascending == 1) { 
                printf("Array in ascending order:\n");
                for(i = 0; i < length - 1; i++) {
                        for(j = 0; j < length - i - 1; j++) {
                                if(begin[j] > begin[j+1]) {
                                        tmp = begin[j];
                                        begin[j] = begin[j + 1];
                                        begin[j + 1] = tmp;
                                }              
                        }
                }
                
        }

        else {
                printf("Array in descending order:\n");
                for(i = 0; i < length - 1; i++) {
                        for(j = 0; j < length - i - 1; j++) {
                                if(begin[j] <  begin[j+1]) {
                                        tmp = begin[j];
                                        begin[j] = begin[j + 1];
                                        begin[j + 1] = tmp;
                                }              
                        }
                }
               
        }   
        printArray(length, begin);     
}
 
        
void printArray(int arraySize, int * arrayPointer) { 

    int i; 
    for(i = 0; i < arraySize; i++) { 
        printf("%d\n", arrayPointer[i]);
    }
}

