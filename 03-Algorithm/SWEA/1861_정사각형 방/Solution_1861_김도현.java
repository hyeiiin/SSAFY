import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution_1861_김도현 {

	public static int T,N,max,index;
	public static int [][] arr;
	public static boolean [][] visited;
	public static int [] dx = {1,0,-1,0};
	public static int [] dy = {0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			max = Integer.MIN_VALUE;
			index = Integer.MAX_VALUE;
			arr = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i,j,1,arr[i][j]);
				}
			}
			System.out.println("#"+test_case+" "+index+" "+max);
		}
		
		
	}
	public static void dfs(int x, int y, int count, int start) {
		
		if(!visited[x][y]) {
			visited[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x +dx[i];
				int ny = y +dy[i];
				if(check(nx, ny)) {
					if(arr[nx][ny]==arr[x][y]+1) {
						dfs(nx,ny,count+1,start);
						visited[x][y]=false;
					}
				}
			}
		}
		if(max<count) {
			index = start;
		}else if(max==count) {
			index = Math.min(index,start);
		}
		max = Math.max(max, count);
	}
	
	public static boolean check(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<N) {
			return true;
		}
		return false;
	}
}
