package boj;
import java.util.*;
import java.io.*;
public class Main_1260_김형민 {
    static int N,M;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int nextNode = Integer.parseInt(st.nextToken());
            //양뱡향 이기 때문에 둘다 연결
            graph[node].add(nextNode);
            graph[nextNode].add(node);
        }
        
        //작은것부터 탐색해야하기 때문에 다음노드 리스트를 정렬
        for (int i = 1; i < N+1; i++) {
            Collections.sort(graph[i]);
        }

        dfs(startNode, new boolean[N+1]);
        sb.append("\n");
        bfs(startNode);
        System.out.println(sb);
    }
    static void dfs(int node, boolean[] visited){
        if (visited[node]) return;
        visited[node] = true;
        sb.append(node).append(" ");
        for (int nextNode : graph[node]) {
            dfs(nextNode, visited);
        }
    }
    static void bfs(int startNode){
        boolean[] visited = new boolean[N+1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(startNode);
        while (!deque.isEmpty()){

            Integer node = deque.pollFirst();

            if (visited[node]) continue;
            visited[node] = true;
            sb.append(node).append(" ");
            for (int nextNode : graph[node]) {
                if (visited[nextNode]) continue;
                deque.addLast(nextNode);
            }
        }
        sb.append("\n");
    }
}
