package boj;
import java.io.*;
import java.util.*;

public class Main_11660_김형민 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());//표의 크기
        int M = Integer.parseInt(st.nextToken());//합을 구해야하는 횟수 M
        int[][] dp = new int[N + 1][N + 1];
        int[][] arr = new int[N + 2][N + 2];
        for (int y = 1; y < N+1; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x < N+1; x++) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }



        for (int y = 1; y < N+1; y++) {
            for (int x = 1; x < N+1; x++) {
                dp[y][x] = arr[y][x]
                        + dp[y-1][x]
                        + dp[y][x-1]
                        - dp[y-1][x-1];
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            sb.append(dp[y2][x2]
                    -dp[y2][x1-1]
                    -dp[y1-1][x2]
                    +dp[y1-1][x1-1]
            ).append("\n");
        }
        System.out.println(sb);


    }
}
