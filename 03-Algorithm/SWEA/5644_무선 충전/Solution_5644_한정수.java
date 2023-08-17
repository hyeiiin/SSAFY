import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5644_한정수 {
	static int M;
	static int A;
	static int[] move_A;
	static int[] move_B;
	
	static int[] bat_x;
	static int[] bat_y;
	static int[] bat_c;
	static int[] bat_p;
	static int[][] board;
	
//	static int[] dx = {0, -1, 0, 1, 0};
//	static int[] dy = {0, 0, 1, 0, -1};
	// 0정지 1상 2우 3하 4좌 로 나와있는데, 문제에서 x,y를 뒤집어서 줬기 때문에 그에 따라 나도 뒤집음.
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	// 0 정지 1좌 2하 3우 4상
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		board = new int[10][10];
		
		for (int test_case=1 ; test_case<=T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			move_A = new int[M];
			move_B = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M ; i++) {
				move_A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M ; i++) {
				move_B[i] = Integer.parseInt(st.nextToken());
			}
			
			bat_x = new int[A+1];
			bat_y = new int[A+1];
			bat_c = new int[A+1];
			bat_p = new int[A+1];
			for (int i=1; i<=A; i++) {
				st = new StringTokenizer(br.readLine());
				bat_x[i] = Integer.parseInt(st.nextToken())-1;
				bat_y[i] = Integer.parseInt(st.nextToken())-1;
				bat_c[i] = Integer.parseInt(st.nextToken());
				bat_p[i] = Integer.parseInt(st.nextToken());
			}
			
			
			for (int i=0; i<10; i++) {
				Arrays.fill(board[i], 0);
			}
			//배열 입력 완료.
			
			//x,y, c가 주어지면. 
			//                    (x-c,y)
			//	     (x-c+1,y-1) (x-c+1,y) (x-c+1, y+1)
			// (x,y-c)  . ... ..    x, y       ~~   x, y+c
			// . . . .        
			//          . . . . . (x+c,y)
			
			
			//각 배터리를 찍는다 치고.
			// 겹치는 영역을 어떻게 케어하지? 음.... A는 최대 8개. 즉, 겹치는건 최대 8개.
			// board에 표시할때, 현재 배터리의 번호, 즉 for(int i=0; i<A ; i++)에서 i를 입력하되,
			// 해당 영역이 0이 아니라면, bat_p[board[x][y]] 값을 비교해서, 더 큰게 낮은 자릿수로 가도록.
			// 즉, 2번, 3번, 5번 배터리가 입력됬는데 파워가 2가 제일 크고, 5가 두번째로 클 경우, 그 자리의 board[x][y] = 52가 되어야함.
			// 겹친 영역이 3개 이상이라면, 어차피 사용자는 최대 2명이라 가장 파워가 큰 값 2개의 index정보만 저장하고 있으면 됨.
			
			// 나중에 A나 B가 어떤 배터리의 영역에 들어가면, board[x][y]의 값에서 10을 나눈 나머지를 구하고,
			// temp = board[x][y] % 10.  bat_p[temp] 이렇게 해서 파워 값을 받아올거임.
			// 똑같은 영역을 밟았을 경우, 그 사람은 temp2 = board[x][y]/10; , bat_p[temp2] 이렇게 될거고.
			
			// A가 밟은 지점의 배터리 영역이 1개일때 >> B가 밟은지점의 배터리 영역이 1개일때
			// A가 밟은 지점의 배터리 영역이 2개일때 >> B가 밟은지점의 배터리 영역이 1개일때
			// A가 밟은 지점의 배터리 영역이 1개일때 >> B가 밟은지점의 배터리 영역이 2개일때
			// A가 밟은 지점의 배터리 영역이 2개일때 >> B가 밟은지점의 배터리 영역이 2개일때.
			// 위 4가지 경우 전부에 대해 A와 B의 영역이 겹칠때와 안겹칠때를 싹 다 판별해줘야함.
			

			// 아, 사람이 A,B 두명 고정임. 그냥 visited[i]가 이미 true인데, 한번 더 visited[i]가 입력이 되면 ...?
			//    >> 아니면 어차피 board[x][y]에 2개 이상이 있으면 board[x][y] >= 11 이니까 이걸로 겹쳐있는지 아닌지 판별이 될듯. 
			//            >>그러고보니 01 은 1로 표시되니깐 배터리는 1부터 받아야겠다.
			
			
			//각 배터리 정보를 배열에 입력
			for(int i=1; i<=A; i++) {
				int x = bat_x[i];
				int y = bat_y[i];
				int c = bat_c[i];
				int p = bat_p[i];
				int count = 0;
				
				//위에 절반 기록
				for (int n = x-c ; n<x ; n++) {
					//배열 밖에 나갔나?======
					if(n < 0 ) {
						count += 1;
						continue;
					}
					if(n >= 10) {
						break;
					}
					//배열 밖에 나갔나? ===== 끝
//					board[n][y] = i;
					fill_board(n,y,i);
					for(int m = 1 ; m <= count ; m++) {
						//배열 나갔나?
						if(y+m >= 10) {
							break;
						}
//						board[n][y+m] = i;
						fill_board(n,y+m,i);
					}
					for(int m = 1 ; m <= count ; m++) {
						//배열 나갔나?
						if(y-m < 0) {
							break;
						}
//						board[n][y-m] = i;
						fill_board(n,y-m,i);
					}
					count += 1; 
				}
				//딱 가운데 줄 기록
				count = c;
				fill_board(x,y,i);
				for(int m=1 ; m<= count ; m++) {
					if(y+m >= 10) {
						break;
					}
					fill_board(x,y+m,i);
				}
				for(int m=1 ; m<= count ; m++) {
					if(y-m < 0) {
						break;
					}
					fill_board(x,y-m,i);
				}
				
				//나머지 아래 절반.
				count -= 1;
				for (int n = x+1 ; n <= x+c ; n++) {
					if(n < 0) {
						continue;
					}
					if(n >= 10) {
						break;
					}
//					board[n][y] = i;
					fill_board(n,y,i);
					for(int m = 1 ; m <= count ; m++) {
						if(y+m >= 10) {
							break;
						}
//						board[n][y+m] = i;
						fill_board(n,y+m,i);
					}
					for(int m = 1 ; m <= count ; m++) {
						if(y-m <0) {
							break;
						}
//						board[n][y-m] = i;
						fill_board(n,y-m,i);
					}
					count -= 1;
				}
			}
			//각 배터리 정보 배열에 입력 완료
			
			
//			for (int i=0; i<10 ; i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
			
			//이제 move_A move_B 하나씩 움직이면서, 그 위치에 해당하는 파워값을 받아옴.
			int A_x = 0;
			int A_y = 0;
			int B_x = 9;
			int B_y = 9;
			int power_sum = 0;

			
			for(int i=-1; i<M; i++) {
				//일단 초기 위치 (0,0) (9,9)부터 시작
				if (i == -1) {
					//초기 탐색.
					// 밑에 있는 일반적인 case에 대한 코드를 그대로 복붙한거라서 주석은 밑에만. 266line부터 시작.
					int visited = -1;
					int temp_A = board[0][0];
					int charge_A = 0;
					if (temp_A != 0) {
						if (temp_A < 11) {
							charge_A = bat_p[temp_A];
							visited = temp_A;
						}
						else {
							int mod = temp_A % 10;
							charge_A = bat_p[mod];
							visited = mod;
						}
					}
					int temp_B = board[9][9];
					int charge_B = 0;
					if(temp_B != 0) {
						if(temp_B < 11) {
							if(visited != temp_B) {
								charge_B = bat_p[temp_B];
							}
							else {
								if(temp_A > 11) {
									charge_A = bat_p[(int)temp_A / (int)10];
									charge_B = bat_p[temp_B];
								}
								else {
									charge_B = (int)bat_p[temp_B] / (int)2;
									charge_A = (int)charge_A / (int)2;
								}
								
							}
						}
						else {
							int mod1 = temp_B % 10;
							int mod2 = (int)temp_B / (int)10;
							if(visited == mod1) {
								if(temp_A < 11) {
									charge_B = bat_p[mod2];
								}
								else {
									int temp1 = temp_A / 10;
									int temp2 = temp_B / 10;
									if(bat_p[temp1] >= bat_p[temp2]) {
										charge_A = bat_p[temp_A/10];
										charge_B = bat_p[temp_B%10];
									}
									else {
										charge_A = bat_p[temp_A%10];
										charge_B = bat_p[temp_B/10];
									}
								}
								
							}
							else {
								charge_B = bat_p[mod1];
							}
							
						}
					}
					power_sum += charge_A + charge_B;
					continue;	
				}// if(i == -1) 끝======================
				
				
				//설마 A랑 B가 배열 밖으로 움직이게 주진 않을테니까?
				int A_nx = A_x + dx[move_A[i]];
				int A_ny = A_y + dy[move_A[i]];
				int B_nx = B_x + dx[move_B[i]];
				int B_ny = B_y + dy[move_B[i]];
				// 문제에선 0 정지 1상 2우 3하 4좌라고 했는데, 난 x,y 뒤집어서 받았기 때문에 그거에 맞춰서 해야됨.
				
				// >> 0 정지 1좌 2하 3우 4상 ==========
				
				int visited = -1;
				//board[nx][ny]에 대해 최댓값 판별해서 배터리 합 구하기.
				//일단 A부터.
				int temp_A = board[A_nx][A_ny];
				int charge_A = 0;
				if (temp_A != 0) {
					if (temp_A < 11) {
						//현재 A 위치에 오로지 배터리 1개 영역일때.
						charge_A = bat_p[temp_A];
						visited = temp_A;
					}
					else {
						//현재 A 위치가 2개의 배터리가 공존하는 영역일때.
						int mod = temp_A % 10;
						charge_A = bat_p[mod];
						visited = mod;
					}
				}
				
				//다음 B 방문할건데,
				//1. A와 배터리가 안겹칠때
				//2. 겹친다면
				//    2-1. >> 서로 다른 배터리인가.
				//    2-2. >> 같은 배터리인가.

				int temp_B = board[B_nx][B_ny];
				int charge_B = 0;
				if(temp_B != 0) {
					if(temp_B < 11) {
						if(visited != temp_B) {
							//서로 같은 배터리가 아니기 때문에 각각 더함.
							charge_B = bat_p[temp_B];
						}
						else {
							//만약 서로 같은 영역이라면, A가 두자리면 A가 다음 자리로 비켜줌.
							if(temp_A > 11) {
								charge_A = bat_p[(int)temp_A / (int)10];
								charge_B = bat_p[temp_B];
							}
							else {
								//A도 한자리면 반띵
								charge_B = (int)bat_p[temp_B] / (int)2;
								charge_A = (int)charge_A / (int)2;
							}
							
						}
					}
					else {
						//공존하는 영역이면.
						//A와 겹치는가
						int mod1 = temp_B % 10;
						int mod2 = (int)temp_B / (int)10;
						if(visited == mod1) {
							//A와 겹친다면, 다음 자릿수의 파워를 받는다.
							//어차피 두 자리고, 그 중 한 자리를 A가 먹고있으면 B가 비켜주는게 맞음.
							 //아닌가? 다 따져줘야하나?
							//아, 만약에 겹쳤을 떄 둘 다 두자리수면, 
							//예를 들어, A = 53, B = 43이면,  5와 4중 더 큰놈을 골라야함.
							if(temp_A < 11) {
								//A가 한자리면 그냥 B가 옮겨준다.
								charge_B = bat_p[mod2];
							}
							else {
								//A가 두자리면, 남은 한자리중 bat_p값이 큰놈을 찾는다.
								int temp1 = temp_A / 10;
								int temp2 = temp_B / 10;
								if(bat_p[temp1] >= bat_p[temp2]) {
									//A가 더 크면, 일의 자리는 B가, 십의자리는 A가
									charge_A = bat_p[temp_A/10];
									charge_B = bat_p[temp_B%10];
								}
								else {
									//B가 더 크면 일의자리는 A가, 십의 자리는 B가
									charge_A = bat_p[temp_A%10];
									charge_B = bat_p[temp_B/10];
								}
								
							}
							
						}
						else {
							//visited != mod1 즉, 서로 안겹친다.
							charge_B = bat_p[mod1];
						}
						
					}
				}
				//둘의 파워값 더해주고
				power_sum += charge_A + charge_B;
				
				//다 끝나고 A, B의 위치 갱신
				A_x = A_nx;
				A_y = A_ny;
				B_x = B_nx;
				B_y = B_ny;
			}
			
			
			
			System.out.printf("#%d %d",test_case, power_sum);
			System.out.println();
			
		}
	}
	
	//보드에 배터리 숫자 채우기.
	public static void fill_board(int x, int y, int value) {
		//아무것도 없으면 그냥 집어넣고
		if (board[x][y] == 0) {
			board[x][y] = value;
			return;
		}
		//1자리 수라는건 할당된 영역이 1개밖에 없으니까, 파워값이 큰 놈이 일의자리로 가게.
		else if(board[x][y] > 0 && board[x][y] <= 9) {
			int temp = board[x][y];
			if(bat_p[temp] >= bat_p[value]) {
				//즉, 원래 적혀있던게 더 파워가 쎄거나 같다면,
				int input = value*10 + temp;
				board[x][y] = input;
			}
			else {
				//원래 있던게 더 약하다면,
				int input = temp*10 + value;
				board[x][y] = input;
			}
			return;
		}
		
		//이미 2개 영역이 있기 때문에, 셋 중 누가 더 파워값이 큰지 비교해서 넣어야함.
		else if(board[x][y] >= 11) {
			//즉, 이미 2개 영역이 겹쳐있는 상황이라면, 셋 중 더 큰놈 2개를 넣어야함.
			int temp1 = (int)board[x][y] / (int)10;
			int temp2 = board[x][y] % 10;
			
			int result1;
			int result2;
			
			//a, b, c에서 a>b 일 경우
			if (bat_p[temp1] >= bat_p[temp2]) {
				result1 = temp1;
				if(bat_p[temp2] >= bat_p[value]) {
					result2 = temp2;
				}
				else {
					result2 = value;
				}
				//셋중 큰놈 둘 찾았고,
				
				//그 둘의 파워값을 비교해서 더 큰놈이 일의자리로 가게.
				if(bat_p[result1] >= bat_p[result2]) {
					board[x][y] = result2*10 + result1;
				}
				else {
					board[x][y] = result1*10 + result2;
				}
			}
			//a, b, c에서 a<b 일 경우.
			else {
				result1 = temp2;
				if(bat_p[temp1] >= bat_p[value]) {
					result2 = temp1;
				}
				else {
					result2 = value;
				}
				
				
				if(bat_p[result1] >= bat_p[result2]) {
					board[x][y] = result2*10 + result1;
				}
				else {
					board[x][y] = result1*10 + result2;
				}
			}
			return;
		}
	}

}
