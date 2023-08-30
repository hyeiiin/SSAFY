import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken()); //M개 중에서 N개를 뽑아야 함
            dp = new int[M + 1][M + 1];

            for (int i = 1; i <= M; i++) {
                dp[i][0] = 1; //nC0 = 1
                dp[i][i] = 1; //nCn = 1
                dp[i][1] = i; //nC1 = n
            }
            for (int i = 2; i <= M; i++) {
                for (int j = 1; j < i; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
            System.out.println(dp[M][N]);
        }

    }
}
