package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo
 * 
 * @author SSAFY
 *
 */
public class Solution_5658_박정인 {
	static int N, K;
	static Set<Integer> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			String str = br.readLine();
			char[] arr = str.toCharArray();
			set = new HashSet<>();

			// 회전
			for (int i = 0; i < N / 4; i++) {
				// 숫자이동
				char tmp = arr[N - 1];
				for (int j = N - 1; j > 0; j--) {
					arr[j] = arr[j - 1];
				}
				arr[0] = tmp;		
				
				// pw를 set에 저장
				for (int j = 0; j < N; j += N / 4) {
					String num = "";
					for (int k = 0; k < N / 4; k++) {
						num += arr[j + k];
					}
					
					set.add(Integer.parseInt(num, 16));
				}
				

			}

			// 내림차순으로 정렬
			List<Integer> list = new ArrayList<>(set);
			Collections.sort(list, Collections.reverseOrder());

			// K번째 수
			sb.append("#").append(tc).append(" ").append(list.get(K - 1)).append("\n");
		}

		System.out.println(sb);
	}
}
