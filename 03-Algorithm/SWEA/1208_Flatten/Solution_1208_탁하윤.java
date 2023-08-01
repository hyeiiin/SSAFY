package com.ssafy.hw.algo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution_1208_탁하윤 {

    static int[] box = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int tc=1; tc<=10; tc++) {

            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            // box 초기화
            for(int i=0; i<100; i++) {
                box[i] = Integer.parseInt(st.nextToken());
            }
            // 정렬한 후 0번 인덱스는 최저점, 99번 인덱스는 최고점
            Arrays.sort(box);

            System.out.print("#"+tc+" ");
            dump(N);    // 재귀 호출 시작
        }


    }

    public static void dump(int n) {
        StringBuilder sb = new StringBuilder();
        if(n!=0 && (box[99]-box[0]==1 || box[99]-box[0]==0)) {
            // 만약 N이 끝나기 전에 최고점과 최저점의 차이가 1 또는 0이라면 크기차이를 출력하고 종료
            sb.append(box[99]-box[0]);
            System.out.println(sb);
            return;
        } else if(n==0){
            // N번 모두 호출되었다면 최고점과 최저점의 차이 출력
            sb.append(box[99]-box[0]);
            System.out.println(sb);
        } else {
            // N번 호출되면서 최고점의 상자 1개를 최저점에 줌
            // 즉, 최고점은 크기 -1, 최저점은 크기 +1
            box[0]++;
            box[99]--;
            // 최고점, 최저점 다시 정렬
            Arrays.sort(box);

            dump(n-1);
        }
    }

}
