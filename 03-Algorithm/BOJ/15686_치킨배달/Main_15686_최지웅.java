package todo.lesson._0811;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15686_최지웅 {

	static class Block {

		int r, c;

		Block(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
	
	static int MAXN = 50;
	
	static int N, M;
	
	static int mCnt;
	
	static List<Block> homeList = new ArrayList<>();
	static List<Block> chickenList = new ArrayList<>();
	
	static boolean[] open;
	
	static int[] seq;
	static boolean[] selected;
	
	static int minCityDist;
	
	static void combination(int cnt, int start) {
		if (cnt == mCnt) {
			Arrays.fill(open, false);
			
			for (int i = 0; i < seq.length; i++) {
				open[seq[i]] = true;
			}

			minCityDist = Math.min(minCityDist, cityDist());
			
		} else {
			for (int i = start; i < chickenList.size(); i++) {
				selected[i] = true;
				seq[cnt] = i;
				combination(cnt + 1, i + 1);
				selected[i] = false;
			}
		}
	}

	static int chickenDist(int r, int c) {
		
		int minDist = Integer.MAX_VALUE;
		
		Block chicken;
		int dist;
		
		if (mCnt == 0) return MAXN;
		
		for (int i = 0; i < chickenList.size(); i++) {
			chicken = chickenList.get(i);
			if (!open[i]) continue;
			dist = Math.abs(chicken.r - r) + Math.abs(chicken.c - c);
			minDist = Math.min(minDist, dist);
		}
		
		return minDist;
	}
	
	static int cityDist() {

		int sum = 0;
		Block home;
		
		for (int i = 0; i < homeList.size(); i++) {
			home = homeList.get(i);
			sum += chickenDist(home.r, home.c);
		}
		
		return sum;
	}

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		final int EMPTY = 0;
		final int HOME = 1;
		final int CHICKEN = 2;

		int[][] city = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				city[r][c] = Integer.parseInt(st.nextToken());
				if (city[r][c] == HOME) homeList.add(new Block(r, c));
				else if (city[r][c] == CHICKEN) chickenList.add(new Block(r, c));
			}
		}
		
		open = new boolean[chickenList.size()];
		Arrays.fill(open, false);
		
		selected = new boolean[chickenList.size()];
		
		minCityDist = Integer.MAX_VALUE;
		
		for (mCnt = 0; mCnt <= M; mCnt++) {
			Arrays.fill(selected, false);
			seq = new int[mCnt];
			combination(0, 0);
		}
		
		System.out.println(minCityDist);
	}

}
