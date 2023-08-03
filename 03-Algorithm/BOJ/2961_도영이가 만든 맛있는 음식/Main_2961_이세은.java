package practice;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2961_이세은 {

	static int n;
	static int[] sourArr; // 신맛 저장 배열
	static int[] bittArr; // 쓴맛 저장 배열
	static boolean[] visited;
	static int rsltS = 1, rsltB = 0;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		visited = new boolean[n + 1];
		sourArr = new int[n];
		bittArr = new int[n];

		// 신맛과 쓴맛의 부분집합을 만들어서 그 차가 가장 작은 것을 결과로 한다
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitt = Integer.parseInt(st.nextToken());

			sourArr[i] = sour;
			bittArr[i] = bitt;

		}
		findSet(0, 0, 0, 0);

		System.out.println(min);

	}

	public static void findSet(int cnt, int sourCnt, int bittCnt, int slctedCnt) {
		if (cnt == n) {
			rsltS = 1;
			rsltB = 0;
			if (slctedCnt > 0) {
				for (int i = 0; i < n; i++) {
					if (visited[i]) {
						rsltS *= sourArr[i];
						rsltB += bittArr[i];
					}
				}
				min = Math.min(min, Math.abs(rsltS - rsltB));
			}
			return;
		}

		visited[cnt] = true;
		findSet(cnt + 1, sourCnt + 1, bittCnt + 1, slctedCnt + 1);
		visited[cnt] = false;
		findSet(cnt + 1, sourCnt + 1, bittCnt + 1, slctedCnt);
	}
}
