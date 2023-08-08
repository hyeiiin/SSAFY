import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14w-rKAHACFAYD
public class Solution {  // SWEA_1228
	static int TC = 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
//		= new StringTokenizer(br.readLine());

		for (int tc = 1; tc < TC + 1; tc++) { // TC
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			List<String> lst = new ArrayList<String>();  // 원본 암호문

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				lst.add(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());  // 명령어 개수

			String[] str = br.readLine().split("I");

			// 명령어 처리
			for (int i = 1; i < M + 1; i++) {
				st = new StringTokenizer(str[i]);
				while (st.hasMoreTokens()) {
					int start = Integer.parseInt(st.nextToken());  // 앞에서부터 start의 위치 바로 다음에,
					int end = Integer.parseInt(st.nextToken());  // end개의 숫자를 삽입
					List<String> temp = new ArrayList<String>();  // 덧붙일 숫자들
					
					for (int j = 0; j < end; j++) {
						temp.add(st.nextToken());
					}
					
					lst.addAll(start, temp);
				}
			}
			
			
			// Print
			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");
			
			for (int i = 0; i < 10; i++) {
				sb.append(lst.get(i)).append(" ");
			}
			
			System.out.println(sb.toString());

		} // TC
	}

}
