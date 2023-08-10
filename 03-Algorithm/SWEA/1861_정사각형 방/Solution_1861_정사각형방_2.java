
import java.util.Scanner;

public class Solution_1861_정사각형방_2 {
	static int[][] map;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	
	static class Ans implements Comparable<Ans>{
		int start; //시작 점
		int cnt; //내가 방문한 방의 개수
		Ans(int start, int cnt){
			this.start = start;
			this.cnt = cnt;
		}
		//result와 다른 객체가 비교를 함.
		@Override
		public int compareTo(Ans o) {
			// TODO Auto-generated method stub
			//result와 다른 객체의 count가 동일하면 시작점의 값이 작은 순으로 정렬 - 오름차순
			if( this.cnt == o.cnt )
				return this.start - o.start;
			//result 객체와 다른 객체의 count 비교 - 내림차순
			return o.cnt - this.cnt;
		}
		public String toString() {
			return this.start + " " + this.cnt;
		}
	}
	static Ans result;//결과를 저장할 객체
	
	public static void main(String[] args) {
		//--------------input
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N+2][N+2];
			for(int i = 1; i < N+1; i++) {
				for(int j = 1; j < N+1; j++)
					map[i][j] = sc.nextInt();
			}
		//-------------end
			//최초 정답객체에는 쓰레기값 아무거나 넣어두기
			result = new Ans(987654321,0);
			//dfs 출발. dfs 모든 방으로 부터 출발
			for(int i = 1; i < N+1; i++) {
				for(int j = 1; j < N+1; j++)
					dfs(i, j, map[i][j], 1);
			}
			System.out.println("#" + tc + " " + result.toString());
		}
	}
	

	/**
	 * 
	 * @param r : 현재 좌표
	 * @param c
	 * @param init : 처음 칸의 정보
	 * @param cnt : 몇칸 이동했는지
	 */
	static void dfs(int r, int c, int init, int cnt) {
		//기저파트는 생각하지 않아도 됨. 언젠간 갈길이 사라짐
		//그때그때 현재의 상태가 최적의 답인지 갱신해나감
		Ans ans = new Ans(init, cnt);
		if( result.compareTo(ans) > 0 )
			result = ans; //더 많은 칸을 이동, 출발지가 더 작은 값이면
		//사방에 대해서 탐색
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(map[nr][nc] == map[r][c]+1) { //이동하려는 곳이 현재 위치보다 1큰곳
				dfs(nr, nc, init, cnt+1);
			}
		}
	}

}








