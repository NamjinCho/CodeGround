import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int fiboArray[];
	static boolean flag=true;
	public static void main(String[]args)
	{
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine().trim());
		
		
		for(int i = 0; i < testCase; i++) {
			int inputData = Integer.parseInt(scan.nextLine().trim());
			if(inputData==0)
				System.out.println("1 0");
			else
				System.out.println(getFibonachi(inputData-1) + " " + getFibonachi(inputData));
			flag = true;
		}
		
	}
	
	
	
	public static int getFibonachi(int n)
	{
		if(flag){
			flag=!flag;
			fiboArray=new int[n+1];
		
			for(int i=0;i<=n;i++)
				fiboArray[i]=-1;
			}
		return calFibo(n);
	}
	
	public static int calFibo(int n)
	{
		if (n==0) {
	        return 0;
	    } else if (n==1) {
	        return 1;
	    } else {
	    	if((n-1 >= 0 && n-2 >= 0)&& (fiboArray[n-1] != -1 && fiboArray[n-2]!=-1) )
	    		return fiboArray[n-1]+fiboArray[n-2];
	    	else
	    	{
	    		if(( n-2 >= 0)&& (fiboArray[n-2]!=-1))
	    			return fiboArray[n-2]+calFibo(n-1);
	    		else if(( n-1 >= 0)&& (fiboArray[n-1]!=-1))
	    			return fiboArray[n-2]+calFibo(n-1);
	    		else
	    			return calFibo(n-1)+calFibo(n-2);
	    	}
	    }
	}
	
}
