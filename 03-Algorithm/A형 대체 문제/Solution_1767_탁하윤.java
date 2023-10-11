package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_탁하윤 {
	static int N, maxCore, minLen, cell[][];
	static List<int[]> coreList;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// tc 개수
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	// cell 크기
			cell = new int[N][N];
			coreList = new ArrayList<>();	// 가장자리를 제외한 코어들의 좌표를 담을 리스트
			maxCore = Integer.MIN_VALUE;	// 연결된 코어 최대 개수
			minLen = Integer.MAX_VALUE;		// 연결 길이의 최소합
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cell[i][j] = Integer.parseInt(st.nextToken());
					
					if(i==0 || i==N-1 || j==0 || j==N-1) continue;	// 가장자리는 이미 연결되어 있으므로 리스트에 삽입 X
					else if(cell[i][j] == 1) {	// 코어들의 좌표 리스트에 담기
						coreList.add(new int[] {i, j});
					}
				}
			}
			dfs(0, 0, 0);	// 코어 연결하기
			sb.append("#").append(tc).append(" ").append(minLen).append("\n");
        }
        System.out.println(sb.toString());
	}
	private static void dfs(int idx, int cntCore, int cntLen) {
		if(idx == coreList.size()) {	// 기저 조건
			if(maxCore < cntCore) {	// 현재 연결된 코어수가 더 많다면 갱신
				maxCore = cntCore;
				minLen = cntLen;
			}else if (maxCore == cntCore) {	// 같다면 최소길이 값으로 갱신
				minLen = Math.min(minLen, cntLen);
			}
			return;
		}
		// 현재 코어 좌표
		int x = coreList.get(idx)[0];
		int y = coreList.get(idx)[1];
		
		for (int i = 0; i < 4; i++) {	// 현재 코어 중심으로 사방탐색
			int cnt = 0;
			int nx = x;
			int ny = y;
			while(true) {	
				nx += dx[i];
				ny += dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) break;	// 범위를 벗어나면 코어 연결완료
				if(cell[nx][ny] != 0) {	// 전선이나 코어를 만나면 연결길이 초기화, 다음 방향
					cnt = 0;
					break;
				}
				cnt++;
			}
			
			if(cnt==0) dfs(idx+1, cntCore, cntLen);	// 연결된 코어가 없다면 다음 코어
			else {
				// 코어의 전선 채우기
				int fx = x;
				int fy = y;
				
				for (int j = 0; j < cnt; j++) {
					fx += dx[i];
					fy += dy[i];
					cell[fx][fy] = 1;					
				}
				dfs(idx+1, cntCore+1, cntLen+cnt);	// 연결된 코어와 길이를 갱신하여 다음 코어 호출
				
				// 리턴되어 오면 다시 코어 전선 초기화
				fx = x;
				fy = y;
				for (int j = 0; j < cnt; j++) {
					fx += dx[i];
					fy += dy[i];
					cell[fx][fy] = 0;
				}
			}
		}
		
	}

}
