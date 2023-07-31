package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1289_서지원 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			char[] arr = br.readLine().toCharArray();
			// 초기화 상태 bit 0과 같으면 0, 다르면 1
			int result = (arr[0] == '0')? 0 : 1;
			// i번째, (i + 1)번쨰 bit 비교, 다르면 +1
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] != arr[i + 1]) {
					result++;
				}
			}
			System.out.printf("#%d %d\n", tc, result);
		}
	}

}
