import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {  // BOJ_15686
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][] lst;  // 도시 정보
	static int N, M;  // 도시 크기, 살릴 치킨집의 최대 개수
	static int res = Integer.MAX_VALUE;  // 치킨 거리의 최솟값
	static int chSize;  // 도시에서 찾은 치킨집 개수
	static List<Integer[]> chicken, house;  // 치킨집, 집 위치
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		// 도시 정보 & 집, 치킨집 초기화
		lst = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				lst[i][j] = Integer.parseInt(st.nextToken());
				
				if (lst[i][j] == 1) {
					house.add(new Integer[] {i, j});
				}
				
				if (lst[i][j] == 2) {
					chicken.add(new Integer[] {i, j});
				}
			}
		}
		
		// 도시에서 찾은 치킨집 개수만큼 방문 배열 초기화
		chSize = chicken.size();
		boolean[] visited = new boolean[chSize];
		
		comb(0, 0, visited);
		
		sb.append(res);
		System.out.println(sb);
		
	}  // Main
	
	/**
	 * 조합식으로 치킨집을 1 ~ M개까지 선택해보고 
	 * 치킨 거리 계산 함수로 넘기는 함수.
	 * @param start : 조합식을 위한 인덱스
	 * @param cnt : 조합식을 위한 선택 개수
	 * @param visited : 조합식을 위한 방문 배열
	 */
	private static void comb(int start, int cnt, boolean[] visited) {
		/*
		 * 치킨집을 1 ~ M개까지 선택해야하므로,
		 * return없이 선택 개수 범위에 들어오면 모두 chickenRoad()로 넘김.
		 */
		if (0 < cnt && cnt <= M) {  // 1개 ~ 최대 M개 
			chickenRoad(visited);
		}
		
		// 조합 : 경우의 수 선택
		for (int i = start; i < chSize; i++) {
			if (visited[i] == true) {
				continue;
			}
			
			visited[i] = true;
			comb(i + 1, cnt + 1, visited);
			visited[i] = false;
		}
	}
	
	/**
	 * 선택된 치킨집 개수로 치킨 거리를 계산하는 함수
	 * @param visited : 치킨집을 true, false로 선택되어있는 방문 배열
	 * 
	 * house
	 * [0, 2], [1, 4], [2, 1], [3, 2]
	 * ------------------------------
	 * [1, 2], [2, 2], [4, 4]
	 * chicken
	 * 
	 */
	private static void chickenRoad(boolean[] visited) {
		// 각각 집과 치킨집의 좌표를 넣어 사용할 임시 배열
		// Ex) [0, 2], [1, 2] 등
		Integer[] hTemp = new Integer[2];
		Integer[] chTemp = new Integer[2];
		
		// 각 집 기준 치킨거리를 계산하기위한 임시 배열
		int[] home = new int[house.size()];
		// 계산 전 거리는 큰 값으로 초기화
		Arrays.fill(home, Integer.MAX_VALUE);
		
		// 치킨 거리 계산
		for (int i = 0; i < visited.length; i++) {
			// 1. 현재 넘어온 방문 배열의 true = 선택된 치킨집
			if (visited[i] == true) { 
				chTemp = chicken.get(i);  // 2. 선택된 치킨집의 좌표를 꺼냄
				for (int j = 0; j < house.size(); j++) {
					// 3. 집을 하나씩 꺼내어 각각의 치킨거리 계산
					hTemp = house.get(j);
					home[j] = Math.min(home[j], Math.abs(hTemp[0]-chTemp[0]) + Math.abs(hTemp[1]-chTemp[1]));
				}
			}
		}
		
		// 현재 선택된 치킨집 기준, 모든 집의 최소 치킨 거리를 구했으니 합산하여 최종적인 치킨거리를 계산
		int sum = 0;
		for (int i = 0; i < home.length; i++) {
			sum += home[i];
		}
		
		// 도시의 치킨거리가 최소인 쪽으로 결과값 갱신
		res = Math.min(res, sum);
		return;
	}
	
}
