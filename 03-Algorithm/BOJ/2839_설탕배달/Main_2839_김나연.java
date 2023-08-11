import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_김나연 {
	
	static int n, bag3, bag5;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n=Integer.parseInt(br.readLine());
		
		int flag=0;
		
		while(n>=0) {
			if(n==0) {
				flag=1;
				break;
			}
			else if(n>0 && n%5==0) {
				flag=1;
				bag5=n/5;
				break;
			}
			
			n=n-3;
			bag3++;
		}
		
		if(flag==0) sb.append("-1");
		else sb.append(bag5+bag3);
		
		System.out.println(sb);
	}

}
