import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17281_야구 {
	/**
	 * 백준 17281 야구 (https://www.acmicpc.net/problem/17281)
	 */
	private static int n;
	private static int[][] map;
	private static boolean[] visit;
	
	private static int[] player;
	
	private static int result = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(reader.readLine());
		map = new int[n+1][10]; //n개의 이닝별 각 타순에서 점수
		
		StringTokenizer st;
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(reader.readLine());
			for (int j=1; j<=9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[10]; //각 타순에 누가 설지 정해졌는지 체크하기 위한 배열
		player = new int[10]; //각 타순에 타석에 설 순서를 저장할 배열 index1 --> 첫번째 칠 타자 번호
		
		visit[4] = true; //4번 타자는 정해져 있으니까 방문처리
		player[4] = 1; //언제나 1번이 4번타자
		
		dfs(2);
		
		System.out.println(result);
		
	}
	/**
	 * 8명(4번타자 제외)의 타순 정하기
	 * @param count 타순을 정할 타자번호
	 */
	private static void dfs(int count) {

		if (count == 10) { //9명 다 정하고 넘어왔으면.
			//순서 조합 끝(순열)
			//경기 시작
			play();
			
		} else {
			for (int i=1; i<=9; i++) {
				if (!visit[i]) { //이미 타순에 설 타자가 정해져 있는지 확인
					visit[i] = true; //타순 선수 배치 
					player[i] = count; //선수 번호 넣기
					dfs(count+1); //다음 선수 어떤 타순에 세울지 정하러가기
					visit[i] = false; //다른 선수 배치해보기 위해서 방문 해제
				}
			}
		}
		
	}//dfs

	private static void play() {
		
		int score = 0;
		int startPlayer = 1;
		boolean[] base;
		
		for (int i=1; i<=n; i++) {//n번의 이닝동안 돌리기
			int outCnt = 0;
			base = new boolean[4];
			
			finish : while(true) {
				for (int j=startPlayer; j<=9; j++) { 
					int hitter = map[i][player[j]]; //현재 타순에 선 선수의 상태 가져오기
					
					switch (hitter) {
					case 0: //아웃
						outCnt++;
						break;
					case 1: //1루타
						for (int k=3; k>=1; k--) { 
							if (base[k]) {
								if (k==3) { //3번에 주자가 있는 상태면 홈으로 들어와야함
									score++; //홈으로 들어왔으므로 점수 추가
									base[k] = false; //3루 떠났기 때문에 비우기
									continue;
								}
								//나가있는 타자들 1루씩 진루
								base[k] = false;
								base[k+1] = true;
							}
							
						}
						//안타 친 타자 1루로 진루
						base[1] = true;
						break;
					case 2: //2루타
						for (int k=3; k>=1; k--) {
							if (base[k]) {
								if (k==3 || k==2) {//2루,3루에 있던 타자들 홈으로
									score++; //점수내고
									base[k] = false; //2,3루 비우기
									continue;
								}
								//1루는 3루로 진루
								base[k] = false;
								base[k+2] = true;
							}
						}
						//타자는 2루로 진루
						base[2] = true;
						break;
					case 3: //3루타
						for (int k=1; k<=3; k++) {
							if (base[k]) {//돌면서 모두 홈으로
								score++;
								base[k] = false;
							}
						}
						//타자만 3루로
						base[3] = true;
						break;
					case 4: //홈런
						for (int k=1; k<=3; k++) {
							if (base[k]) {//다 들어오기
								score++;
								base[k] = false;
							}
						}
						//타자도 점수 내기
						score++;
						break;
					}

					if (outCnt == 3) { //out횟수 체크 필수!!
						startPlayer = j + 1; //다음 이닝에 타석에 설 타자 셋팅
						if (startPlayer == 10) {
							startPlayer = 1;
						}
						break finish; //finish라벨 있는 쪽으로 탈출 후 다음 이닝 시작
					}
				}
				//이닝이 안끝났는데 타자들 끝까지 다 쳤으면 다시 1번타자부터 시작하기
				startPlayer = 1;
			}
		}
		//점수 갱신
		result = Math.max(result, score);
		
	}//play
}