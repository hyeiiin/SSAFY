import java.io.*;
import java.util.*;

public class Main_9205_이도훈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {

            int N = Integer.parseInt(br.readLine());

            int[][] nodes = new int[N + 2][2];

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                nodes[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }

            // 다익스트라로 풀기

            // 간선 리스트 만들기
            ArrayList<Edge>[] adjList = new ArrayList[N + 2];
            for (int i = 0; i < N + 2; i++) {
                adjList[i] = new ArrayList<>();
            }


            for (int i = 0; i <= N; i++) {
                for (int j = i + 1; j <= N + 1; j++) {
                    int diff = Math.abs(nodes[i][0] - nodes[j][0]) + Math.abs(nodes[i][1] - nodes[j][1]);
                    adjList[i].add(new Edge(j, diff));
                    if (i != 0) {
                        adjList[j].add(new Edge(i, diff));
                    }
                }
            }

            // 거리 배열 초기화
            int[] distance = new int[N + 2];
            for (int i = 0; i < N + 2; i++) {
                distance[i] = Integer.MAX_VALUE;
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(value -> value.weight));

            pq.add(new Edge(0, 0));

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();

                if (distance[cur.to] != Integer.MAX_VALUE) continue;

                distance[cur.to] = cur.weight;

                for (Edge edge : adjList[cur.to]) {
                    if (edge.weight > 1000) continue;
                    if (distance[edge.to] != Integer.MAX_VALUE) continue;
                    pq.add(new Edge(edge.to, distance[cur.to] + edge.weight));
                }
            }

            if (distance[N + 1] == Integer.MAX_VALUE) {
                sb.append("sad");
            } else {
                sb.append("happy");
            }
            sb.append("\n");

        }
        System.out.println(sb);

    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

}
