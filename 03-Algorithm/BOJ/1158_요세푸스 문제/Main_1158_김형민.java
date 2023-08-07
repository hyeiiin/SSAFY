package boj;
import java.io.*;
import java.util.*;
public class Main_1158_김형민 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int j = 0;
        int num = k;
        int idx = k-2;
        boolean[] chk = new boolean[n];


        while(j<n){
            idx++;
            if (idx>=n){
                idx-=n;
            }
            if (!chk[idx]&&idx!=k-1){
                num++;
            }
            if (num==k){
                num=0;
                j++;
                chk[idx]=true;
                sb.append(idx+1).append(", ");
            }

        }
        sb.delete(sb.length()-2,sb.length());
        System.out.println("<"+sb+">");


    }
}
