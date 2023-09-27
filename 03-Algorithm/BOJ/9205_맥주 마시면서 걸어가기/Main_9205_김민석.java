package BOJ;

import java.util.*;
import java.io.*;

public class Main_9205_김민석 {
	//시작좌표, 끝좌표
	private static Point start, end;
	//편의점 좌표들
	private static Point[] store;
	//편의점 방문배열
	private static boolean[] visited;
	private static StringBuilder sb;
	/**
	 * 좌표 클래스 
	 */
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static void BFS() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//끝좌표가 현재 좌표에서 갈수있다면 happy 추가하고 리턴
			if(Math.abs(cur.x-end.x) + Math.abs(cur.y-end.y) <= 1000) {
				sb.append("happy");
				return;
			}
			//편의점 개수만큼 
			for (int i = 0; i < store.length; i++) {
				//방문안한 편의점이면서 갈수있는 거리에 있다면
				if(!visited[i] &&Math.abs(cur.x-store[i].x) + Math.abs(cur.y-store[i].y) <= 1000) {
					//방문표시
					visited[i] = true;
					//큐에 편의점 위치 추가
					queue.offer(store[i]);
				}
			} 
		}
		//그대로 while문 빠져나왔다면 sad 추가
		sb.append("sad");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		//입력 및 초기화
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			store = new Point[N];
			visited = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				store[j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			BFS();
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
