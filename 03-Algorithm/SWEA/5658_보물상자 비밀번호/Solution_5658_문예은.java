import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5658_문예은 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 보물상자 비밀번호
		/*
		 * 0~F 16진수
		 * 뚜껑 - 시계방향 한칸씩 회전
		 * 전체 숫자 N개
		 * 
		 * N/4 자리 수들 중, K번째로 큰 수를 10진수로 변환하기
		 * (2~7자리수)
		 * 동일한 수가 중복생성될 수 있음, 제외하고 순서 구해야함
		 * 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int pwd = 0; // 16->10진수 변환해서 담을 비밀번호
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 뚜껑 숫자들 + 앞에서부터 (N/4)-1개 숫자 붙이기
			String num = br.readLine();
			num += num.substring(0, N/4-1);
			
			HashSet<Integer> set = new HashSet<>(); // 10진수 변환된 비밀번호숫자들 넣기(중복제거됨)
			// N/4개씩 (+N/4)해가며 숫자 조합 생성 -> 10진수 변환해서 hashset넣기
			for (int i = 0; i < N/4; i++) { // 해당 인덱스부터 조합 시작(한칸씩 회전)
				for (int j = i; j < N; j+=(N/4)) { // N/4 자리수씩 자르기
					pwd = 0;
					for (int k = j; k < j+(N/4); k++) { // 생성된 16진수를 10진수로 변환
						int n = 0;
						if (num.charAt(k) >= 'A' && num.charAt(k) <= 'F') {
							n = num.charAt(k) - 'A' + 10;
						} else {
							n = num.charAt(k) - '0';
						}
						pwd += n*(Math.pow(16, (N/4)-((k-j)%(N/4))-1));
					}
					set.add(pwd);
				}
			}
			// list 변환해서 sort, k번째 수 찾기
			List<Integer> list = new ArrayList<Integer>();
			list.addAll(set);
			Collections.sort(list, Collections.reverseOrder());
			
			sb.append("#"+t+" "+list.get(K-1)+"\n");
		}
		System.out.println(sb.toString());
	}

}
