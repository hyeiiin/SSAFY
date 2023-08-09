
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//38432KB, 1132ms
public class Main_16926_배열돌리기1_2 {

	static int N,M,R;	
	static int P;	
	static int[][] arr;	
	static boolean[][] visited;	
	//우 하 좌 상 델타
	public static int [] dr = {0, 1, 0, -1};
	public static int [] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		R = Integer.parseInt(st.nextToken());	
		
		arr = new int[N][M];	
		visited = new boolean[N][M];	
		//배열 정보 입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = Math.min(N, M)/2;	
		int nN=N;
		int nM=M;
		for(int i=0;i<cnt;i++) {
			P = i;	
			rotate(i,i, 1);	
		}
		
		
		for(int[] a:arr) {
			for(int el: a ) {
				sb.append(el+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
	/**
	 * 
	 * @param r : 시작 인덱스
	 * @param c : 시작 인덱스
	 * @param rnum : 로테이션 횟수
	 */
	private static void rotate(int r, int c, int rnum) {
		int nr;
		int nc;
		
		int start = arr[r][c]; 
		int dir = 0;	
		while(true) {
			nr = r + dr[dir]; //이동할 좌표 
			nc = c + dc[dir];
			
			//경계를 벗어났거나, 테두리의 값인지? 방향전환
			if(nr<0 || nr>=N || nc<0 || nc>=M  || visited[nr][nc]) {
				dir = ++dir%4;
				nr = r + dr[dir];//방향전환된 위치를 다시 셋팅 필요
				nc = c + dc[dir];
			}

			arr[r][c]=arr[nr][nc]; 
			r=nr; 
			c=nc;
			
			
			if(R==rnum) visited[r][c] = true;

			if(r==P && c==P) {
				arr[P+1][P] = start; 
				break;
			}
		}
		if(rnum<R) rotate(r,c,rnum+1);
		
	}

}
