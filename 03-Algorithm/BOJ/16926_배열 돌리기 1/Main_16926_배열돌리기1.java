
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//37160kb, 1056ms
public class Main_16926_배열돌리기1 {

	static int N,M,R;	
	static int P;	
	static int[][] arr;	
	static boolean[][] visited;	
	// 하 우 상 좌
	final static int[] dr = {1, 0, -1, 0};
	final static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 배열의 행크기 입력
		M = Integer.parseInt(st.nextToken());	//배열의 열크기 입력 
		R = Integer.parseInt(st.nextToken());	// 로테이션 횟수
		
		arr = new int[N][M];	//배열 생성
		visited = new boolean[N][M];
		//배열 정보 입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = Math.min(N, M)/2;	
		
		
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
		// 이동할 좌표
		int nr;
		int nc;
		
		int temp = arr[r][c];	
		int temp2 = 0;	
		int dir = 0;
		
		while(true) {
			//이동할 좌표 
			nr = r + dr[dir];
			nc = c + dc[dir];

			if(nr<0 || nr>=N || nc<0 || nc>=M  || visited[nr][nc]) {
				dir = ++dir%4;
			}
	
			r += dr[dir]; 
			c += dc[dir];

			temp2 = arr[r][c];	
			arr[r][c] = temp;
			temp = temp2;
			
			if(R==rnum) visited[r][c] = true; 
			if(r==P && c==P) break;
		}
		if(rnum<R) rotate(r,c,rnum+1);
		
	}

}
