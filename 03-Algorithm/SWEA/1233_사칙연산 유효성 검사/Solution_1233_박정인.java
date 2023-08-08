package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1233_박정인 {

	static String operations = "+-*/";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			int result = 1;
			
			for (int i = 1; i <= N; i++) {
				String str = br.readLine();
				
				String[] arr = str.split(" ");
				if (arr.length == 4 && !operations.contains(arr[1])) {
					result = 0;					
				}
				
				if (arr.length == 2 && operations.contains(arr[1])) {
					result = 0;					
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}	
}
