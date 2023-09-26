package SWEA;

import java.util.*;
import java.io.*;

public class Solution_2115_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	static int T, N, M, C, arr[][], ans;
	static Honey selectedHoney[];
	static List<Honey> list;
	static boolean[] isSelected;
	
	static class Honey {
		int row, start, end;
		int max = 0;
		boolean[] isSelected = new boolean[M];
		
		public Honey(int row, int start, int end) {
			this.row = row;
			this.start = start;
			this.end = end;
			setMax(0, 0);
		}
		
		public void setMax(int sum, int max) {
			if(sum > C) {
				return;
			}
			this.max = Integer.max(max, this.max);
			for(int i = start; i < end; i++) {
				if(!isSelected[i - start]) {
					isSelected[i - start] = true;
					setMax(sum + arr[row][i], max + (int) Math.pow(arr[row][i], 2));
					isSelected[i - start] = false;
				}
			}
		}
	}
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		list = new ArrayList<>();
		selectedHoney = new Honey[2];
		ans = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; k++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void settingHoney() {
		for(int i = 0; i < N; i++) {
			for(int k = 0; k <= N - M; k++) {
				list.add(new Honey(i, k, k + M));
			}
		}
		isSelected = new boolean[list.size()];
	}
	
	// 사실 같은 라인에 있냐가 아닌 start ~ end 사이에 있냐 없냐로 해야하는데 문제에 오류가 있어서 row로 비교.
	public static boolean isSameLine(Honey h1, Honey h2) {
		return h1.row == h2.row;
	}
	
	public static void comb(int index, int cnt) {
		if(cnt == 2) {
			if(!isSameLine(selectedHoney[0], selectedHoney[1])) {
				ans = Integer.max(selectedHoney[0].max + selectedHoney[1].max, ans);
			}
			return;
		}
		
		for(int i = index; i < list.size(); i++) {
			if (!isSelected[i]) {
				selectedHoney[cnt] = list.get(i);
				isSelected[i] = true;
				comb(i + 1, cnt + 1);
				isSelected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			settingHoney();
			comb(0, 0);
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}
}
