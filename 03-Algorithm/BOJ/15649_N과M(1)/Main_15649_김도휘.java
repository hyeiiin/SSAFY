import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] result;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        result = new int[M];

        permutation(0);
        System.out.println(sb.toString());

    }
    public static void permutation(int index) {
        if (index == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]+" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            result[index] = i;
            permutation(index + 1);
            visited[i] = false;
        }
    }
}