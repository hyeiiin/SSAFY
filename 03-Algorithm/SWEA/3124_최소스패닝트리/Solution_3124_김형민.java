package swea;
import java.util.*;
import java.io.*;
public class Solution_3124_김형민 {

    static int V, E;
    static ArrayList<Eg> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 노드의 개수
            E = Integer.parseInt(st.nextToken()); // 간선의 개수


            edges = new ArrayList<Eg>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                int nextNode = Integer.parseInt(st.nextToken());
                Long cost = Long.parseLong(st.nextToken());
                edges.add(new Eg(node,nextNode,cost));
            }

            Collections.sort(edges, (o1, o2) -> Long.compare(o1.cost, o2.cost));

            int[] parents = new int[V+1];
            for (int i = 0; i < V+1; i++) {
                parents[i] = i;
            }
            Long ans = 0L;
            for (Eg edge : edges) {
                if (union(parents, edge.left, edge.right))continue;
                ans += edge.cost;
            }

            sb.append(ans);

            sb.append("\n");
        }
        System.out.println(sb);

    }
    static int find(int[] arr, int i){
        if (arr[i]==i) return i;
        return arr[i] = find(arr,arr[i]);
    }
    static boolean union(int[] arr, int a, int b){
        int pA = find(arr, a);
        int pB = find(arr, b);
        if (pA==pB){//사이클이 있음
            return true;
        }
        arr[pB]= pA;
        return false;
    }


}
class Eg{

    int right;
    int left;
    Long cost;

    public Eg(int left, int right, Long cost) {
        this.left = left;
        this.right = right;
        this.cost = cost;
    }
}
