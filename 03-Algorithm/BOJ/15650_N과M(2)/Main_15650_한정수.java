import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15650_한정수 {
	
	static int n;
	static int m;
	static int[] num_result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//3 1 받고
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); //3   n C m
		m = Integer.parseInt(st.nextToken()); //1
		num_result = new int[m];
		comb(0, 1);
	}
	
	private static void comb(int cnt, int start) { //조합
		if (cnt == m) {
			for (int i=0 ; i<m ; i++) {
				System.out.print(num_result[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i<= n ; i++) {
			num_result[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

}


