package boj;
import java.util.*;
import java.io.*;
public class Main_1010_김형민 {
    static int[][] dp = new int[30][30];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            sb.append(comb(M,N)).append("\n"); //조합으로 답을 찾자
        }
        System.out.println(sb);
    }

    /**
     * nCr = n-1Cr-1 + n-1Cr
     */
    static int comb(int n, int r){
        if (dp[n][r] > 0){//이미 저장된 값이 있다면 리턴한다.
            return dp[n][r];
        }
        if (n==r||r==0){//n개중 n개를 뽑는 경우와 n개중 0개를 뽑는 경우는 1이다.
            return dp[n][r] = 1;
        }
        //저장된 값이 없다면 -> 0이라면 -> 값을 찾아준다.
        return dp[n][r] = comb(n-1,r-1) + comb(n-1,r);
    }
}
