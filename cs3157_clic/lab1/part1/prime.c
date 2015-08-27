#include <stdio.h>
#include "prime.h"


int prime(int x) {

    int i;

    for(i = 2; i <= (x-1); i++) {
        if(x % i == 0) {
                return 0;
                }        
        }
   return 1;
}


