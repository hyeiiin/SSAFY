import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {  // 2839
	static StringTokenizer st;
	static StringBuilder sb;
	static int N; // 설탕의 무게
	static int res = Integer.MAX_VALUE;  // 최종값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		// 구하려는 N을 설탕의 최소 무게로 나눔.
		// = 설탕 봉지 최대 개수이므로, DP[][]의 크기를 정할 수 있음.
		int size = N / 3 + 1;

		// three = 3kg를 넣은 개수, five = 5kg를 넣은 개수
		for (int three = 0; three < size; three++) {
			for (int five = 0; five < size; five++) {
				int temp = (three * 3) + (five * 5);  // 현재 각각 선택된 개수.
				if (temp > N) { // 구하려는 수 보다 크다면 필요없음.
					break;
				} else { // 구하려는 수 보다 작거나 같으면
					// END. 정확한 무게 일치 = 개수의 합이 최소로 결과값 갱신
					if (temp == N) {
						res = Math.min(res, three + five);
					}
				}
			} // for
		} // for

		sb = new StringBuilder();
		
		// 1. res가 초깃값 그대로다 : 정확하게 Nkg로 나눌 수 없다.
		if (res == Integer.MAX_VALUE) {
			sb.append(-1);
		} else {  // 2. 아닐 경우 최종 갱신된 res 반환.
			sb.append(res);
		}
		System.out.println(sb);

	} // Main

}
