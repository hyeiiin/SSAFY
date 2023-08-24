import java.io.*;
import java.util.*;

public class Main_17471_김현영 {

	static boolean[] connectA, connectB;	// 각 구간이 다른 구역과 연결되어 있는지 여부 
	static int[][] list;
	static boolean isDivided;	//구역을 나눈 적이 있는지 여부
	static int n; // 구역의 개수
	static int peopleA, peopleB, difMin, connectCount; // A구역 인구, B구역 인구, 인구 차이 최솟값, 다른 구역과 연결된 구역의 수
	static int[] people; // 구역별 인구
	static int[] areaA, areaB;	//A의 구역, B의 구역
	static Deque<Integer> q;

	// size : A 구역이 가질 수 있는 구역의 크기
	static void divide(int size, int cnt, int start) {
		if (cnt == size) {
			// areaB 구역 구하기
			int indexB = 0;
			findB: 
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < areaA.length; j++) {
					if (areaA[j] == i)
						continue findB;
				}
				areaB[indexB++] = i;
			}

			// 구역이 모두 연결되어있는지 확인
			// 구역 연결 여부 초기화
			connectA = new boolean[size];
			connectB = new boolean[n - size];

			// A구역
			q = new ArrayDeque<>();
			q.offer(areaA[0]);
			connectA[0] = true;
			connectCount = 1;	//현재 연결 구역 1개
			// 연결이 끝날 때까지 반복
			while (!q.isEmpty()) {
				int now = q.poll();
				//현재 구역과 연결된 구역리스트를 중에서 A구역이 있는지 탐색
				for (int i : list[now]) {
					for (int j = 0; j < areaA.length; j++) {
						// 이미 탐색한 구역이면 다음구역 탐색
						if (connectA[j])	
							continue;
						//현재 구역과 연결된 구역이 있다면 큐에 넣기
						if (i == areaA[j]) {
							q.offer(i);
							connectA[j] = true;
							connectCount++;
						}
					}
				}
			}
			//A구역의 모든 구역이 연결되지 않았다면 다른 구역 조합 찾기
			if (connectCount != areaA.length)
				return;
			 
			// B구역
			q = new ArrayDeque<>();
			q.offer(areaB[0]);
			connectCount = 1;	//현재 연결 구역 1개
			connectB[0] = true;
			// 연결이 끝날 때까지 반복
			while (!q.isEmpty()) {
				int now = q.poll();
				//현재 구역과 연결된 구역리스트를 중에서 B구역이 있는지 탐색
				for (int i : list[now]) {
					for (int j = 0; j < areaB.length; j++) {
						// 이미 탐색한 구역이면 다음구역 탐색
						if (connectB[j])
							continue;
						//현재 구역과 연결된 구역이 있다면 큐에 넣기
						if (i == areaB[j]) {
							q.offer(i);
							connectB[j] = true;
							connectCount++;
						}
					}
				}
			}
			//B구역의 모든 구역이 연결되지 않았다면 다른 구역 조합 찾기
			if (connectCount != areaB.length)
				return;
			

			// areaA의 인구, areaB의 인구를 구해서 차이값구하기
			peopleA = 0;
			for (int i = 0; i < areaA.length; i++) {
				peopleA += people[areaA[i]];
			}
			peopleB = 0;
			for (int i = 0; i < areaB.length; i++) {
				peopleB += people[areaB[i]];
			}

			// 최소 인구차이 갱신
			if (difMin > Math.abs(peopleA - peopleB)) {
				isDivided = true;
				difMin = Math.abs(peopleA - peopleB);
			}

			return;
		}

		// A구역이 가질 수 있는 조합 찾기
		for (int i = start; i < n; i++) {
			areaA[cnt] = i;
			divide(size, cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 구역의 개수
		st = new StringTokenizer(br.readLine());

		// 구역 별 인구 입력
		people = new int[n]; // 구역 별 인구
		for (int i = 0; i < n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		// 인접 구역 정보 입력
		list = new int[n][0];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			list[i] = new int[size];
			for (int j = 0; j < size; j++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				list[i][j] = to;
			}
		}

		difMin = Integer.MAX_VALUE;	//구역차이의 최솟값을 구하기 위해 큰 값으로 지정
		for (int i = 1; i <= n / 2; i++) {
			areaA = new int[i];
			areaB = new int[n - i];
			divide(i, 0, 0);
		}
		//한 번도 구역을 나눈 적이 없다면 = 두 구역으로 나눌 수 없다면 -1 출력
		if(!isDivided)
			System.out.println(-1);
		else
			System.out.println(difMin);
	}
}
