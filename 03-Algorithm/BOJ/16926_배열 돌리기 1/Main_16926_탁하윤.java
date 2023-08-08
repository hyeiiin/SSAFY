package com.ssafy.hw.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_탁하윤 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());    // N
        int M = Integer.parseInt(st.nextToken());    // M
        int R = Integer.parseInt(st.nextToken());    // 돌릴 횟수
        int num = Math.min(N, M)/2;        // 돌려야 할 박스  ex. (0,0) (1,1) ...
        int[][] arr = new int[N][M];    // 받은 정수

        // 입력 받기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<R; i++) { //회전 횟수만큼 반복

            for(int j=0; j<num; j++) { // 돌려야 할 박스 개수 만큼
                int temp = arr[j][j]; //맨 마지막에 넣기위해 temp에 담아두기

                // 상
                for(int k=j+1; k<M-j; k++)
                    arr[j][k-1] = arr[j][k];
                // 우
                for(int k=j+1; k<N-j; k++)
                    arr[k-1][M-1-j] = arr[k][M-1-j];
                // 하
                for(int k=M-2-j; k>=j; k--)
                    arr[N-1-j][k+1] = arr[N-1-j][k];
                // 좌
                for(int k=N-2-j; k>=j; k--)
                    arr[k+1][j] = arr[k][j];
                // (0,0 -> 1,0)
                arr[j+1][j] = temp;
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }


    }
}
