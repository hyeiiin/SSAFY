package boj;
import java.io.*;
import java.util.*;
public class Main_1753_김형민 {

    static int V, E, startNodeIdx;
    static ArrayList[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken())+1;
        E = Integer.parseInt(st.nextToken());
        startNodeIdx = Integer.parseInt(br.readLine());


        graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());

            int node = Integer.parseInt(st.nextToken());
            int nextNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[node].add(new Nod(nextNode, cost));//그래프 연결
        }
        
        int[] minDist = getMinDist(new Nod(startNodeIdx, 0));//다익스트라

        //답안출력
        for (int i = 1; i < V; i++) {
            if (minDist[i]== Integer.MAX_VALUE){
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(minDist[i]).append("\n");
        }
        System.out.println(sb);
    }
    // 다익스트라
    static int[] getMinDist(Nod startNode){

        int[] minDist = new int[V]; // 시작 노드로부터 각 노드번호까지 최저거리가 들어감
        Arrays.fill(minDist, Integer.MAX_VALUE);//최저거리가 들어가야하니 max로 초기화
        minDist[startNode.num] = 0;// 시작부터 시작까지는 거리가 0임
        boolean[] visited = new boolean[V];//방문체크 배열 체크
        PriorityQueue<Nod> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));//간선의 거리의 최소힙 생성
        q.add(startNode);//시작 노드 추가

        while (!q.isEmpty()){
            Nod node = q.poll();//노드빼기
            if (visited[node.num]) continue;//재방문 안함
            visited[node.num] = true;// 방문체크

            ArrayList<Nod> nextNodes = graph[node.num];//현재 노드에서 갈 수 있는 노드들
            for (Nod nextNode : nextNodes) {
                if (minDist[nextNode.num]>minDist[node.num]+ nextNode.cost){//현재까지 저장된 최소 거리가 시작노드에서 현재노드까지의 최소 거리 + 현재 노드에서 다음 노드까지의 거리 보다 크다면
                    minDist[nextNode.num] = minDist[node.num]+ nextNode.cost;//값 갱신
                    q.add(new Nod(nextNode.num, minDist[nextNode.num]));//다음노드로 이동
                }
            }
        }
        return minDist;
    }
}
class Nod{
    int num;
    int cost;

    public Nod(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}
