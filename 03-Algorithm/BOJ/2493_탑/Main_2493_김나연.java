import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_김나연 {
	
	static int n, temp;
	static int[] a, ret;
	
	public static void main(String[] args) throws IOException {
		Stack<Integer> st = new Stack<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer sto = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(sto.nextToken());
		
		a=new int[n];
		ret=new int[n];
		
		sto = new StringTokenizer(br.readLine());
		for (int i = 0; i < n ; i++) {
			a[i] = Integer.parseInt(sto.nextToken());
		}
		
		for (int i = n-1; i >= 0; i--) {
			while(!st.empty()&&a[st.peek()]<a[i]) {
				ret[st.peek()]=i+1;
				st.pop();
			}
			
			st.push(i);
		}
		
		StringBuilder sb=new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			sb.append(ret[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
