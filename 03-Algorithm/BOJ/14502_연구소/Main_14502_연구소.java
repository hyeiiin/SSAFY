import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 안전영역의 최댓값을 구하기 위해 3개의 벽세우기
 * 1. 입력 받기 
 * 	바이러스 위치 별도 관리 
 * 2. 벽 세우기(조합)
 * 	- 빈칸 중 3개를 뽑아 벽세우기
 * 3. 벽이 세워지면 바이러스 확산 시키기 
 * 	- BFS or DFS로 확산
 * 	- 확산 이전 원본값은 보존해야함
 * 4. 안전 영역 최댓값 갱신
 * 5. 출력
 *
 */
public class Main_14502_연구소{
	//바이러스의 위치 정보 관리를 위해서 클래스로 만들기
	static class Virus{
		int r,c;
		public Virus(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Virus [x=" + r + ", y=" + c + "]";
		}
	}
	static int N,M; //연구소의 크기 N,M
	static int map[][],MAX=0,temp[][]; //연구소 원본 상태 map, 임시 배열 temp, 최대값 저장을 위한 MAX
	//4방 탐색을 위한 델타
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		temp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		//-----------INPUT END
		tryWall(0,0,0);
//		tryWall(0,0);
		System.out.println(MAX);
	}
	
	
	/**
	 * 연구소에 3개의 벽 세우기
	 * - whk
	 * @param x row 좌표
	 * @param y col 좌표
	 * @param cnt : 벽 세운 개수
	 */
	private static void tryWall(int x, int y, int cnt) {
		if(cnt == 3) { //벽 3개 다 세웠으면 = 조합 완성
			for (int i = 0; i < N; i++) { //배열 복사
				for (int j = 0; j < M; ++j) {
					temp[i][j] = map[i][j];
				}
			}
			int result = bfs(temp); // 벽세웠으면 바이러스 퍼뜨리기 
			if(MAX < result) MAX= result; // 안전 영역 갱신
			return;
		}
		
		//연구소 전체를 확인하면서 빈칸이면 벽 세우기
		//이전에 벽을 세웠던 다음줄부터 벽 세우기.
		// x,y부터 시작하면 row의 값이 다음으로 넘어가도 column은 y위치부터 확인. 따라서 0부터 M까지 확인해줘야함
		// row는 이전 위치를 고려할 필요 없으므로 x~N까지 고려하면 됨
        for (int r = x; r < N; r++) {
            for (int c = 0; c < M; c++) {
    			if(map[r][c]==0) { // 빈칸
    				map[r][c] ^= 1;	//벽세우기				
    				tryWall(r,c,cnt+1); //다음 벽 세우러 가기
    				map[r][c] ^= 1; //벽 세운거 해제
    			}
            }
        }
	}



	private static void tryWall(int start,int cnt) {
		//1036ms, 307468kb
//		for (int x1=0; x1<N; x1++) { //1번째 벽 찾기
//            for (int y1=0; y1<M; y1++) {
//                if (map[x1][y1] != 0) continue;
//                
//                for (int x2=0; x2<N; x2++) {//2번째 벽 찾기
//                    for (int y2=0; y2<M; y2++) {
//                        if (map[x2][y2] != 0) continue;
//                        
//                        for (int x3=0; x3<N; x3++) {//3번째 벽 찾기
//                            for (int y3=0; y3<M; y3++) {
//                                if (map[x3][y3] != 0) continue;
//                                //같은 곳에 벽이 세워질 수 없으므로 continue;
//                                if (x1 == x2 && y1 == y2) continue;
//                                if (x1 == x3 && y1 == y3) continue;
//                                if (x2 == x3 && y2 == y3) continue;
//                                //각 위치에 벽 세우기
//                                map[x1][y1] = 1;
//                                map[x2][y2] = 1;
//                                map[x3][y3] = 1;
//                                
//                                //배열 복사
//                    			for (int i = 0; i < N; i++) {
//                    				for (int j = 0; j < M; ++j) temp[i][j] = map[i][j];
//                    			}
//                    			//확산 시키기
//                                int cur = bfs(temp);
//                                //갱신
//                                if (MAX < cur) MAX = cur;
//                                //벽 세운거 해제
//                                map[x1][y1] = 0;
//                                map[x2][y2] = 0;
//                                map[x3][y3] = 0;
//                            }
//                        }
//                    }
//                }
//            }
//        }
		
		//484ms, 128012kb
		if(cnt == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; ++j) temp[i][j] = map[i][j];
			}
			int result = bfs(temp); // 벽세웠으면 바이러스 퍼뜨리기 
			if(MAX < result) MAX= result; // 안전 영역 갱신
			
			return;
		}
		
		for (int i = start; i < N*M; i++) {
			int r = i/M;
			int c = i%M;
			if(map[r][c]==0) { // 빈칸
				map[r][c] ^= 1;					
				tryWall(i+1,cnt+1);
				map[r][c] ^= 1;
			}
		}


	}

	private static int bfs(int[][] map) {
		Queue<Virus> queue = new LinkedList<Virus>();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]>1) queue.offer(new Virus(i,j)); 
			}
		}
		
		int nr=0,nc=0;
		Virus cur = null;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				nr = cur.r + dr[d]; 
				nc = cur.c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]<1) { 
					map[nr][nc] = 2; 
					queue.offer(new Virus(nr,nc));
				}
			}
		}
		
		
		int count = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]<1) count++;
			}
		}
		return count;
	}
}
