import java.io.*;
import java.util.*;

public class Solution_1247_김현영 {

	static int n;
	static int[][] pos, dis;
	static List<Integer> totalDis; // 각 경로별 이동거리 저장
	static int[] movePos; // 각 경로별 좌표 저장
	static boolean[] isVisited; // 방문 여부

	static void findWay(int count) {
		//경로의 경우를 구했다면 이동거리 구하기 시작
		if (count == n + 1) {
			int nowDis = 0;	//현재 이동거리
			for (int i = 0; i < n + 1; i++) {
				nowDis += dis[movePos[i]][movePos[i+1]];
				//현재 경로에서 지금까지 이동거리가 이전경로에서의 최소이동거리보다 크다면 탐색 중단
				if(!totalDis.isEmpty() && totalDis.get(0) < nowDis)
					return;
			}
			totalDis.add(nowDis);	//경로별 이동거리에 현재 이동거리 추가
			Collections.sort(totalDis);	//경로별 이동거리 오름차순
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (!isVisited[i]) {
				movePos[count] = i;
				isVisited[i] = true;
				findWay(count + 1);
				isVisited[i] = false;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine()); // 고객의 수

			pos = new int[n + 2][2]; // n명고객 + 회사 + 집 좌표
			st = new StringTokenizer(br.readLine());

			// 회사 좌표
			pos[0][0] = Integer.parseInt(st.nextToken());
			pos[0][1] = Integer.parseInt(st.nextToken());
			// 집 좌표
			pos[n + 1][0] = Integer.parseInt(st.nextToken());
			pos[n + 1][1] = Integer.parseInt(st.nextToken());
			// 고객 좌표
			for (int i = 1; i <= n; i++) {
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}

			dis = new int[n + 2][n + 2]; // 고객, 회사, 집 좌표 각각에 대한 거리
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					dis[i][j] = Math.abs(pos[j][0] - pos[i][0]) + Math.abs(pos[j][1] - pos[i][1]);
				}
			}

			movePos = new int[n + 2];	// 각 경로별 좌표 저장
			movePos[0] = 0; // 첫 경로 회사
			movePos[n + 1] = n + 1; // 마지막 경로 집
			isVisited = new boolean[n + 2];
			isVisited[0] = true; // 회사 방문처리
			isVisited[n + 1] = true; // 집 방문처리
			totalDis = new ArrayList<>(); 	// 경로별 이동거리 
			findWay(1);

			// 출력
			sb.append("#").append(t).append(" ").append(totalDis.get(0)).append("\n");
		}
		System.out.println(sb.toString());
	}
}
