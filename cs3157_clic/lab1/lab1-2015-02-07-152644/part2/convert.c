#include <stdio.h>

void output(int value);
void decimalToBinary(int value);

int main()


{
        int value;
        scanf("%d", &value);       
        output(value);
               
        return 0;
}


void output(int value) {

        printf("signed dec:     %d\n", value);
        printf("unsigned dec:   %u\n", value);
        printf("hex:            %x\n", value);
        printf("binary:         ");
        
       int i;
       int bitShifted;
       for(i = 31 ;i >= 0; i--) {
                bitShifted = value >> i;//the decimal's bitwise representation
                                        //is shifted all the way to the right
                                        //so that the representation becomes
                                        //00000....0 or 000000....1
               
                if(bitShifted & 1) {    //this statement asks: is the LSB
                        printf("1");    //1; if yes, bitwise AND returns true
                }  
                else {
                        printf("0");
                }
                if(i % 4 == 0) {
                        printf(" ");
                }
        }
        printf("\n");   
}




