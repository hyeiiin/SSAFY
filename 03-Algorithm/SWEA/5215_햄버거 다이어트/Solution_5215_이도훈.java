import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution_5215_이도훈 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[] flavor = new int[N + 1];
            int[] kal = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                flavor[i] = Integer.parseInt(st.nextToken());
                kal[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 1][L + 1];


            for (int i = 1; i <= N; i++) {
                for (int j = 0; j <= L; j++) {
                    if (i == 0 || j == 0) continue;

                    // 현재 재료의 칼로리가 제한 칼로리보다 작은 경우
                    if (kal[i] <= j) {
                        // (현재 칼로리 - 재료 칼로리) 의 맛  + 현재 재료 맛 vs 현재 맛
                        dp[i][j] = Math.max(dp[i-1][j - kal[i]] + flavor[i], dp[i-1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            sb.append("#").append(test_case).append(" ").append(dp[N][L]).append("\n");
        }
        System.out.println(sb);
    }
}
