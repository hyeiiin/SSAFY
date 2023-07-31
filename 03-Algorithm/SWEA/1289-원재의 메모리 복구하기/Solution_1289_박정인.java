package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN
 * 원재의 메모리 복구하기
 */
public class Solution_1289_박정인 {
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int len = str.length();
            int cnt = 0;            
            char now = '0';
            
            for (int i = 0; i < len; i++) {
                if (str.charAt(i) != now) {
                    now =  str.charAt(i);
                    cnt++;
                }
            }
            
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
