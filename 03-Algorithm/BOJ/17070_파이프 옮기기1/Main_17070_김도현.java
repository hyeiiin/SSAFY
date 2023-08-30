import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_김도현 {

	static int N,result,home[][];
	static int dir[] = {0,1,2}; // 가로 , 세로 , 대각
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		home = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		dfs(0,1,0);
		System.out.println(result);
	}
	public static void dfs2(int x, int y, int dir) {
		if(x==N-1 && y==N-1) {
			result++;
			return;
		}
		if(dir==0) {
			for (int i = 0; i < 2; i++) {
				if(i==0) {
					int nx = x;
					int ny = y+1;
					if(isValid(nx, ny,0)) {
						dfs(nx,ny,0); // dir은 우측
					}
				}else if(i==1) {
					int nx = x+1;
					int ny = y+1;
					if(isValid(nx, ny,2)) {
						dfs(nx,ny,2); // dir은 대각선
					}
				}
			}
		}else if(dir==1) {
			for (int i = 0; i < 2; i++) {
				if(i==0) {
					int nx = x+1;
					int ny = y;
					if(isValid(nx, ny,1)) {
						dfs(nx,ny,1); // dir은 하단
					}
				}else if(i==1) {
					int nx = x+1;
					int ny = y+1;
					if(isValid(nx, ny,2)) {
						dfs(nx,ny,2); // dir은 대각선
					}
				}
			}
		}else if(dir==2) {
			for (int i = 0; i < 3; i++) {
				if(i==0) {
					int nx = x;
					int ny = y+1;
					if(isValid(nx, ny,0)) {
						dfs(nx,ny,0); // dir은 우측
					}
				}else if(i==1) {
					int nx = x+1;
					int ny = y;
					if(isValid(nx, ny,1)) {
						dfs(nx,ny,1); // dir은 하단
					}
				}else if(i==2) {
					int nx = x+1;
					int ny = y+1;
					if(isValid(nx, ny,2)) {
						dfs(nx,ny,2); // dir은 대각선
					}
				}
			}
		}
		
	}
	
	public static void dfs(int x, int y, int dir) {
		if(dir==0) {
			dfs2(x,y,0);
		}else if(dir==1) {
			dfs2(x,y,1);
		}else if(dir==2) {
			dfs2(x,y,2);
		}
	}
	
	public static boolean isValid(int x, int y, int dir) {
		if(dir==0) {
			if(isValid2(x, y)) {
				return true;
			}
		}else if(dir==1) {
			if(isValid2(x, y)) {
				return true;
			}
			
		}else if(dir==2) {
			if(isValid2(x, y) && isValid2(x, y-1) && isValid2(x-1, y)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValid2(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<N && home[x][y]==0) {
			return true;
		}
		return false;
	}
}
