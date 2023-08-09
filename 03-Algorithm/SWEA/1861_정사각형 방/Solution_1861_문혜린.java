package algorithm.swea;

import java.util.*;
import java.io.*;

//정사각형 방
public class Solution_1861_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine()); //배열 크기
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxMove = 0; //최대 이동 수
			int maxIndex = 0; //처음 출발하는 방
			int cnt = 0; //이동한 방 개수 카운트
			
			Queue<int[]> q; //방 좌표 값 저장
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//초기화
					q = new LinkedList<>();
					cnt = 0;
					
					//상하좌우
					int[] dx = {-1, 1, 0, 0};
					int[] dy = {0, 0, -1, 1};
					
					q.add(new int[] {i, j}); //처음 출발하는 방
					cnt++; //처음 방 이동 카운트
					while(!q.isEmpty()) {
						int[] tmp = q.remove();
						
						for(int k=0; k<4; k++) {
							//상하좌우 이동
							int nx = tmp[0] + dx[k];
							int ny = tmp[1] + dy[k];
							//이동하려는 곳이 범위 안에 있고
							if(nx>=0 && nx<N && ny>=0 && ny<N) {
								//이동하려는 방이 현재 방에 적힌 숫자보다 정확히 1이 더 크다면 큐 삽입
								if(arr[tmp[0]][tmp[1]]+1 == arr[nx][ny]) {
									q.add(new int[] {nx, ny});
									cnt++; //이동 증가
								}
							}
						}
					}
					if(maxMove < cnt) { //현재 최대 이동 개수보다 cnt가 크면 갱신
						maxMove = cnt; //이동 개수
						maxIndex = arr[i][j]; //출발해야 하는 방
					}
					else if(maxMove == cnt) { //현재 최대 이동 개수와 cnt가 같으면
						if(maxIndex > arr[i][j]) { // 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력
							maxIndex = arr[i][j]; //출발해야 하는 방
						}
					}
				}
			}
			sb.append("#"+t+" "+maxIndex+" "+maxMove+"\n");
		}
		System.out.println(sb);

	}

}
