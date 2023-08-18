import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1260_이도훈 {

    static boolean[] visited;
    static int V;
    static int E;
    static ArrayList<Integer>[] adjList;


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }


        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        for (int i = 1; i <= V; i++) {
            Collections.sort(adjList[i]);
        }

        dfs(start);
        bfs(start);
    }

    public static void dfs(int start) {
        boolean[] visited = new boolean[V+1];

        Stack<Integer> stack = new Stack<>();

        stack.add(start);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();

            if(visited[cur]) continue;

            visited[cur] = true;
            sb.append(cur).append(" ");

            Collections.sort(adjList[cur],Collections.reverseOrder());
            for (Integer adj : adjList[cur]) {
                if(visited[adj]) continue;
                stack.add(adj);
            }
        }

        System.out.println(sb);
    }

    public static void bfs(int start) {
        boolean[] visited = new boolean[V+1];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();;

            if(visited[cur]) continue;

            visited[cur] = true;
            sb.append(cur).append(" ");

            Collections.sort(adjList[cur]);

            for (Integer adj : adjList[cur]) {
                if(visited[adj]) continue;
                queue.add(adj);
            }
        }

        System.out.println(sb);
    }


}

