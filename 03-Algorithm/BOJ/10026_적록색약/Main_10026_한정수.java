import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10026_한정수 {
	static int N;
	static char[][] arr;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int[] point;
	static int area;
	static int area_RG;
	
	static int[] dx = {1, 0, -1, 0}; //아래 우 위 좌
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new char[N][N];
		visited = new boolean[N][N];
		point = new int[2];
		queue = new ArrayDeque<>();
		
		
		int[] pop_one = new int[2];
		
		String temp;
		for (int i=0; i<N ; i++) {
			temp = br.readLine();
			for (int j=0; j<N ; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}
		
		//bfs 써보자. arr[][] i~j 탐색하면서 (이미 탐색됬으면 continue 박고.)
		//상하좌우 탐색하고 큐. 탐색하면서 계속 방문 체크하고.
		int nx;
		int ny;
		char first_char;
		area = 0;
		for (int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (visited[i][j]) {
					continue;
				}
				else {
					bfs(i, j);
				}
				
			}
		}
		//다시 초기화.
		for(int i=0; i<N ; i++) {
			Arrays.fill(visited[i], false);			
		}
		area_RG = 0;
		
		//적록색약
		for (int i=0; i<N; i++) {
			for(int j=0; j<N ; j++) {
				if (visited[i][j]) {
					continue;
				}
				else {
					bfs_RG(i, j);
				}
			}
		}
		System.out.println(area+" "+area_RG);
		
		
	}
	public static void bfs(int start_x, int start_y) {
		queue.add(new int[] {start_x, start_y});
		visited[start_x][start_y] = true;
		char first_char = arr[start_x][start_y];
		int nx;
		int ny;
		while(!queue.isEmpty()) {
			point = queue.poll();
			first_char = arr[point[0]][point[1]];
			for (int idx=0; idx<4; idx++) {
				nx = point[0] + dx[idx];
				ny = point[1] + dy[idx];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					//배열 밖으로 나갔으면 그 방향은 아님.
					continue;
				}
				if(!visited[nx][ny] && first_char == arr[nx][ny]) {
					//아직 방문한 위치가 아니고, 동일한 영역에 해당하면 큐에 넣는다
					queue.add(new int[] {nx, ny});
					//방문체크 하고.
					visited[nx][ny] = true;
				}
				
			}
			
		}//while 끝, 즉 1개 영역 탐색 끝.
		area += 1;
	}
	
	public static void bfs_RG(int start_x, int start_y) {
		queue.add(new int[] {start_x, start_y});
		visited[start_x][start_y] = true;
		char first_char = arr[start_x][start_y];
		int nx;
		int ny;
		while(!queue.isEmpty()) {
			point = queue.poll();
			first_char = arr[point[0]][point[1]];
			for (int idx=0; idx<4; idx++) {
				nx = point[0] + dx[idx];
				ny = point[1] + dy[idx];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					//배열 밖으로 나갔으면 그 방향은 아님.
					continue;
				}
				if (first_char == 'R' || first_char == 'G') {
					if(!visited[nx][ny] && (arr[nx][ny] == 'R' || arr[nx][ny] == 'G') ) {
						//아직 방문한 위치가 아니고, 동일한 영역에 해당하면 큐에 넣는다
						queue.add(new int[] {nx, ny});
						//방문체크 하고.
						visited[nx][ny] = true;
					}
				}
				else {
					//first_char == 'B'
					if(!visited[nx][ny] && first_char == arr[nx][ny]) {
						//아직 방문한 위치가 아니고, 동일한 영역에 해당하면 큐에 넣는다
						queue.add(new int[] {nx, ny});
						//방문체크 하고.
						visited[nx][ny] = true;
					}
				}
				
				
			}
			
		}//while 끝, 즉 1개 영역 탐색 끝.
		area_RG += 1;
	}
}
