package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1228_전상혁 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		LinkedList<Integer> numbers = new LinkedList<>();
		
		for (int tc = 1; tc <= 10; tc++) {
			//첫번째 줄: 원본 암호문의 길이
			int N = Integer.parseInt(br.readLine());
			
			//두번째 줄: 원본 암호문
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {

				numbers.add(Integer.parseInt(st.nextToken()));
			}
			//세번째 줄: 명령어의 개수
			int M = Integer.parseInt(br.readLine());
			
			//네번째 줄: 명령어
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				if (st.hasMoreTokens()) {
					String str = st.nextToken(); //구분 ㅣ 
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					
					//y개의 숫자를 x번째 값 바로 다음에 삽입
//					System.out.println(x);
					for (int j = 0; j < y; j++) {
						numbers.add(x, Integer.parseInt(st.nextToken()));
						x++;
					}
					
//					System.out.println(numbers);
				}
			}
			System.out.printf("#%d ",tc);
			for (int i = 0; i < 10; i++) {
				System.out.print(numbers.get(i) + " ");
			}
			System.out.println();
			numbers.clear();
			
		}
	}

}
