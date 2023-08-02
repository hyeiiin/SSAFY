import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] input_arr = new int[N][N];
		int[][] sum_arr = new int[N][N];
		
		//sum 배열 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(i == 0 && j == 0) { // 0,0
					sum_arr[i][j] = Integer.parseInt(st.nextToken());
				}
				else if(i != 0 && j == 0) { // 0,0 제외 첫 col
					sum_arr[i][j] = sum_arr[i-1][j] + Integer.parseInt(st.nextToken());
				}
				else if(i == 0 && j != 0) { // 0,0 제외 첫 row
					sum_arr[i][j] = sum_arr[i][j-1] + Integer.parseInt(st.nextToken());
				}
				else {
					sum_arr[i][j] = sum_arr[i][j-1] + sum_arr[i-1][j] - sum_arr[i-1][j-1] + Integer.parseInt(st.nextToken());
				}
				
			}
		}
		
		//부분합 계산
		for (int i=0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1,y1,x2,y2;
			x1 = Integer.parseInt(st.nextToken()) -1;
			y1 = Integer.parseInt(st.nextToken()) -1;
			x2 = Integer.parseInt(st.nextToken()) -1;
			y2 = Integer.parseInt(st.nextToken()) -1;
			
			if(x1 == 0 && y1 == 0) {
				System.out.println(sum_arr[x2][y2]);
			}
			else if(x1 == 0) {
				System.out.println(sum_arr[x2][y2] - sum_arr[x2][y1-1]);
			}
			else if(y1==0) {
				System.out.println(sum_arr[x2][y2] - sum_arr[x1-1][y2]);
			}
			else {
				System.out.println(sum_arr[x2][y2] - sum_arr[x1-1][y2] - sum_arr[x2][y1-1] + sum_arr[x1-1][y1-1]);
			}
			
		}
	}

}

// 1  2  3  4
// 2 !3 !4 !5        2,2 부터 3,4까지. >> (3,4) - (1,4) - (3,1) + (1,1)
// 3 !4 !5 !6						>> (x2,y2) - (x1-1,y2) - (x2,y1-1) + (x1-1,y1-1)
// 4  5  6  7
 