package com.ssafy.hw.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_탁하윤 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 배열 크기
        int M = Integer.parseInt(st.nextToken());   // 찾아내야 하는 누적합 개수

        // 누적합에서 시작 인덱스가 0이라면 범위 넘어감
        int[][] arr = new int[N+1][N+1];
        int[][] s = new int[N+1][N+1];              // 누적합 배열
        int cnt = 0;                                // 찾아낸 누적합 개수 count

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                s[i][j] = s[i-1][j]+s[i][j-1]-s[i-1][j-1]+arr[i][j];
                // 한칸 전의 누적합과 한칸 위의 누적합을 더하고 중복 누적합 제거 후 현재 배열의 값 더하기
            }
        }

        while(cnt < M) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            cnt++;
            System.out.println(s[x2][y2]-s[x1-1][y2]-s[x2][y1-1]+s[x1-1][y1-1]);
            // 구하려는 누적합의 큰 좌표에서 작은 좌표 시작전까지의 누적합을 제거하고 중복으로 제거된 누적합은 다시 더해주기
        }

    }
}