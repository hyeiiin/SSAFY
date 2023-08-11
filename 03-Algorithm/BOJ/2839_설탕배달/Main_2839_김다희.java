import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int x=n/5;
		if(n%5==0) System.out.println(x);
		else if(n % 5 == 3|| (n % 5 == 1 && n > 5)) System.out.println(x+1);
		else if (n % 5 == 4 && n > 5||(n%5==2&&n>10)) System.out.println(x+2);
		else System.out.println(-1);
	}
}
