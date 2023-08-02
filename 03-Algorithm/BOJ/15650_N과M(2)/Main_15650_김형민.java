package boj;
import java.io.*;
import java.util.*;

public class Main_15650_김형민 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
        }
        ArrayList<int[]> result = new ArrayList<>();
        perm(arr, M, 0, 0, new int[M],result);
        for (int[] ints : result) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void perm(int[] arr, int n, int cnt, int start, int[] temp, ArrayList<int[]> result){
        if (cnt==n){
            result.add(Arrays.copyOf(temp,temp.length));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            temp[cnt] = arr[i];
            perm(arr, n, cnt+1, i+1, temp, result);
        }





    }


}
