import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {  // BOJ_16926
	static StringBuilder sb;
	static StringTokenizer st;
	static boolean[][] visited;  // bfs() 방문처리
	static int[][] lst;  // 원본 배열
	static int N, M, R;  // 행, 열, 수행해야 할 회전 수
	static int cycle;  // 회전 출발점의 기준

    // 우하좌상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());  // 원본 배열 행
		M = Integer.parseInt(st.nextToken());  // 원본 배열 열
		R = Integer.parseInt(st.nextToken());  // 수행해야 할 회전 수
		
		lst = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				lst[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 한 번 회전에서 돌려야 하는 사각형 범위의 수
		int range = Math.min(N, M) / 2;
		for (int i = 0; i < range; i++) {
			cycle = i;  // 출발점의 기준
			rotate(i, i, 1);
		}
		
		sb = new StringBuilder();
		for (int[] subLst : lst) {
			for (int element : subLst) {
				sb.append(element).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}  // Main
	
	/**
	 * 
	 * @param x : 출발점의 행 좌표
	 * @param y : 출발점의 열 좌표
	 * @param cnt : 현재 회전수
	 */
	private static void rotate(int x, int y, int cnt) {
		int nx;
		int ny;
		
		/*
		 * start = lst[x][y]
		 * BFS() 동작상 값을 임시저장하지 않기 때문에,
		 * 처음 위치의 값을 따로 기억해놓고
		 * 마지막에 원점으로 돌아왔을 때 그 값을 적기 위함.
		 */
		int start = lst[x][y];  
		int direc = 0;  // 방향
		
		while (true) {
			nx = x + dx[direc];
			ny = y + dy[direc];
			
			// 1. 주어진 방향대로 범위안에 이동이 잘 되었다면,
			if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
				// 1-1. 다음 위치의 값을 당겨오기.
				lst[x][y] = lst[nx][ny];
				// 1-2. 현재 위치를 다음위치로 이동
				x = nx;
				y = ny;
				
				
				/*
				 * 1-3. 현재 회전 사이클이 마지막 회전 중이라면,
				 * bfs() 동작상 다시 재귀로 들어와서 한 번 더 사이클이 실행될 때
				 * 또 처음 위치부터 같은 곳을 돌아오기 때문에 방문 처리를 해주어
				 * 중복 회전을 방지함.
				 */
				if (cnt == R) {
					// visited 경로 찍어보기
//					for (boolean[] a : visited) {
//						for (boolean b : a) {
//							System.out.printf("%6s", b);
//						}
//						System.out.println();
//					}
//					System.out.printf("-------%d-------\n", cnt);
					visited[x][y] = true;
				}
				
				// END : rotate 함수에 처음 들어왔을 때의 출발점으로 돌아왔다면,
				// 덮어씌어진 처음 위치의 값을 최초 함수 시작 맨 처음 저장해놓은 출발점 값으로 변경.
				if (x == cycle && y == cycle) {
					lst[cycle + 1][cycle] = start;
					break;
				}
			}
			else {  // 한 방향으로 미는중 배열 범위에 막히면 방향전환.
				direc = (direc + 1) % 4;
				continue;
			}
		}  // while
		
		if (cnt < R) {  // 최종 목표 회전 횟수까지 재귀 호출
			rotate(x, y, cnt + 1);
		}
		
	}  // rotate()
}
	
	
