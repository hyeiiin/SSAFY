import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_이도훈 {

    static int N;
    static boolean[] visited;
    static int[][] ingredients;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ingredients = new int[N][2];
        visited = new boolean[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 2; j++) {
                ingredients[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // i : 요리에 들어갈 재료 개수
        for (int i = 1; i <= N; i++) {
            recur(0, i, 1, 0);
        }
        System.out.println(min);

    }

    public static void recur(int depth, int target, int sour, int bitter) {
        // 재료를 목표치까지 넣었다면
        if (depth == target) {
            min = Math.min(min, Math.abs(sour - bitter));
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            recur(depth + 1, target, sour * ingredients[i][0], bitter + ingredients[i][1]);
            visited[i] = false;
        }
    }

}
