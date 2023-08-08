import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		int axis;
		if (N <= M) {
			axis = N;
		}
		else {
			axis = M;
		}
		
		int bound_num = (int)axis / (int)2;
		for (int i=0; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotate(arr, R, bound_num, N, M);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		
		

	}

	public static void rotate(int[][] arr, int revolve_num, int bound_num, int N, int M) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0}; //오른쪽 아래 왼쪽 위
		int temp = -1;
		int index = 0;
		int x = -1;
		int y = -1;
		int count = 0;
		for (int r=0 ; r<revolve_num; r++) {
			for (int i=0; i<bound_num; i++) {
				//bound_num은 즉 테두리 넘버. 외곽에서 부터 각 bound의 시작은 (i,i)임.
				count = 0;
				index = 0;
				while(true) {
					if (count==0) {
						x = i;
						y = i;
						temp = arr[x][y];
						count = 1;
					}
//					System.out.println("x : "+x+" y : "+y);
					
					if(x+dx[index] < 0+i || x+dx[index] >=N-i || y+dy[index] < 0+i || y+dy[index] >= M-i) {
						index += 1;
//						System.out.println("index+=1");
					}
					
					if(x+dx[index] == i && y+dy[index] == i) {
						//맨처음 지점으로 돌아오면? 마무리.
						arr[x][y] = temp;
//						System.out.println("break");
						break;
					}

					
					arr[x][y] = arr[x+dx[index]][y+dy[index]];
					x += dx[index];
					y += dy[index];
						
					
					
					
				}
				
				
				
			}
			
		}
		
	}
}
