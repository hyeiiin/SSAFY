package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15686
 * @author SSAFY
 *
 */
public class Main_15686_박정인 {	
	static class Combination {	// 조합 목록을 얻기위한 클래스
		List<List<Position>> comList;	// 결과로 얻어올 조합 목록
		int[] output;	// 하나의 경우에 해당하는 조합
		int n, r;	// nCr
		
		public Combination(int n, int r) {
			this.n = n;
			this.r = r;
			output = new int[r];
			comList = new ArrayList<>();
		}
		
		// 조합 구하기 
		public void combination(List<Position> list, int cnt, int start) {
			if (cnt == r) {
				List<Position> tmp = new ArrayList<>();
				for (int i = 0; i < r; i++) {
					tmp.add(list.get(output[i]));
				}
				comList.add(tmp);
				return;
			}
			
			for (int i = start; i < n; i++) {
				output[cnt] = i;
				combination(list, cnt + 1, i + 1);				
			}
		}
	}
	
	// 위치 클래스
	static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}				
	}
	
	private static final int BLANK = 0;
	private static final int HOME = 1;
	private static final int CHICKEN = 2;
	
	static int N, M, city[][];
	static List<Position> chickens;	// 치킨집 위치를 저장하기 위한 List
	static List<Position> homes;	// 집의 위치를 저장하기 위한 List
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE;
		chickens = new ArrayList<>();
		homes = new ArrayList<>();
		city = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {			
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == CHICKEN) {
					chickens.add(new Position(i, j));
				}
				
				if (city[i][j] == HOME) {
					homes.add(new Position(i, j));
				}
			}
		}
		
		// 전체 로직)
		// 치킨집 조합 구하기
		// 각 조합에 따른 도시의 치킨거리 구하기
		// 치킨 거리 최소값 구하기 
		
		// 치킨집 조합 목록얻기
		Combination com = new Combination(chickens.size(), M);
		com.combination(chickens, 0, 0);
		List<List<Position>> list= com.comList;
		
		// 각 조합에 해당하는 치킨집과 집 사이의 치킨거리를 계산한 도시의 치킨거리를 구하고 최소값 갱신
		for (List<Position> posList : list) {
			min = Math.min(min, getCityChicken(posList));
		}
				
		System.out.println(min);
	}
	
	private static int getCityChicken(List<Position> list) {
		int total = 0;
		
		// 각 집에서의 치킨 거리 
		for (Position home : homes) {
			// 치킨 거리구하기 
			int minValue = Integer.MAX_VALUE;
			for (Position chicken : list) {
				int dist = Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y);
				minValue = Math.min(minValue, dist);
			}
			total += minValue;
		}
		
		return total;
	}
}
