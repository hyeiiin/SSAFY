package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3109_전상혁 {
	static int R,C,res;
	static char map[][];
	static int delta[][] = {{-1,1},{0,1},{1,1}};
	static boolean isPossible;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//첫째 열= 근처 빵집의 가스 관, 마지막 열= 원웅이의 빵집
		//각 칸은 오른쪽(0,1), 오른쪽 위 대각(-1,1), 오른쪽 아래 대각(1,1)
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		
		
		for (int i = 0; i < R; i++) {
			//행마다 파이프 연결하러 출발
			map[i][0] = 'o';
			isPossible = false;
			connect(i,0);
			
		}
		
		//최종 파이프라인 개수 출력
		System.out.println(res);
	}
	private static void connect(int r, int c) {
		
		if (c==C-1) { //첫째 열- 마지막 열이 연결이 된 경우
			//파이프 라인 개수 카운트
			isPossible = true;
			res++;
			
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println("=========================");

			return;
		}
						
		int d = 0;
		
		while(d<3) {
			//파이프를 연결할 때 위쪽부터 연결해야 파이프라인을 최대로 연결할 수 있음.
			//따라서 우선으로 탐색해야 할 순서 -> 1.오른쪽 위 대각, 2.오른쪽, 3.오른쪽 아래 대각			
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];
			//범위를 벗어나거나 건물(x)로 인해 못 지나가거나 탐색하려는 곳에 이미 파이프(o)가 연결되어 있을 경우
			if (nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc]=='x' || map[nr][nc]=='o') {
				//다른 경로로 다시 탐색
				d++;
				continue;
			}else {
				//연결한 파이프 표시
				map[nr][nc] = 'o';
				connect(nr, nc);
				
				//첫 열과 마지막 열이 연결되었다면 반복 탈출
				if (isPossible) {
					
					return;
				}
				
			}
		}


	}
	

}
