import java.util.Scanner;

public class MakeChange { 
	static int quarterval = 25;
	static int dimeval = 10; 
	static int nickelval = 5; 
	
	public static void main(String[] args) {
		
		Scanner enteredInput = new Scanner(System.in);
		//user prompted
		System.out.println("Enter Amount to be changed: ");
		int enteredAmount = enteredInput.nextInt();
		if (enteredAmount%5 != 0) {	//if entered amount is not divisible by 5
			System.out.print(enteredAmount +" cannot be changed");
			return;
		}
		else 
			changeMaker(enteredAmount, 0, 0);
	}
	
public static void changeMaker(int total, int quarterNUM, int dimeNUM){
	
		//display coin combinations 
		for(int i = 1; i <= quarterNUM; i++)
			System.out.print(quarterval + " ");
		for(int i = 1; i <= dimeNUM; i++)
			System.out.print(dimeval + " ");
		for(int i = 1; i <= total/nickelval; i++)
			System.out.print(nickelval +" ");
		System.out.print("\n");
	
		//if the total is greater than 10, decrement total towards base case 
		//increment number of dimes
		//call changeMaker with updated total, number of quarters, dimes 
	if(total >= dimeval) { 
		changeMaker(total - 10, quarterNUM, dimeNUM + 1);
	}
	
	int dimeMoney = dimeNUM*dimeval;	//total amount of money in dimes left
	//if total is less than 5 AND there is more money (in dimes) than 
	//the total remaining minus 25, call changemaker 
	if(total <= 5 && dimeMoney >= 25 - total)
		changeMaker(((dimeMoney + 1) -25), quarterNUM + 1, 0);		
	}
}