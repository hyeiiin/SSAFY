package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1228_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());	// 원본 암호문 크기
			List<Integer> arr = new ArrayList<>();		// 암호문을 만들 list
			
			st = new StringTokenizer(br.readLine(), " ");	// 공백만큼 잘라서 토큰화
			for(int i=0; i<N; i++) {
				arr.add(Integer.parseInt(st.nextToken()));	// 원본 암호 list
			}
			
			int cmd = Integer.parseInt(br.readLine());	// 명령어 개수
			
			st = new StringTokenizer(br.readLine(), "I ");	// I 씩 짤라서 토큰화
			
			for(int i=0; i<cmd; i++) {	// 명령어 개수만큼 for문
				int start = Integer.parseInt(st.nextToken());	// 시작 지점
				int end = Integer.parseInt(st.nextToken());	// 끝 지점
				for(int j=start; j<start+end; j++) {	// 시작부터 끝까지 리스트에 insert
					arr.add(j, Integer.parseInt(st.nextToken()));
				}
			}
			
			System.out.printf("#%d ", tc);
			for(int i=0; i<10; i++) {
				System.out.print(arr.get(i)+" ");	// 10개 출력
			}
			System.out.println();
		}
		

	}

}
