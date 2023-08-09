package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16935
 * @author SSAFY
 *
 */
public class Main_16935_박정인 {
	static int N, M, R, map[][], tmp[][];
	static int[] dx = {-1, 0, 1, 0};	// n/2 * dx[i] + x ... ?
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int OP = Integer.parseInt(st.nextToken());

			create(OP);
			rotate(OP);
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void create(int op) {
		N = map.length;
		M = map[0].length;
		switch (op) {
		case 1:
		case 2:
		case 5:
		case 6:
			tmp = new int[N][M];
			break;

		case 3:
		case 4:
			tmp = new int[M][N];
			break;
		}
	}

	private static void rotate(int op) {		
		switch (op) {
		case 1:	// 상하
			for (int i= 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp[i].length; j++) {
					tmp[i][j] = map[N - 1 - i][j];
				}
			}
				
			break;
		case 2:	// 좌우
			for (int i= 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp[i].length; j++) {
					tmp[i][j] = map[i][M - 1 - j];
				}
			}
			break;
		case 3:	// 오른쪽 90
			for (int i= 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp[i].length; j++) {
					tmp[i][j] = map[N - 1 - j][i];
				}
			}
						
			break;
		case 4:	// 왼쪽 90 == 오른쪽 270
			for (int i= 0; i < tmp.length; i++) {
				for (int j = 0; j < tmp[i].length; j++) {
					tmp[i][j] = map[j][M - 1 - i];
				}
			}
							
			break;
		case 5:	// 4등분, 오른쪽 90
			int n = N/2, m = M/2;
			int[][] arr = new int[n][m];	// 임시 보관
			
			// 4번 구역 임시보관					
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					arr[i][j] = map[i + n][j];
				}
			}
						
			// 3 -> 4
			for (int i = n; i < N; i++) {
				for (int j = m; j < M; j++) {
					tmp[i][j - m] = map[i][j];					
				}
			}
			
			// 2 -> 3
			for (int i = 0; i < n; i++) {
				for (int j = m; j < M; j++) {
					tmp[i + n][j] = map[i][j];
				}
			}
			
			// 1 -> 2
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tmp[i][j + m] = map[i][j];	
				}
			}				
			
			// 임시보관한 내용 1번 구역에 저장
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tmp[i][j] = arr[i][j];
				}
			}
			
			break;
		case 6:	// 4등분, 왼쪽 90		
			n = N/2; 
			m = M/2;
			arr = new int[n][m];	// 임시 보관
			
			// 4번 구역 임시보관					
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					arr[i][j] = map[i + n][j];
				}
			}
						
			// 1 -> 4
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tmp[i + n][j] = map[i][j];				
				}
			}
			
			// 2 -> 1
			for (int i = 0; i < n; i++) {
				for (int j = m; j < M; j++) {
					tmp[i][j - m] = map[i][j];
				}
			}
			
			// 3 -> 2
			for (int i = n; i < N; i++) {
				for (int j = m; j < M; j++) {
					tmp[i - n][j] = map[i][j];	
				}
			}				
			
			// 임시보관한 내용 3번 구역에 저장
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tmp[i + n][j + m] = arr[i][j];
				}
			}
						
			break;
		}
		
		map = tmp;
	}
}
