package swea;

import java.util.*;
import java.io.*;

class Point implements Comparable<Point>{
	int x; //행
	int y; //열
	int n; //미생물 수
	int d; //방향
	int cell; //셀 순서
	
	public Point(int x, int y, int n, int d, int cell) {
		super();
		this.x = x;
		this.y = y;
		this.n = n;
		this.d = d;
		this.cell = cell;
	}

	@Override
	public int compareTo(Point o) {
		//좌표가 같으면 미생물 내림차순 정렬
		if(this.cell == o.cell) {
			return o.n - this.n;
		}
		return this.cell - o.cell;
	}
}
public class Solution_2382_문혜린 {
	static int N, M, K;
	static int x, y, n, d;
	static List<Point> list;
	//상하좌우
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테케 개수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //셀의 개수
			M = Integer.parseInt(st.nextToken()); //격리 시간
			K = Integer.parseInt(st.nextToken()); //미생물 군집의 개수
			
			list = new ArrayList<>(); //미생물 리스트
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken()); //세로위치
				y = Integer.parseInt(st.nextToken()); //가로위치
				n = Integer.parseInt(st.nextToken()); //미생물 수
				d = Integer.parseInt(st.nextToken()); //이동 방향
				
				list.add(new Point(x, y, n, d, x*N+y));
			}
			//시간 당 미생물 이동
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < list.size(); j++) {
					Point p = list.get(j);

					//미생물 이동
					p.x = p.x + dx[p.d];
					p.y = p.y + dy[p.d];
					p.cell = p.x*N + p.y;
					
					//셀의 가장자리일 경우 미생물 절반 죽고 이동방향 반대로 바뀜
					if(p.x==0 || p.x==N-1 || p.y==0 || p.y==N-1) {
						p.n /= 2;
						
						switch(p.d) {
						case 1: //상
							p.d = 2;
							break;
						case 2: //하
							p.d = 1;
							break;
						case 3: //좌
							p.d = 4;
							break;
						case 4: //우
							p.d = 3;
							break;
						}
						//미생물 0일 경우 군집 사라짐
						if(p.n == 0) {
							list.remove(j);
							j--;
						}
					}
				}
				
				Collections.sort(list); //미생물 수 내림차순 정렬
				
				for (int j = 0; j < list.size()-1; j++) {
					Point now = list.get(j);
					Point next = list.get(j+1);
					//같은 위치에 있을 경우 군집 합침
					if(now.cell == next.cell) {
						//미생물 수 내림차순 정렬 했으므로 미생물 수만 합치고 방향은 그대로 두기
						now.n += next.n;
						list.remove(j+1);
						j--;
					}
				}
			}
			//남아있는 미생물 수 총 합
			int result = 0;
			for (int i = 0; i < list.size(); i++) {
				result += list.get(i).n;
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}
