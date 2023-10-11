import java.io.*;
import java.util.*;

public class Solution_4013_김현영 {

	static List<Deque<Integer>> list;	//자석 자성정보 저장

	//자석의 회전정보 저장
	public static class Info {
		int num, dir;	//자석 번호, 회전 방향

		public Info(int num, int dir) {
			super();
			this.num = num;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스
		for (int t = 1; t <= T; t++) {
			int k = Integer.parseInt(br.readLine());// 자석 회전 횟수

			// 자석 날 자성정보 입력
			list = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				Deque<Integer> magnet = new ArrayDeque<>();
				for (int j = 0; j < 8; j++) {
					magnet.offer(Integer.parseInt(st.nextToken()));
				}
				list.add(magnet);
			} 

			// 회전정보 입력 : 자석 번호, 회전방향
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1; // 자석 번호
				int dir = Integer.parseInt(st.nextToken()); // 회전방향 1:시계, -1:반시계

				// 회전 정보에 따라 회전시킬 자석의 번호, 방향 찾기
				Deque<Info> info = new ArrayDeque<>();
				boolean[] visited = new boolean[4];
				info = rotation(num, dir, info, visited);

				// info에 저장된 회전 정보대로 자석 돌리기
				while (!info.isEmpty()) {
					Info current = info.pollFirst();
					// 시계 방향 : 마지막 자성을 앞으로 이동
					if (current.dir == 1) {
						int move = list.get(current.num).pollLast();
						list.get(current.num).addFirst(move);
					}
					// 반시계 방향 : 맨 앞 자성을 마지막으로 이동
					else {
						int move = list.get(current.num).poll();
						list.get(current.num).offerLast(move);
					}
				}
			} 
			// 점수 계산
			int score = 0;
			for (int i = 0; i < 4; i++) {
				if (list.get(i).peek() == 1)
					score += Math.pow(2, i);
			}

			sb.append("#").append(t).append(" ").append(score).append("\n");
		}

		System.out.println(sb.toString());

	}

	// 자석 회전정보를 찾는 함수
	static Deque<Info> rotation(int num, int dir, Deque<Info> info, boolean[] visited) {
		info.add(new Info(num, dir));
		visited[num] = true;	//방문처리

		// deque -> 배열로 변환하고 값(자성)확인
		// 오른쪽 확인
		if (checkIndex(num + 1) && !visited[num+1]) {
			Integer[] now = list.get(num).toArray(new Integer[8]);
			Integer[] next = list.get(num + 1).toArray(new Integer[8]);
			// 옆 자석과 다른 자성일 경우 회전
			if (now[2] != next[6])
				rotation(num + 1, dir * -1, info, visited);
		}
		// 왼쪽 확인
		if (checkIndex(num - 1) && !visited[num-1]) {
			Integer[] now = list.get(num).toArray(new Integer[8]);
			Integer[] next = list.get(num - 1).toArray(new Integer[8]);
			// 옆 자석과 다른 자성일 경우 회전
			if (now[6] != next[2])
				rotation(num -1, dir * -1, info, visited);
		}
		return info;
	}

	static boolean checkIndex(int x) {
		if (0 <= x && x < 4)
			return true;
		return false;
	}

}
