import java.util.Scanner;

public class Main_2023_김나연 {

    static int n;
    
    static boolean isPrime(int num) {
    	if(num<2) return false;
	    for (int i = 2; i * i <= num; i++) {
	    	if(num%i==0) return false;
	    }
	    return true;
    }
    
    static void go(int num, int cnt) {
    	if(cnt==n && isPrime(num)) {
    		System.out.println(num);
    		return;
    	}
    	
    	for(int i=0;i<10;i++) {
    		int temp=Integer.parseInt(Integer.toString(num)+Integer.toString(i));
    		if(isPrime(temp)) go(temp, cnt+1);
    	}
    	
    }

	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    n=in.nextInt();
	    
	    go(0,0);
	}
}
