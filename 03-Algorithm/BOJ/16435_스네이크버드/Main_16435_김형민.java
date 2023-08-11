package boj;

import java.io.*;
import java.util.*;

public class Main_16435_김형민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());//과일 수
        int L = Integer.parseInt(st.nextToken());// 스네이크 버드 길이

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            if (arr[i]<=L){
                L++;
            }
        }
        System.out.println(L);



    }
}
