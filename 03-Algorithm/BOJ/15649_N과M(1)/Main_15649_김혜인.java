import java.util.*;
import java.io.*;

public class Main_15649_김혜인 {

	public static int[] numbers;
	public static boolean[] isSelected;
	static int n;
	static int m;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		isSelected = new boolean[n+1]; 
		numbers = new int[m];
		perm(0);
					
	}

	private static void perm(int cnt) {
		if(cnt==m) {
			
			for(int i=0; i<numbers.length; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();			
		}
		
		else {
			for(int i=1; i<=n; i++) {
				if(isSelected[i])
					continue;
				numbers[cnt] = i;
				isSelected[i] = true;
				perm(cnt+1);
				isSelected[i] = false;
			}
		}
		
	}
}
