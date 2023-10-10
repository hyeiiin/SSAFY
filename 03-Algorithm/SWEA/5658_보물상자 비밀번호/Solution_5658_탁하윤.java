package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5658_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	// tc 개수
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 숫자의 개수
			int K = Integer.parseInt(st.nextToken());	// 크기 순서 
			
			String str = br.readLine();	// N개의 숫자들
			LinkedList<Character> nums = new LinkedList<>();	// 숫자들 담을 리스트
			for (int i = 0; i < N; i++) {
				nums.add(str.charAt(i));
			}
			
			// 0회전 값 10진수로 바꿔서 리스트에 담기
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i+=N/4) {	// 각 변 길이만큼 증가
				String num = "";
				for (int j = i; j < i+N/4; j++) {	// 각 변 길이의 숫자들 합치기
					num += nums.get(j);
				}
				int decimal = Integer.parseInt(num, 16);	// 16진수->10진수
				if(list.contains(decimal) == false) {	// 값이 list에 없다면 list에 추가
					list.add(decimal);
				}
			}
			
			int rot = N/4;	// 회전 횟수
			for (int i = 1; i < rot; i++) {
				// 맨 뒤의 수 앞으로 옮기기
				char c = nums.getLast();
				nums.removeLast();
				nums.addFirst(c);
				
				// i 회전 값 10진수로 바꿔서 리스트에 담기
				for (int k = 0; k < N; k+=N/4) {
					String num = "";
					for (int j = k; j < k+N/4; j++) {
						num += nums.get(j);
					}
					int decimal = Integer.parseInt(num, 16);
					if(list.contains(decimal) == false) {
						list.add(decimal);
					}
				}				
			}
			
			Collections.sort(list, Collections.reverseOrder());	// 내림차순 정렬
			sb.append("#").append(tc).append(" ").append(list.get(K-1)).append("\n");
		}
		System.out.println(sb.toString());
	}

}
