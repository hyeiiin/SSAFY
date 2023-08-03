package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_김형민 {
    static int minNum = Integer.MAX_VALUE;
    static int N;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            arr[n][0] = Integer.parseInt(st.nextToken());
            arr[n][1] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i < 1<<N; i++) {
            int sour = 1;
            int dixx = 0;
            for(int j=0; j<N; j++) {
                if((i & 1<<j) != 0) {
                    sour*=arr[j][0];
                    dixx+=arr[j][1];
                }
            }
            minNum = Math.min(minNum,Math.abs(sour-dixx));
        }
        System.out.println(minNum);
    }

}
