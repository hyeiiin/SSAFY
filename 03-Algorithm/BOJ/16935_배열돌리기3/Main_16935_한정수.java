import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16935_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int NM_temp = 0; // 배열 회전(3,4번)할때 N, M이 바뀌므로 그때를 대비
		
		int[][] arr = new int[N][M];
		int[][] res = new int [N][M];
		int[][] res_temp;
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.arraycopy(arr, 0, res, 0, arr.length);
		//=======입력 끝===============
		
		
		//마지막 연산(1~6 숫자 나오는거) 줄로 읽어오고.
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int temp = Integer.parseInt(st.nextToken());
			switch (temp) {
				case 1:
					action_1(arr, N, M);

//					for(int j=0; j<N; j++) {
//						for(int k=0 ; k<M; k++) {
//							System.out.print(arr[j][k]+" ");
//						}
//						System.out.println();
//					}
					break;
				case 2:
					action_2(arr, N, M);
//					for(int j=0; j<N; j++) {
//						for(int k=0 ; k<M; k++) {
//							System.out.print(arr[j][k]+" ");
//						}
//						System.out.println();
//					}
					break;
				case 3:
					res_temp = new int[M][N];
					action_3(arr, N, M, res_temp);
					arr = new int[M][N];
					for(int j=0; j<M; j++) {
						System.arraycopy(res_temp[j], 0, arr[j], 0, N);						
					}
					NM_temp = N;
					N = M;
					M = NM_temp;
					
					
//					for(int j=0; j<M; j++) {
//						for(int k=0 ; k<N; k++) {
//							System.out.print(res[j][k]+" ");
//						}
//						System.out.println();
//					}
					break;

				case 4:
					res_temp = new int[M][N];
					action_4(arr, N, M, res_temp);
					arr = new int[M][N];
					for(int j=0; j<M; j++) {
						System.arraycopy(res_temp[j], 0, arr[j], 0, N);						
					}
					NM_temp = N;
					N = M;
					M = NM_temp;
					break;
				case 5:
					action_5(arr, N, M);
//					for(int j=0; j<N; j++) {
//						for(int k=0 ; k<M; k++) {
//							System.out.print(arr[j][k]+" ");
//						}
//						System.out.println();
//					}
					break;
					
				case 6:
					action_6(arr, N, M);
//					for(int j=0; j<N; j++) {
//						for(int k=0 ; k<M; k++) {
//							System.out.print(arr[j][k]+" ");
//						}
//						System.out.println();
//					}
					break;
				
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	public static void action_1(int[][] arr, int N, int M) {
		//상하 반전. N 절반만큼.
		int num = (int)N / (int)2;
		
		for (int i=0 ; i<num ; i++) {
			//arr[i] swap arr[N-1-i]
//			int[] temp = Arrays.copyOf(arr[i], M);
			int[] temp = new int[M];
			System.arraycopy(arr[i], 0, temp, 0, M);

			for(int j=0; j<M; j++) {
				//위 라인
				arr[i][j] = arr[N-1-i][j];
				//아래 라인
				arr[N-1-i][j] = temp[j];
			}
		}
		
	}
	public static void action_2(int[][] arr, int N, int M) {
		//좌우 반전. 각 줄을 reverse
		int num = (int)M / (int)2;
		for (int i=0; i<N ; i++) {
			for(int j=0; j<num; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][M-1-j];
				arr[i][M-1-j] = temp;
			}
		}
		
		
		
	}
	public static void action_3(int[][] arr, int N, int M, int[][] res) {
		//90도 시계방향.
		//N x M 배열이   M x N 배열이 되어야함.
		/*
		 *  a o o o o o		   d c b a
		 *  b o o o o o		   o o o o
		 *  c o o o o o   >>   o o o o
		 *  d o o o o o        o o o o
		 *                     o o o o
		 *                     o o o o
		 * 
		 * 
		 */
		//00 10 20 30      01 11 21 31
		int x_idx = 0;
		int y_idx = 0;
		for(int i=0; i<M; i++) {
			for(int j=N-1; j>=0; j--) {
				res[x_idx][y_idx] = arr[j][i];
				y_idx += 1;
			}
			x_idx += 1;
			y_idx = 0;
		}
	
	}
	public static void action_4(int[][] arr, int N, int M, int[][] res) {
		//90도 반시계방향.
		int x_idx = 0;
		int y_idx = 0;
		for(int i=M-1; i>=0; i--) {
			for(int j=0; j<N; j++) {
				res[x_idx][y_idx] = arr[j][i];
				y_idx += 1;
			}
			x_idx += 1;
			y_idx = 0;
		}
	}
	public static void action_5(int[][] arr, int N, int M) {
		//그룹 시계 회전
		//1번 : arr[0~n-1][0~m-1]
		//2번 : arr[0~n-1][m~M-1]
		//3번 : arr[n~N-1][m~M-1]
		//4번 : arr[n~N-1][0~m-1]
		int n = (int)N/(int)2;
		int m = (int)M/(int)2;		
		int[] dx = {0, n, n, 0};  //1 4 3 2 사분면
		int[] dy = {0, 0, m, m};
		

		
		int[] temp = new int[m];
		for (int i=0; i<n; i++) {
			//일단 1사분면  i번째 줄 temp로 복사.
			System.arraycopy(arr[i], 0, temp, 0, m);
			//1 << 4 , 4 << 3,  3<< 2,  2 << temp
			for(int idx=0; idx<3; idx++) {
				for(int j=0; j<m; j++) {
					arr[i+dx[idx]][j+dy[idx]] = arr[i+dx[idx+1]][j+dy[idx+1]];
				}
			}
			for(int j=0; j<m; j++) {
				arr[i+dx[3]][j+dy[3]] = temp[j];
			}
			
		}
	
	}
	public static void action_6(int[][] arr, int N, int M) {
		//그룹 반시계 회전
		
		int n = (int)N/(int)2;
		int m = (int)M/(int)2;		
		int[] dx = {0, 0, n, n};  //1 2 3 4 사분면
		int[] dy = {0, m, m, 0};
		

		
		int[] temp = new int[m];
		for (int i=0; i<n; i++) {
			//일단 1사분면  i번째 줄 temp로 복사.
			System.arraycopy(arr[i], 0, temp, 0, m);
			//1 << 2 , 2 << 3,  3<< 4,  4 << temp
			for(int idx=0; idx<3; idx++) {
				for(int j=0; j<m; j++) {
					arr[i+dx[idx]][j+dy[idx]] = arr[i+dx[idx+1]][j+dy[idx+1]];
				}
			}
			for(int j=0; j<m; j++) {
				arr[i+dx[3]][j+dy[3]] = temp[j];
			}
			
		}
	
	}

}
