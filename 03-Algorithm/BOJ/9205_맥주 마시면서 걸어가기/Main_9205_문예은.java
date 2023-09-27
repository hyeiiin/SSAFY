import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9205_문예은 {
	/*
	집 - 편의점 - 편의점 - 페스티벌
	1000미터씩만 이동가능
	? 의문 ? 좌표값이 정렬되어 나오는가?
	
	맥주가 0이 되면 sad
	맥주 0 안되고 종점 도착하면 happy

	 */
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int t = 1; t <= T; t++) {
			int S = Integer.parseInt(br.readLine()); // 편의점 개수
			st = new StringTokenizer(br.readLine()); // 시작 좌표
			Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			ArrayList<Point> spot = new ArrayList<>(); // 정점 좌표담을 리스트
			if (S != 0) { // 편의점 있으면
				for (int s = 0; s < S; s++) {
					st = new StringTokenizer(br.readLine());
					spot.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				}
			}
			st = new StringTokenizer(br.readLine()); // 목표 좌표
			Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			spot.add(end);
			
			Queue<Point> queue = new ArrayDeque<>(); // 이동좌표 담을 큐
			boolean[] visited = new boolean[S]; // 편의점 개수만큼의 방문배열
			boolean isHappy = false; // 락페스티벌 도착 여부
			queue.add(start); // 시작좌표 먼저 큐에 담기
			while (!queue.isEmpty()) {
				Point cur = queue.poll();
				int curX = cur.x;
				int curY = cur.y;
				if (Math.abs(curX - end.x)+Math.abs(curY - end.y) <= 1000) { // 목표 좌표까지 이동 완료되면
					isHappy = true;
					break;
				}
				for (int i = 0; i < S; i++) { // 근처 편의점들 거리 계산
					if (visited[i]) continue; // 이전에 방문한 편의점은 못간다!
					int nextX = spot.get(i).x;
					int nextY = spot.get(i).y;
					if (Math.abs(curX - nextX)+Math.abs(curY - nextY) <= 1000) { // 이동가능 편의점이면
						visited[i] = true;
						queue.add(new Point(nextX, nextY)); // 편의점으로 이동하기
					}
				}
			}
			if (isHappy) {
				sb.append("happy"+"\n");				
			} else {
			sb.append("sad"+"\n");
			}
		}
		System.out.print(sb.toString());	
	}

}