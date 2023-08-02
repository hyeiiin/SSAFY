package boj;
import java.io.*;
import java.util.*;

public class Main_11659_김형민 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        arr[0] = 0;
        for (int idx = 1; idx < N+1; idx++) {
            arr[idx] = arr[idx-1]+ Integer.parseInt(st.nextToken());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken())-1;
            int right = Integer.parseInt(st.nextToken());
            sb.append(arr[right]-arr[left]).append("\n");
        }
        System.out.println(sb);
    }



}


