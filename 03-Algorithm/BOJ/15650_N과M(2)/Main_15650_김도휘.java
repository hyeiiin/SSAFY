import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        permutation(0, 1);

    }

    public static void permutation(int index, int start) {
        if (index == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = start; i <= N; i++) {
            arr[index] = i;
            permutation(index + 1, i + 1);
        }

    }
}