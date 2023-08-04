import java.io.*;
import java.util.*;

public class Solution_1225_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int T = 0; T < 10; T++) {
			st = new StringTokenizer(br.readLine());

			int num = 0; // 사이클을 돌면서 마지막원소에 감소시킬 숫자
			int t = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			Deque<Integer> pw = new ArrayDeque<>();

			// enQueue
			for (int i = 0; i < 8; i++) {
				pw.add(Integer.parseInt(st.nextToken()));
			}

			// 큐의 처음 원소가 0이하가 될 때까지 반복
			while (true) {
				// 처음원소에 각 사이클의 숫자를 빼줌
				int first = pw.pollFirst() - (num + 1);
				// 원소가 0보다 작아지면 0을 큐에 넣고 종료
				if (first <= 0) {
					first = 0;
					pw.add(first);
					break;
				}
				// 원소가 0보다 크면 원소를 큐에 넣고 다시 사이클 반복
				pw.add(first);
				num = (num + 1) % 5;
			}
			
			// 출력하기
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for (Integer i : pw) {
				sb.append(i).append(" ");
			}
			System.out.println(sb.toString());

		}
	}

}
