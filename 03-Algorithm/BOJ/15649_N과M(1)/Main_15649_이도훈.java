import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15649_이도훈 {

    static boolean[] visited;
    static int[] answer;
    static StringBuilder sb;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        answer = new int[M];
        sb = new StringBuilder();

        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int depth) {
        // 탈출 조건
        if (depth == M) {
            for (int i = 0; i < answer.length; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            answer[depth] = i;
            visited[i] = true;
            dfs(depth+1);
            visited[i] = false;
        }

    }
}
