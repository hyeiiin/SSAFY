import java.io.*;
import java.util.*;

class Node {
	public int r;
	public int c;

	public Node(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

// 1767. 프로세서 연결하기
public class Solution_1767_김아현 {
	
	static int n, maxCore, minWire;
	static int[][] map;
	static List<Node> cores;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int test = 1; test <= t; test++) {
			sb.append("#").append(test+" ");
			
			n = Integer.parseInt(br.readLine()); // map의 가로, 세로 길이
			map = new int[n][n];
			cores = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// core의 위치 정보 저장 (가장자리에 있는 core 제외)
					if(map[i][j] == 1) {
						if(i > 0 && i < n-1 && j > 0 && j < n-1) {
							cores.add(new Node(i, j));
						}
					}
				}
			}
			
			maxCore = Integer.MIN_VALUE;
			minWire = Integer.MAX_VALUE;
			dfs(0,0,0);
			sb.append(minWire).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// 상우하좌
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static void dfs(int cnt,int coreCnt, int wireLen) {
		// [가지치기] 현재까지 연결된 코어 수 + 남은 코어 수 < 현재까지의 최대 코어 수
		if(coreCnt + (cores.size() - cnt) < maxCore) {
			return;
		}
		
		// [기저조건] 모든 코어를 돌았다면
		if(cnt == cores.size()) {
			// core 정보 update
			if(maxCore < coreCnt) {
				maxCore = Math.max(maxCore, coreCnt);
				minWire = wireLen;
			}else if(maxCore == coreCnt) {
				minWire = Math.min(minWire, wireLen);
			}
			return;
		}
		
		int nowR = cores.get(cnt).r;
		int nowC = cores.get(cnt).c;
		
		// 상하좌우 방향으로 연결 시작 & 확인
		for (int d = 0; d < 4; d++) {
			int wire = 0;
			for (int i = 1; i < n-1; i++) {
				int nextR = nowR + dr[d]*i;
				int nextC = nowC + dc[d]*i;
				
				// 경계를 벗어나면 연결
				if(!inArea(nextR, nextC)) {
					break;
				}
				
				// 연결하는 길에 core나 전선을 만나면 그 방법은 연결 불가
				if(map[nextR][nextC] > 0) {
					wire = 0;
					break;
				}
				wire++;
			}
			
			// 연결한 전선의 길이가 있다면 -> 전선의 길이만큼 연결
			if(wire > 0) {
				for (int i = 1; i <= wire; i++) {
					map[nowR + dr[d]*i][nowC + dc[d]*i] = 2;
				}
				
				// 해당 코어 연결
				dfs(cnt+1, coreCnt+1, wireLen+wire);
				
				for (int i = 1; i <= wire; i++) {
					map[nowR + dr[d]*i][nowC + dc[d]*i] = 0;
				}
			}else {
				// 해당 코어 연결 X
				dfs(cnt+1, coreCnt, wireLen);
			}
		}
	}
	
	static boolean inArea(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
}