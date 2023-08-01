package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh
 * Ladder1
 * @author SSAFY
 *
 */
public class Solution_1210_박정인 {
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			int[][] map = new int[100][100];
			int x = 0;
			int y = 0;
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
										
					if (map[i][j] == 2) {
						x = i;
						y = j;
					}
				}
			}
			
			boolean[][] visited = new boolean[100][100];
			while (x != 0) {
				visited[x][y] = true;
				if (y - 1 >= 0 && map[x][y - 1] == 1 && !visited[x][y - 1]) {
					y--;
				} else if (y + 1 < 100 && map[x][y + 1] == 1 && !visited[x][y + 1]) {
					y++;
				} else {
					x--;
				}

			}
			
			sb.append("#").append(T).append(" ").append(y).append("\n");
		}
		System.out.println(sb);
	}
	
	
}
