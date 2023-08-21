package boj;
import java.io.*;
import java.util.*;
public class Main_1697_김형민 {
    static int N, K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;
        ArrayDeque<Nd> q = new ArrayDeque<>();//bfs를 위한 q 생성
        boolean[] visited = new boolean[100001];//visit 배열 생성
        q.addFirst(new Nd(N,0));//첫 노드 생성
        while (!q.isEmpty()){// bfs 탐색
            Nd node = q.pollLast();
            if (node.idx==K) {//만약 목표 노드에 도달했다면
                ans = node.cost;//이게 최소값이 맞음
                break;//bfs탐색 끝
            }
            if (visited[node.idx]) continue;//방문체크
            visited[node.idx] = true;//방문체크
            Nd[] nextNodes = getNextNodes(node);//다음 노드로 갈 수 있는 경우의수
            for (Nd nextNode : nextNodes) {
                //범위를 벗어나거나, 이미 다녀온 노드는 제외
                if (nextNode.idx<0||nextNode.idx>100000||visited[nextNode.idx]) continue;
                q.addFirst(nextNode);//다음 노드로 탐색
            }
        }
        System.out.println(ans);
    }
    static Nd[] getNextNodes(Nd node){
        Nd[] nextNodes = { new Nd(node.idx - 1, node.cost+1), new Nd(node.idx + 1, node.cost+1), new Nd(node.idx*2, node.cost+1)};
        return nextNodes;
    }
}
class Nd{
    int idx;
    int cost;

    public Nd(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

