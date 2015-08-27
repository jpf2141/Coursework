#include "gcd.h"


int gcd(int x, int y) {

        int temp = 0;

        if(x < y) {
                temp = x;
                x = y;
                y = temp;
        }
        while(y != 0) {
                temp = x % y;
                x = y;
                y = temp;
        }
        return x;
}
