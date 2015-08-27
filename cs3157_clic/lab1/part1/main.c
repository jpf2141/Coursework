#include <stdio.h>


int userPrompt();
int average(int a, int b);
int prime(int x);
int gcd(int x, int y);
void primeOut(int x, int y);
void gcdOut(int x, int y, int z);


int main(void) {

        int a;
        int b;

        //prompt the user for number inputs
        printf("Enter numbers to calculate the average!\n\n");
        a = userPrompt();
        b = userPrompt();
        
        average(a, b);
        primeOut(a, prime(a));
        primeOut(b, prime(b));
        
        gcdOut(gcd(a,b), a, b);

        //printf("You typed in %d and %d\n", a, b); 

        return 0;
}

//this method asks the user to enter an integer value
//it is called whenever input is required for the program
int userPrompt() {
        
        int value;
        printf("Please input an integer value: ");
        scanf("%d", &value);
        return value;
}

int average(int a, int b) {
        float sum = (a + b);
        float average = sum/2;
        printf("The average is: %f\n", average);
        return 0;
}

void primeOut(int x, int y) {
        if(y == 0) {
                printf("%d is not a prime number!\n", x);
        }
        else {
                printf("%d is a prime number!\n", x);
        } 
        return;
}

void gcdOut(int x, int y, int z) {
        if(x == 1) {
            printf("%d and %d are relatively prime numbers!\n", y, z);
        }
        else {
            printf("%d and %d are not relatively prime numbers!\n", y, z);
        }
}




