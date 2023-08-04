package practice;

import java.io.*;
import java.util.*;

public class Solution_1225_이세은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트 케이스 10개 고정
			sb = new StringBuilder(); //sb 초기화
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new LinkedList<>(); // 큐 생성
			for (int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken())); // 8개 숫자 모두 넣기
			}
			int minus = 1;
			int newN = 0;
			do {
				if(minus==6) //5까지 감소 수행 후 6 되었다면 1로 초기화
					minus=1;
				newN = q.poll() - (minus++); // 순번의 수 poll 후 감소시키기
				if(newN<0)
					newN=0;
				q.add(newN); // 감소시킨 값 뒤로
			} while (newN > 0); // (뒤로 간)감소한 값이 0보다 작거나 같아지는 경우 끝내기

			sb.append("#" + test_case + " ");
			for (Integer integer : q) {
				sb.append(integer + " ");
			}
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		br.close();
		bw.close();
	}

}
