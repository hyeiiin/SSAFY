
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1
7
19 26 34 12 25 48 40 
32 43 49 39 28 44 1 
33 14 8 15 23 6 38 
3 21 24 13 9 7 41 
42 17 31 4 45 27 18 
20 29 47 22 30 5 2 
16 46 10 37 36 35 11 

 */
/*
 30,208 kb 145 ms
 */

public class Solution_1861_정사각형방 {

	static int N,MAX,start;
	static int map[][]; 
	
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	static int memo[][]; 
	public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//입력 처리
        int TC = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		for(int t=1; t<=TC; ++t) {
			N = Integer.parseInt(in.readLine()); //방의 크기
			map = new int[N][N]; //방의 정보
			memo = new int[N][N]; // 카운트 횟수 저장
			MAX = 0; //결과를 담는 변수
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//---------INPUT END------------------------ 
			int count = 0; 
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					
					if(memo[i][j]==0) { //출발지 i,j를 기준으로 한번도 계산된적이 없을 경우만
						//방문횟수를 계산하는 부분
						count = go(i,j);
						
						if(count>MAX) { // 내가 가진 수가 Max 큰지 확인
							MAX = count;
							start = map[i][j]; //시작 위치 가지고 감
						}else if(count==MAX) { // 최대 방의 크기가 같은 경우, 출발지의 숫자 값이 가장 작은
							// 값으로 바꿔 줌
							if(start>map[i][j]) start = map[i][j];
						}
					}
				}
			}
			System.out.println("#"+t+" "+start+" " +MAX);
		}
		in.close();
	}

	/**
	 * 
	 * @param r : 출발 위치
	 * @param c
	 * @return : 방의 개수를 계산해서 리턴
	 */
	private static int go(int r, int c) {
		
		int nr,nc,res=0;
		for(int d=0; d<4; ++d) { 
			nr = r + dr[d];
			nc = c + dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<N) { //경계를 벗어나지 않는지 확인
				if(map[nr][nc]==map[r][c]+1) { // 정확히 나보다 1큰지 확인
					res = go(nr,nc); //다음방으로 이동하도록 재귀 호출
					break; //재귀가 리턴 되었다? 갈 수 있는 끝까지 간 다음 리턴
					//4방 중 현 위치 기준으로 +1 하나 밖에 없음. 그대로 빠져나가 줌.
				}
			}
		}
		//여기 까지 내려왔다? 위에 상황을 만족하지 못했다는 이야기 --> 현 위치를 기준으로 이동 가능한 방 없음
		//즉, 첫 출발지 위치 기준으로 갈 수 있는 끝까지 가본 상태

		return memo[r][c] = res+1; // 현재 카운팅 한 수가 이동횟수, 방의 수는 +1해줘야함
	}
}
