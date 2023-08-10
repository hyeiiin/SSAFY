
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution_6808_이도훈 {

    static final int TOTAL = 362880;
    static int cnt;
    static int except;
    static boolean[] visited;
    static int[] A;
    static int[] B;


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());


        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            A = new int[10];
            B = new int[10];
            visited = new boolean[19];

            for (int i = 1; i <= 9; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                visited[A[i]] = true;
            }

            cnt = 0;
            except = 0;

            perm(1);

            sb.append("#").append(test_case).append(" ").append(cnt).append(" ").append(TOTAL - cnt - except).append("\n");
        }
        System.out.println(sb);
    }

    public static void perm(int depth) {
        // 9장을 다 고른 경우
        if (depth == 10) {
            checkAWin();
            return;
        }

        for (int i = 1; i <= 18; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            B[depth] = i;
            perm(depth + 1);
            visited[i] = false;
        }


    }

    public static void checkAWin() {
        int a = 0;
        int b = 0;

        for (int i = 1; i <= 9; i++) {
            if (A[i] > B[i]) {
                a += A[i] + B[i];
            } else if (A[i] < B[i]) {
                b += A[i] + B[i];
            }
        }

        if (a > b) {
            cnt++;
        } else if (a == b) {
            except++;
        }
    }
}
