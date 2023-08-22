import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static boolean isPossible;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        isPossible = false;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            if (!visited[i]) {
                dfs(i, 1);
            }

            if (isPossible) {
                System.out.println(1);
                break;
            }

        }
        if (!isPossible) {
            System.out.println(0);
        }

    }

    public static void dfs(int index, int depth) {
        if (visited[index]) {
            return;
        }
        if (depth == 5) { //친구 관계 도달
            isPossible = true;
            return;
        }
        visited[index] = true;
        for (int p : list[index]) {
            if (!visited[p]) {
                dfs(p, depth + 1);
            }
        }
        //가다가 조건 미충족되서 다시 돌아오는 경우
        visited[index] = false;

    }
}
