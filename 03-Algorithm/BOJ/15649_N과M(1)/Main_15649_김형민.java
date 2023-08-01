package boj;

import java.io.*;
import java.util.*;

public class Main_15649_김형민 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] output = new int[M];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
        }
        perm(arr,output,visited,0,arr.length,M);
        System.out.println(sb.toString());
    }

    static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r){
        if (depth == r){
            for (int i : output) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth+1, n, r);
                visited[i] = false;
            }
        }

    }
}
