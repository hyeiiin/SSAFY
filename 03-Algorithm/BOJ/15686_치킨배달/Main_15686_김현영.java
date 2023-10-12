import java.io.*;
import java.util.*;

public class Main_15686_김현영 {
	static int n, m, minCityDis;
	static int[][] city; // 도시 좌표
	static int[][] chicken; // 치킨집 좌표
	static int[][] house; // 집 좌표
	static boolean[] selectChicken; // 부분집합에서 선택된 치킨

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 도시 크기 nxn
		m = Integer.parseInt(st.nextToken()); // 폐업시키지 않을 치킨 집

		// 도시 지도 입력
		city = new int[n][n];
		chicken = new int[13][2]; // 처음 치킨 집 개수를 모르므로 최대 크기인 13으로 할당
		house = new int[2 * n][2]; // 처음 집 개수를 모르므로 최대 크기인 2*n 할당
		int c = 0; // 치킨집 위치 인덱스
		int h = 0; // 집 위치 인덱스
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				// 치킨집의 좌표 저장
				if (city[i][j] == 2) {
					chicken[c][0] = i;
					chicken[c][1] = j;
					c++;
				} 
				// 집 좌표 저장
				else if (city[i][j] == 1) {
					house[h][0] = i;
					house[h][1] = j;
					h++;
				}
			}
		}
		chicken = Arrays.copyOf(chicken, c); // 치킨집의 개수만큼 크기 조절
		house = Arrays.copyOf(house, h); // 집의 개수만큼 크기 조절
		
		// 도시의 치킨거리 구하기
		selectChicken = new boolean[c];
		minCityDis = Integer.MAX_VALUE;
		findChicken(0, 0);
		System.out.println(minCityDis);
	}

	static void findChicken(int start, int count) {
		if (count == m) { 
			// 각 집부터 부분조합에 선택된 치킨집까지의 거리 중 최소값 찾기
			// 치킨 거리 합을 찾고 최소 도시 치킨거리  갱신
			int cityDis = 0; //현재 도시 치킨 거리
			for (int i = 0; i < house.length; i++) {
				if (cityDis > minCityDis)
					return;
				int dis = Integer.MAX_VALUE;
				for (int j = 0; j < chicken.length; j++) {
					if (!selectChicken[j])
						continue;
					dis = Math.min(dis, Math.abs(house[i][0] - chicken[j][0]) + Math.abs(house[i][1] - chicken[j][1]));
				}
				cityDis += dis;
			}

			minCityDis = Math.min(minCityDis, cityDis);	// 도시 치킨거리 갱신

			return;
		}
		
		// 치킨집 부분집합 구하기
		for (int i = start; i < chicken.length; i++) {
			selectChicken[i] = true;
			findChicken(i + 1, count + 1);
			selectChicken[i] = false;
		}

	}

}
