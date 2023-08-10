package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4012_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int TC, N, arr[][], num[], ans;
	static List<Integer> synergy;
	static boolean isSelected[];

	public static int minSynergy() {
		int x = 0;
		int y = 0;
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < N; k++) {
				if(i == k) continue;
				if(isSelected[i] && isSelected[k]) {
					x += arr[i][k];
				} else if(!isSelected[i] && !isSelected[k]) {
					y += arr[i][k];
				}
			}
		}
		return Math.abs(x - y);
	}
	
	public static void recursionComb(int index, int cnt) { // 조합 구하기
		if (cnt == N / 2) {
			ans = Math.min(ans, minSynergy());
			return;
		} else {
			for (int i = index; i < N; i++) {
				if (isSelected[i]) // 선택된 것이면 넘어감
					continue;
				num[cnt] = i; // 새로운 조합 num에 inArr선택된 거 넣어주기
				isSelected[i] = true; // 선택된 건 true로
				recursionComb(i + 1, cnt + 1); // cnt를 늘려서 다음 수 찾기
				isSelected[i] = false; // 선택됐던 거 false로 돌려주기(중복 조합이 아니니까!)
			}
		}
	}

	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					arr[i][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			num = new int[N / 2];
			isSelected = new boolean[N];
			synergy = new ArrayList<>();
			recursionComb(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}
}
