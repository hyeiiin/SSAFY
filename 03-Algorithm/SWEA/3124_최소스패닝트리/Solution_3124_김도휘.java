import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
 
    static Edge[] edgeArr;
    static int[] parents;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수
        StringBuilder sb = new StringBuilder();
 
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            edgeArr = new Edge[E];
            parents = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                parents[i] = i;
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken()); //가중치
 
                edgeArr[i] = new Edge(A, B, C);
            }
            Arrays.sort(edgeArr);
            int useEdge = 0;
            long sum = 0;
            while (useEdge < V - 1) {
                for (Edge now : edgeArr) {
                    if (find(now.from) != find(now.to)) {
                        union(now.from, now.to);
                        useEdge++;
                        sum += now.weight;
                    }
                }
            }
            sb.append("#").append(t + 1).append(" ");
            sb.append(sum);
            sb.append("\n");
        }
        System.out.println(sb);
 
    }
 
    public static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);
        if (pA != pB) {
            parents[pB] = pA;
        }
    }
 
    public static int find(int x) {
        if (x != parents[x]) {
            return parents[x] = find(parents[x]);
        }
        return x;
    }
}
 
class Edge implements Comparable<Edge>{
    int from, to, weight;
 
    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
 
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
