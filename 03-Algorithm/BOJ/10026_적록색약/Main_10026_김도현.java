import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_10026_김도현 {

	
	static int N,res,res2;
	static char[][] map,map2;
	static boolean[][] visited,visited2;
	static int[] dx = {-1,1,0,0}; // 상 하 좌 우
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		map2 = new char[N][N];
		visited = new boolean[N][N];
		visited2 = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if(str.charAt(j)=='G') {
					map2[i][j] = 'R';
				}else {
					map2[i][j] = str.charAt(j);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					res++;
					bfs(i,j,map[i][j]);
				}
				if(!visited2[i][j]) {
					res2++;
					bfs2(i,j,map2[i][j]);
				}
			}
		}
		System.out.println(res+" "+res2);
	}
	
	public static void bfs(int x, int y, char color) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y,(int)color});
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int[] current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				if(check(nx, ny) && !visited[nx][ny]) {
					if(map[nx][ny]==(char)(current[2])) {
						visited[nx][ny] = true;
						q.add(new int[] {nx,ny,map[nx][ny]});
					}
				}
			}
		}
	}
	
	public static void bfs2(int x, int y, char color) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y,(int)color});
		visited2[x][y] = true;
		while(!q.isEmpty()) {
			int[] current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				if(check(nx, ny) && !visited2[nx][ny]) {
					if(map2[nx][ny]==(char)(current[2])) {
						visited2[nx][ny] = true;
						q.add(new int[] {nx,ny,map2[nx][ny]});
					}
				}
			}
		}
	}
	
	public static boolean check(int x, int y) {
		if(x>=0 && y>=0 && x < N && y< N) {
			return true;
		}
		return false;
	}

}
