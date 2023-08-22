import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV{
	int x;
	int y;
	int type;
	public CCTV(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
public class Main_15683_문예은 {
	static int N,M; // 사무실 세로 가로크기
	static int[] cctvList; // 순열리스트 저장
	static int[][] del = {{0,-1},{0,1},{-1,0},{1,0}}; // 좌우상하
	static int[][] screen, copyScreen;
	static ArrayList<CCTV> cctvs; // 설치된 cctv들의 좌표와 종류 저장할 리스트
	static int zeroCount = Integer.MAX_VALUE; // 최종 출력할 사각지대 영역 최소 개수
	
	private static boolean isIn(int dx, int dy) {
		if(dx < 0 || dx >= N || dy < 0 || dy >= M) return false;
		return true;
	}
	
	// 상하좌우 4가지 방향에서 cctv 개수만큼 순열로 뽑기
	private static void permutation(int depth, int size) { 
		if(depth == size) {
			copyScreen = new int[N][M]; // 순열 CCTV 방향대로 사각지대 계산할 임시 배열 생성
			for (int i = 0; i < screen.length; i++) {
				System.arraycopy(screen[i], 0, copyScreen[i], 0, screen[i].length);
			}
			
			for (int s = 0; s < cctvs.size(); s++) { // 각 cctv 방향 설정
				CCTV cctv = cctvs.get(s); // cctv 좌표와 종류 가져오기
				int direc = cctvList[s]; // 순열에서 방향 가져오기
				int cctvNum = cctv.type;

				if (cctvNum==5) { // 좌우상하
					for (int i = 0; i < 4; i++) {
						int dx = cctv.x + del[i][0];
						int dy = cctv.y + del[i][1];
						while (isIn(dx, dy) && copyScreen[dx][dy]!=6) { // 범위 밖으로 나가거나, 6을 만나면 종료 후 회전
							if(copyScreen[dx][dy]!=0) { // cctv 만나면
								dx += del[i][0]; // 한칸 건너뛰기
								dy += del[i][1];
							} else { // 빈 공간이면
								copyScreen[dx][dy] = -1; // # 기호 대신 -1사용
								dx += del[i][0];
								dy += del[i][1];
							}
						}
					}
				} // 여기까지는 고정 영역(5번 cctv는 회전해도 모두 같기 때문)
				
				else if (cctvNum==4) { // del 4방향 에서 하나씩 빼며 체크 진행
					for (int i = 0; i < 4; i++) {
						if(i == direc) continue; // 순열로 주어진 숫자와 같을때만 제외하고, 나머지 3방향 탐색
						int dx = cctv.x + del[i][0];
						int dy = cctv.y + del[i][1];
						while (isIn(dx, dy) && copyScreen[dx][dy]!=6) { // 범위 밖으로 나가거나, 6을 만나면 종료 후 회전
							if(copyScreen[dx][dy]!=0) { // cctv 만나면
								dx += del[i][0]; // 한칸 건너뛰기
								dy += del[i][1];
							} else { // 빈 공간이면
								copyScreen[dx][dy] = -1; // # 기호 대신 -1사용
								dx += del[i][0];
								dy += del[i][1];
							}
						}
					}
				}
				else if (cctvNum==3) {
					if(direc==0) {  // 좌상
						turnCCTV(cctv, 0);
						turnCCTV(cctv, 2);
					} else if (direc==1) { // 우하 
						turnCCTV(cctv, 1); 
						turnCCTV(cctv, 3);
					} else if (direc==2) { // 상우
						turnCCTV(cctv, 2);
						turnCCTV(cctv, 1);
					} else if (direc==3) { // 하좌
						turnCCTV(cctv, 0);
						turnCCTV(cctv, 3);
					}
				}
				else if (cctvNum==2) {
					if(direc==0 || direc==1) { // 좌우
						turnCCTV(cctv, 0);
						turnCCTV(cctv, 1);
					} else if (direc==2 || direc==3) { // 상하
						turnCCTV(cctv, 2);
						turnCCTV(cctv, 3);
					}
				}
				else if (cctvNum==1) {
					if(direc==0) turnCCTV(cctv, 0); // 좌
					else if (direc==1) turnCCTV(cctv, 1); // 우
					else if (direc==2) turnCCTV(cctv, 2); // 상
					else if (direc==3) turnCCTV(cctv, 3); // 하
				}
			}
			// 사각지대 빈칸 세기
			int tempCount = 0;
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++)
					if(copyScreen[n][m]==0) tempCount++;
			}
			zeroCount = Math.min(zeroCount, tempCount);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) { // 4방향으로 cctv 각각 돌려보기 - 순열리스트
			cctvList[depth] = i;
			permutation(depth+1, size);
		}
	}
		
	private static void turnCCTV(CCTV cctv, int d) { // 주어진 d방향에 대해서만 탐색진행
		int dx = cctv.x + del[d][0];
		int dy = cctv.y + del[d][1];
		while (isIn(dx, dy) && copyScreen[dx][dy]!=6) { // 범위 밖으로 나가거나, 6을 만나면 종료 후 회전
			if(copyScreen[dx][dy]!=0) { // cctv 만나면
				dx += del[d][0]; // 한칸 건너뛰기
				dy += del[d][1];
			} else { // 빈 공간이면
				copyScreen[dx][dy] = -1; // # 기호 대신 -1사용
				dx += del[d][0];
				dy += del[d][1];
			}
		}
	}
	public static void main(String[] args) throws IOException {
		/*
		 * 순열 - 각 카메라가 보는 방향의 순열리스트, 차례대로 적용하여 사각지대 최소갱신
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행 길이
		M = Integer.parseInt(st.nextToken()); // 열 길이
		screen = new int[N][M]; // CCTV 화면 배열
		// cctv 좌표와 종류 저장할 큐
		cctvs = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				screen[n][m] = Integer.parseInt(st.nextToken()); 
				if(screen[n][m]>=1 && screen[n][m]<=5) { // cctv만 큐에 저장
					cctvs.add(new CCTV(n, m, screen[n][m])); // 좌표, 종류
				}
			}
		}
		
		cctvList = new int[cctvs.size()]; // 순열로 cctv 리스트 저장할 배열
		permutation(0, cctvs.size()); // cctv 개수만큼 순열뽑기
		
		System.out.println(zeroCount);
	}
}
