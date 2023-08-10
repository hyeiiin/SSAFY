package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_6808_박정인 {
	static List<Integer> listK;	// 규영의 패
	static List<Integer> listI;	// 인영의 패에 가능한 번호
	static int win, output[];	// 승리, 인영의 패 
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int total = 1;	// 전체 경우의 수 9!
		for (int i = 1; i <= 9; i++) {
			total *= i;
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			listK = new ArrayList<>();
			listI = new ArrayList<>();
			win = 0;
			
			for (int i = 0; i < 9; i++) {
				listK.add(Integer.parseInt(st.nextToken()));
			}
						
			for (int i = 1; i <= 18; i++) {
				if (!listK.contains(i)) {
					listI.add(i);
				}
			}
			
			output = new int[9];
			visited= new boolean[9];
			dfs(0);
			sb.append(win).append(" ").append(total - win).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 인영 순열
	private static void dfs(int cnt) {
		if (cnt == 9) {
			if (isWin(output)) {
				win++;
			}
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[cnt] = listI.get(i);
				dfs(cnt + 1);
				visited[i] = false;
			}
		}
	}	
	
	// 누가 이겼는지
	private static boolean isWin(int[] output) {
		int scoreK = 0;
		int scoreI = 0;
		
		// 총 9라운드 반복
		for (int i = 0; i < 9; i++) {
			int K = listK.get(i);
			int I = output[i];
			
			if (K > I) {	// 규영이 이긴경우
				scoreK += (K + I);
			} else if (K < I) {	// 인영이 이긴경우
				scoreI += (K + I);
			}
		}
		
		return scoreK > scoreI;	// 전체 승패 
	}
	
	/*
	// 더 빠른 풀이
	static List<Integer> gList;
    static List<Integer> aList;
    static int win;
    static int lose;
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            gList = new ArrayList<>();
            aList = new ArrayList<>();
            boolean[] visited = new boolean[9];
            int total = 1;
            win = 0;
            lose = 0;
            st = new StringTokenizer(br.readLine());
 
            for (int i = 0; i < 9; i++) {
                gList.add(Integer.parseInt(st.nextToken()));
            }
 
            for (int i = 1; i <= 18; i++) {
                if (gList.contains(i)) continue;
                aList.add(i);
            }
 
            dfs(0, 0, visited);
 
            for (int i = 1; i <= 9; i++) {
                total *= i;
            }
 
            sb.append("#" + tc + " " + (total - lose) + " " + lose + "\n");
        }
 
        System.out.println(sb);
        br.close();
    }
 
    public static void dfs(int result, int depth, boolean[] visited) {
        // 1 ~ 18 까지 합은 171로 만약 규영이가 모든 라운드를 이겼다면 171점을 획득한다. >> 85, 86 이 절반인데 86점만 규영이가 먼저 획득하면 승리는 확정
        if (result >= 86) {
            win++;
            return;
        }
        if (depth == 9) {
            lose++;
            return;
        }
 
        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (gList.get(depth) > aList.get(i)) {
                    dfs(result + gList.get(depth) + aList.get(i), depth + 1, visited);
                } else if (gList.get(depth) < aList.get(i)) {
                    dfs(result, depth + 1, visited);
                }
                visited[i] = false;
            }
        }
    }
	 */
}
