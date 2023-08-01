package BOJ;

import java.io.*;
import java.util.*;

public class Main_15649_김수린 {
	static int N, M;
	static int []num;
	static boolean []isSelected;
	static int tc;
	
	public static void recursionPermutation(int cnt) {
		if (cnt == N) {
//			System.out.println(Arrays.toString(num));
			for(int i : num) {
				System.out.print(i != 0 ? i + " " : "");
			}
			System.out.println();
			return;
		} else {
			for(int i = 1; i <= N; i++) {
				if(isSelected[i - 1]) continue;
				num[cnt] = i;
				isSelected[i - 1] = true;
				recursionPermutation(cnt + 1);
				isSelected[i - 1] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		isSelected = new boolean[N];
		recursionPermutation(N - M);
	}
}
