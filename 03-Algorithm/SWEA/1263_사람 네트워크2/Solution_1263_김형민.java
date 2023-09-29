import java.io.*;
import java.util.*;

// 궁금한점 플루이드 사용했을때 n^3 시간복잡도이고 현재 코드도 n^3 시간복잡도를 가지는데 
// 성능차이가 3배나 느립니다. 이유가 뭘까요.. gpt한테 물어봐도 컴파일러 탓이라고 이상한 소리만 하는데..
public class Solution_1263_김형민 {

    static int ans;
    static ArrayList[] graph;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<Integer>();
                for (int j = 0; j < N; j++) {
                    if (Integer.parseInt(st.nextToken())==1)graph[i].add(j);
                }
            }
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) { //모든 노드에 대하여
                ans = Math.min(ans,getDist(i,N)); // bfs로 모든 정점을 탐색한 최저값 찾는다.
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    //bfs 로 모든 정점 탐색하여 각 정점까지 도달한 최저 비용값을 저장한다.
    static int getDist(int startNode, int N){
        int result = 0; // 모든 최저 비용 값을 저장할 변수
        boolean[] visit = new boolean[N]; // 방문 배열
        ArrayDeque<Node> q = new ArrayDeque<>(); // queue
        q.add(new Node(startNode,0)); // 시작 노드 넣어주기
        while (!q.isEmpty()){

            Node node = q.poll();
            if (visit[node.index])continue;
            visit[node.index] = true;
            result += node.depth; // 현재 노드를 처음 왔다면 그 시점이 가장 빨리 온거임 (bfs) result에 저장

            ArrayList<Integer> nextNodes = graph[node.index];
            for (Integer nextNode : nextNodes) {
                if (visit[nextNode]) continue;//만약 다음 노드가 방문한적 있다면 재 방문 x
                q.add(new Node(nextNode,node.depth+1));// 다음 노드로
            }

        }
        return result;
    }

}
class Node{
    int index;
    int depth;

    public Node(int index, int depth) {
        this.index = index;
        this.depth = depth;
    }
}

