import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //정점 개수
        int M = Integer.parseInt(st.nextToken()); //간선 개수
        int V = Integer.parseInt(st.nextToken()); //시작 정점 번호
        visited = new boolean[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            list[v1].add(v2);
            list[v2].add(v1);
        }
        for (int i = 0; i <= N; i++) {
            Collections.sort(list[i]);
        }

        dfs(V);
        System.out.println();
        visited = new boolean[N + 1];
        bfs(V);
    }

    public static void dfs(int v) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        System.out.print(v + " ");
        for (Integer node : list[v]) {
            if (!visited[node]) {
                dfs(node);
            }
        }
    }

    public static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");

            for (Integer node : list[now]) {
                if (!visited[node]) {
                    queue.offer(node);
                    visited[node] = true;
                }
            }
        }

    }

}
