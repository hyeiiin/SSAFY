package ssafy.Boj;

import java.io.*;
import java.util.*;

/**
 * 도영이가 만든 맛있는 음식 1 재료의 개수 n (1<=n<=10) 3 10 신맛 쓴맛 여러 재료 신맛 = 신맛들의 곱 쓴맛 = 쓴맛들의 합
 * 
 * 신맛과 쓴맛의 차이가 가장 작은 요리?
 * 
 * @author SSAFY
 */
public class _2961_Boj {

	private static int n, idx[];
	private static boolean[] isSelected;
	private static int[][] tasty;
	static int minValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		idx = new int[n];
		for (int i = 0; i < n; i++) {
			idx[i] = i;
		}

		isSelected = new boolean[n];
		tasty = new int[n][2]; // 맛 배열
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			tasty[i][0] = Integer.parseInt(st.nextToken()); // 신맛
			tasty[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛
		}

		makeSet(0);
		System.out.println(minValue);
	}

	private static void makeSet(int cnt) {
		if (cnt == n) {
			int sour = 1;
			int bitter = 0;
			int count = 0;
			for (int i = 0; i < n; i++) {
				if (isSelected[i] == false)
					continue;
				count++;
				sour *= tasty[i][0];
				bitter += tasty[i][1];
			}

			if (count != 0) {
				minValue = Math.min(minValue, Math.abs(sour - bitter));
			}
			return;
		}

		isSelected[cnt] = true;
		makeSet(cnt + 1);
		isSelected[cnt] = false;
		makeSet(cnt + 1);

	}
}
