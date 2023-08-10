
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Solution_4012_이도훈 {


    static boolean[] visited;
    static int N;
    static int size;
    static int[][] map;
    static int answer;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());


            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[N];
            size = N / 2;
            answer = Integer.MAX_VALUE;

            dfs(0, 0);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");

        }
        System.out.println(sb);
    }

    // 조합 문제 , 한쪽만 구하면 나머지 반대 쪽은 구할 수 있음
    public static void dfs(int prev, int depth) {
        if (depth == size) {
            answer = Math.min(calc(), answer);
            return;
        }

        for (int i = prev + 1; i < N; i++) {
            visited[i] = true;
            dfs(i, depth + 1);
            visited[i] = false;
        }

    }

    // 점수 계산
    public static int calc() {
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) {
                    sumA += map[i][j];
                } else if(!visited[i] && !visited[j]){
                    sumB += map[i][j];
                }
            }
        }

        return Math.abs(sumA - sumB);
    }
}
