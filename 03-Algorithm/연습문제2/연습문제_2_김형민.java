import java.util.*;
import java.io.*;
public class 연습문제_2_김형민 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[1] = 2;
        dp[2] = 5;
        for (int i = 3; i < n+1; i++) {
            dp[i] = dp[i-1]*2 + dp[i-2];
        }
        System.out.println(dp[n]);
    }
}
