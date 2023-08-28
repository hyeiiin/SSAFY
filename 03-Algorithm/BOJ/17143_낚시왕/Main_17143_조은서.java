import java.util.*;
import java.io.*;

public class Main_17143_조은서 {
		public static class Shark {
			int r, c, speed, direction, size;
			
			public Shark(int r, int c, int speed, int direction, int size) {
				super();
				this.r = r;
				this.c = c;
				this.speed = speed;
				this.direction = direction;
				this.size = size;
			}
		}
		
		static Shark[][] map;
		static int R,C,M;
		
		// 상 하 우 좌
		static int[] dr = {-1, 1, 0, 0};
		static int[] dc = {0, 0, 1, -1};
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = null;
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // 격자 크기 R
			C = Integer.parseInt(st.nextToken()); // 격자 크기 C
			M = Integer.parseInt(st.nextToken()); // 상어 수
			
			if(M==0) { // 상어 없으면 0 출력하고 그대로 끝
				System.out.println(0);
				return;
			}
			
			map = new Shark[R+1][C+1]; // 한 칸에 상어가 여러마리 들어 있을 수 있으므로 3차원 배열로 생성함
			// 상어 정보 -> 위치 (r,c), 속력(s), 이동 방향(d), 크기(z)
			// 이동방향(d) -> 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				map[r][c] = new Shark(r, c, s,d,z);
			}
			
			// 낚시왕은 1열씩 움직임
			// 	- 1. 해당 열에서 가장 땅과 가까운 상어를 찾아서 삭제
			// 낚시왕이 상어를 잡으면, 나머지 상어는 이동함
			// 	- 1. 방향, 속도에 맞춰 이동
			//	- 2. 벽 만나면 방향 수직으로 전환해서 이동
			//	- 3. 칸에 겹치면 크기가 큰 놈이 차지하고 나머지는 먹힘
			
			int initCol = 1; // 낚시왕 1열부터 낚시 시작
			int sum = 0;
			

			while(initCol != C+1) {
				
				// 낚시왕 낚시 시작
				for(int r=1; r<=R; r++) { // 1행부터 상어 있는지 보고
					if(map[r][initCol] != null) { // 있으면
//						System.out.println("사이즈" + " " +map[r][initCol].size); // 먹은 상어 확인
						sum += map[r][initCol].size; // 해당 위치의 상어 크기 더해준 뒤  
						map[r][initCol] = null; // 상어 삭제
						M--; // 상어 마리 수 감소
						break; // 해당 열의 상어를 잡았으니 끝
					}
				}
				// 낚시 끝
				
				// 상어 이동 시작
				moveShark();
				// 상어 이동 끝
				
				initCol++; // 낚시왕 다음 열로 이동
			}
			System.out.println(sum);
			
		}
		
		static boolean isIn(int nr, int nc) {
			if(nr > 0 && nr <= R && nc > 0 && nc <= C) return true;
			return false;
		}
		
		static void moveShark() {
			List<Shark> sharkList = new ArrayList<>();
			
			// map 탐색하면서 상어가 있으면 sharkList에 추가하고 map 비워둔다
			for(int i=0; i<=R; i++) {
				for(int j=0; j<=C; j++) {
					if(map[i][j] != null) {
						sharkList.add(map[i][j]);
						map[i][j] = null;
					}
				}
			}
			
			// list에 추가된 상어를 하나씩 꺼내서 이동시켜줌
			for(Shark shark: sharkList) {
				int d = shark.direction - 1; // 상어 direction이 1부터고, dx dy 의 index는 0부터니까, 1 빼줌
				int nr = shark.r; // 초기 위치
				int nc = shark.c;

				for(int t = 0; t<shark.speed; t++) { // 상어의 speed만큼 이동
					nr += dr[d];
					nc += dc[d];
					
					if(!isIn(nr,nc)) { // 범위 벗어나면 방향 전환
						nr -= dr[d]; // 이동 취소하고
						nc -= dc[d];
						
						// 상-하 / 좌-우 반전 해주면 되니까 0->1, 1->0 / 2->3, 3->2 로 바꿔줌 
						if(d == 0) {
							d = 1;
						} else if (d==1) {
							d = 0;
						}
						if (d==2) {
							d = 3;
						} else if(d==3) {
							d=2;
						}
						
						nr += dr[d]; // 바꿔준 방향으로 다시 이동
						nc += dc[d];
					}


				}
				// 이동을 마친 최종 위치와 방향을 저장해줌
				shark.r = nr;
				shark.c = nc;
				shark.direction = d+1;
				
				// 이동을 마친 위치에 아무것도 없으면 그 위치에 현재 상어 넣어주고
				if(map[shark.r][shark.c] == null) {
					map[shark.r][shark.c]= shark; 
				}
				// 이동을 마친 위치에 상어가 존재하면, 크기를 비교해서 더 큰 걸로 넣어줌
				else {
					if(map[shark.r][shark.c].size < shark.size ) {
						map[shark.r][shark.c] = shark;
					}
				}
			}
		}

	}