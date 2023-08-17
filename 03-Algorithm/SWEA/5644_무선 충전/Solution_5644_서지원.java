package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_서지원 {
	
	static int M, A, result;
	static int[] aMove, bMove;
	static List<List<List<BC>>> map;
	static int[] dx = {0, -1, 0, 1, 0}, dy = {0, 0, 1, 0, -1};
	static List<BC> BCList;
	
	private static class BC implements Comparable<BC> {
		private int x, y, c, p;
		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		@Override
		public int compareTo(BC o) {
			return o.p - this.p;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			// 초기 위치 포함
			aMove = new int[M + 1];
			bMove = new int[M + 1];
			aMove[0] = 0; bMove[0] = 0;
			
			// A 이동 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			// B 이동 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				bMove[i] = Integer.parseInt(st.nextToken());
			}
			
			// BC의 정보 
			BCList = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				BCList.add(new BC(y - 1, x - 1, c, p));
			}
			
			// BC 설치
			installBC();
			
			// A, B 이동
			result = 0;
			move();
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	// BC 설치
	private static void installBC() {
		// map 초기화
		map = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			map.add(new ArrayList<>());
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map.get(i).add(new ArrayList<>());
			}
		}
		
		// BC 설치
		for (int i = 0; i < A; i++) {
			BC bc = BCList.get(i);
			int sx = bc.x - bc.c;
			int ex = bc.x + bc.c + 1;
			int sy = bc.y, ey = bc.y + 1;
			for (int a = sx; a < ex; a++) {
				for (int b = sy; b < ey; b++) {
					if (a >= 0 && a < 10 && b >= 0 && b < 10) {
						map.get(a).get(b).add(bc);						
					}
				}
				if (a < bc.x) {
					sy--; ey++;
				} else {
					sy++; ey--;
				}
			}
		}
		
		// map 내림차순 정렬(BC 성능 p 기준)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				List<BC> list = map.get(i).get(j);
				if (list.size() != 0) {
					Collections.sort(list);
				}
			}
		}
	}
	
	//  A, B 이동 
	private static void move() {
		int ax = 0, ay = 0;
		int bx = 9, by = 9;
		boolean aCheck, bCheck;
		int anx, any, bnx, bny;
		for (int i = 0; i < M + 1; i++ ) {
			// A, B 좌표
			anx = ax + dx[aMove[i]]; any = ay + dy[aMove[i]];
			bnx = bx + dx[bMove[i]]; bny = by + dy[bMove[i]];
			ax = anx; ay = any; bx = bnx; by = bny;
			
			List<BC> aBC = map.get(anx).get(any);
			List<BC> bBC = map.get(bnx).get(bny);
			
			aCheck = false; bCheck = false;
			// 이동한 위치에 BC가 있으면 aCheck = true
			if (aBC.size() != 0) aCheck = true;
			// 이동한 위치에 BC가 있으면 bCheck = true
			if (bBC.size() != 0) bCheck = true;

			// A만 BC에 접속한 경우
			if (aCheck && !bCheck) {
				result += aBC.get(0).p;
			}
			// B만 BC에 접속한 경우
			if (!aCheck && bCheck) {
				result += bBC.get(0).p;
			}
			// A, B 둘 다 BC에 접속한 경우
			if (aCheck && bCheck) {
				int sum = 0;
				for (int a = 0; a < aBC.size(); a++) {
					for (int b = 0; b < bBC.size(); b++) {
						BC bc1 = aBC.get(a);
						BC bc2 = bBC.get(b);
						if (bc1.equals(bc2)) {
							sum = Math.max(sum, bc1.p);
						} else {
							sum = Math.max(sum, bc1.p + bc2.p);
						}
					}
				}
				result += sum;
			}
		}
	}
	
}
