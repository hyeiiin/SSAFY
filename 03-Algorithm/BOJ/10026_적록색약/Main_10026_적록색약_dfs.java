import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10026_적록색약_dfs {
	static int N;
	static char[][] map;
	static int[] dr = {-1,0,1,0}; //상,우,하,좌
	static int[] dc = {0,1,0,-1};
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i]= br.readLine().toCharArray();
		}
		
		int notCnt = find(0);
		
		//적록색약인 사람 방문하기위해서 값 변경하기 'G'->'R'로
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <N; j++) {
				if(map[i][j]=='G')
					map[i][j]='R'; 
			}
		}
		int yesCnt=find(0);//적록색약인 사람
		
		System.out.println(notCnt+" "+yesCnt);

	}
	
	static private int find(int cnt) {
		visited = new boolean[N][N]; //dfs 방문 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					cnt++; //영역 수 추가
					dfs(i,j);
				}
			}
		}
		return cnt;
	}
	
	static private void dfs(int r, int c) {
		visited[r][c]=true;
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0||nc<0||nr>=N||nc>=N||visited[nr][nc])continue;
			if(map[r][c]==map[nr][nc]) //같은 색인 애들만 방문하기
			dfs(nr,nc);
		}
	}

}
