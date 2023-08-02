import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_15650_이도훈 {

    static boolean[] visited;
    static int M;
    static int N;

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        sb = new StringBuilder();

        permutation(0, "",1);
        System.out.println(sb);

    }

    public static void permutation(int depth, String prevStr,int n) {
        if (depth == M) {
            sb.append(prevStr.trim()).append("\n");
            return;
        }

        for (int i = n; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            permutation(depth + 1, prevStr + " " + i, i);
            visited[i] = false;
        }

    }

}
