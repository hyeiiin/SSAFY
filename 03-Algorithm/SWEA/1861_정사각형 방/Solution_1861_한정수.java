import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_한정수 {
	static int max = -1;
	static int max_x = 0;
	static int max_y = 0;
	static int arr_min = 1000001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		
		for (int test_case=1 ; test_case<=T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			int count = 0;
			//배열입력
			for (int i=0; i<N ; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					dfs(arr, i, j, i, j, N, visited, 1);
				}
			}
			
			
			//배열입력 끝 =====================
			
			// 시작하기 전에 전역변수 초기화. temp_count = 0; max = -1;
			// 배열의 각 element를 일일이 돌면서.. dfs? bfs?  dfs.
			// dfs(node) 실행. >> visited[node] = true
			// 각 방향을 보면서 dx, dy.  >> 배열 밖으로 나가는지 체크하면서
			//   >> dx,dy 결과 배열 밖인가? 혹은 visited[dx][dy] == true ??  >> 종료
			//   >> 그 방향에 있는 애가 +1인가? >> dfs(node+dx+dy) 및 temp_count += 1
			System.out.printf("#%d %d %d", test_case, arr_min, max);
			System.out.println();
			max = -1;
			max_x = 0;
			max_y = 0;
			arr_min = 1000001;
			//다음 테케 대비 전역변수 초기화

			
			

		}
		
	}
	public static void dfs(int[][] arr, int origin_x, int origin_y, int x, int y,int N, boolean[][] visited, int count) {

		
		int[] dx = {0, 1, 0, -1}; //우 하 좌 상
		int[] dy = {1, 0, -1, 0};
		boolean all_searched = false;
//		int d_idx = 0;
		
		//현재노드 방문 true.
		visited[x][y] = true;
		
		for (int d_idx=0; d_idx<4; d_idx++) {
			int nx = x+dx[d_idx];
			int ny = y+dy[d_idx];
			//배열을 나갔나?
			if(nx < 0 || nx >= N || ny < 0 || ny >= N ) {
				//지금 까지의 최댓값 결과를 갱신
				if (max <= count) {
					if(max < count) {
						max = count;
						max_x = origin_x;
						max_y = origin_y;
						arr_min = arr[origin_x][origin_y];

					}
					else {
						//max == count 이면, arr[x][y]가 더 작은애로 갱신해야함.
						if(arr_min > arr[origin_x][origin_y]) {
							max = count;
							max_x = origin_x;
							max_y = origin_y;
							arr_min = arr[origin_x][origin_y];

						}
					}			
				}
				continue;
			}
			//이미 방문 했나?
			if(visited[nx][ny]) {
				//지금 까지의 최댓값 결과를 갱신
				if (max <= count) {
					if(max < count) {
						max = count;
						max_x = origin_x;
						max_y = origin_y;
						arr_min = arr[origin_x][origin_y];

					}
					else {
						if(arr_min > arr[origin_x][origin_y]) {
							max = count;
							max_x = origin_x;
							max_y = origin_y;
							arr_min = arr[origin_x][origin_y];

						}
					}			
				}
				continue;
			}
			//그 놈이 나보다 1 큰가?
			if(arr[nx][ny] == arr[x][y]+1) {
				all_searched = false;
				//그 1 큰놈 기준으로 다시 dfs.
				dfs(arr, origin_x, origin_y, nx, ny, N, visited, count+1);
			}
			else {
				//지금 까지의 최댓값 결과를 갱신
				if (max <= count) {
					if(max < count) {
						max = count;
						max_x = origin_x;
						max_y = origin_y;
						arr_min = arr[origin_x][origin_y];

					}
					else {
						if(arr_min > arr[origin_x][origin_y]) {
							max = count;
							max_x = origin_x;
							max_y = origin_y;
							arr_min = arr[origin_x][origin_y];

						}
					}			
				}
			}
			
			
		}
		
		
		//전부 끝나면 true로 바꾼 자리 다시 false로 바꿔주고.
		visited[x][y] = false;
		
		
	}

}
