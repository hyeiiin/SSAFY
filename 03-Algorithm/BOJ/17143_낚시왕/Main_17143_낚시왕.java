
import java.util.ArrayList;
import java.util.Scanner;

public class Main_17143_낚시왕 {
	static int R, C, M;
	static int sum = 0;
	static Shark[][] map;
	static ArrayList<Shark> list;
    public static int[] dr = {-1, 1, 0, 0};  // 상,하,우,좌
    public static int[] dc = {0, 0, 1, -1};
	
    /**
     * 상어의 정보를 관리하기 위한 클래스
     * r,c : 위치
     * s : 속도
     * d : 방향
     * z : 크기
     * 
     */
	static class Shark{
		int r,c,s,d,z;
		//		d 0:위, 1 : 아래, 2 : 오른쪽, 3 : 왼쪽 
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		int r,c,s,d,z;
		Shark shark;
		list = new ArrayList<Shark>();
		map = new Shark[R+1][C+1]; //상어 정보를 담을 수 있는 map 만들기
		for(int i = 0 ; i < M; i++) {
			r = sc.nextInt();
			c = sc.nextInt();
			s = sc.nextInt();
			d = sc.nextInt();
			z = sc.nextInt();
			shark = new Shark(r,c,s,d-1,z);		// 상,하,우,좌 배열 첨자와 d값 위치 맞추기
			list.add(shark); //상어 목록을 관리할 list에 담아주기
			map[r][c] = shark; //map에도 위치시키기
		}
		//낚시는 왼쪽 끝에서 오른쪽 끝까지 컬럼개수 만큼 진행하므로 C번 반복
		for(int i = 1; i <= C; i++) {
			//이동한 낚시꾼이 상어 낚기
			catchShark(i);
			//상어들 이동시키기
			moveShark();
			//상어 다시 위치 시키기 + 중첩되는 상어들 처리하기
			killShark();
		}
		System.out.println(sum);
	}
	
    /**
     * 상어 잡기
     * @param col 현재 낚시하는 사람의 위치
     */
	static void catchShark(int col) {
		for(int i = 1; i <= R; i++) { //모든 행 확인
			if(map[i][col] != null) {
				sum += map[i][col].z; //잡은 상어의 크기 더하기
				list.remove(map[i][col]);//잡았으므로 상어 목록에서 지워주기
				break;
			}
		}
	}
	
	/**
	 * 상어 이동시키기
	 */
	private static void moveShark() {
		for(Shark shark : list) { //상어들을 한마리씩 꺼내와서 이동
			int nr = shark.r;
			int nc = shark.c;
			int s = shark.s;
			int d = shark.d;
			//방향마다 이동시킬 것
			switch(d) {
			case 0:	//상,하
			case 1:
				s = s %(R*2-2);  //반복되는 횟수는 줄여주기
				for(int i =0; i < s; i++) {
					//양 끝에 도달하면 방향 반대로 바꿔주기
					if(nr==1) {
						d = 1;
					}else if(nr == R) {
						d = 0;
					}
					//한칸씩 이동하기
					nr += dr[d];
				}
				//상어의 위치 바꿔주기
				shark.r= nr;
				shark.d = d;
				break;
			case 2:	//좌,우
			case 3 :
				s = s %(C*2-2); //반복되는 횟수는 줄여주기
				for(int i =0; i < s; i++) {
					if(nc==1) {
						d = 2;
					}else if(nc == C) {
						d = 3;
					}
					nc += dc[d];
				}
				shark.c= nc;
				shark.d = d;
				break;				
			}
		}
	}
	
	/**
	 * 재배치 및 중첩된 샤크 제거하기
	 */
	private static void killShark() {
		//새로운 맵 할당. 상어의 위치 정보는 list에 저장되어 있으므로 이전 map 필요 없음
		map = new Shark[R+1][C+1]; 
		int size = list.size();
		for(int i = size-1; i >= 0; i--) {
			Shark s = list.get(i);
			//비어있는 공간이면 해당 공간에 상어 배치
			if(map[s.r][s.c] == null) {
				map[s.r][s.c] = s;
			}else {//이미 상어가 있으면 크기 비교해서 잡아먹기
				if(s.z > map[s.r][s.c].z) {
					list.remove(map[s.r][s.c]);
					map[s.r][s.c] = s;					
				}else {
					list.remove(s);
				}
			}
		}
	}

}
