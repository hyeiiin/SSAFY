
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution_6808_이도훈 {

    static final int TOTAL = 362880;
    static int cnt;
    static boolean[] visited;
    static int[] A;


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());


        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            A = new int[10];
            visited = new boolean[19];

            for (int i = 1; i <= 9; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                visited[A[i]] = true;
            }

            cnt = 0;

            perm(1,0);

            sb.append("#").append(test_case).append(" ").append(cnt).append(" ").append(TOTAL - cnt).append("\n");
        }
        System.out.println(sb);
    }

    public static void perm(int depth, int sum) {
        // 9장을 다 고른 경우
        if (depth == 10) {
            if (sum > 85) {
                cnt++;
            }
            return;
        }

        for (int i = 1; i <= 18; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            if (A[depth] > i) {
                perm(depth + 1, sum + A[depth] + i);
            } else {
                perm(depth + 1, sum);
            }

            visited[i] = false;
        }


    }

}
