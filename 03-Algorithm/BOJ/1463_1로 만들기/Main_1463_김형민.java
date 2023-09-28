
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        for (int x = 2; x < N+1; x++) {
            arr[x] = arr[x-1] + 1;
            if (x%3==0){
                arr[x] = Math.min(arr[x], arr[x/3]+1);
            }
            if (x%2==0){
                arr[x] = Math.min(arr[x], arr[x/2]+1);
            }

        }
        System.out.println(arr[N]);
    }

}
