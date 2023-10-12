import java.io.*;
import java.util.*;

public class Main_9205_김현영 {

	//좌표 정보를 가진 클래스
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 수

		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine()); // 편의점 수

			// 집 편의점 페스티벌 좌표
			List<Point> list = new ArrayList<>();
			for (int i = 0; i < n+2; i++) {
				st = new StringTokenizer(br.readLine());
				Point next = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				list.add(next);
			}

			
			//i좌표에서 j좌표로 이동가능한지 여부 저장
			boolean[][] d = new boolean[n+ 2][n+2];
			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					Point now= list.get(i);		//현재좌표
					Point next = list.get(j);	//이동할 좌표
					if (getDistance(now, next) <= 1000) //두 좌표의 길이가 1000미만이라면 이동가능
						d[i][j] = true;
				}
			}

			// 플로이드 워샬 알고리즘
			for (int k = 0; k < n + 2; k++) {
				for (int i = 0; i < n + 2; i++) {
					for (int j = 0; j < n + 2; j++) {
						if(d[i][k]&&d[k][j])
							d[i][j] = true;
					}
				}
			}
			
			if(d[0][n+1])
				sb.append("happy").append("\n");
			else
				sb.append("sad").append("\n");

		}

		System.out.println(sb.toString());
	}

 
	// 두 좌표 간 거리 구하기
	static int getDistance(Point a, Point b) {
		return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
	}

}