package boj;
import java.util.*;
import java.io.*;
public class Main_2839_김형민 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans=0;
        while (N>=0){
            if (N%5==0){
                ans+=N/5;
                sb.append(ans);
                break;
            }
            N -= 3;
            ans ++;
        }
        if (N<0){
            sb.append(-1);
        }
        System.out.println(sb);




    }
}
