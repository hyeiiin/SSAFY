import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int ans = -1;
    static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
    static int[][] map, edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==0) map[i][j] = -1;
            }
        }
        
        // 1. 섬 찾고 섬 번호로 라벨링하기 -> bfs
        boolean[][] visit = new boolean[N][M];
        int islandNum = 0; // 섬 번호
        ArrayList[] islands = new ArrayList[6]; // 섬 번호에 각 섬의 노드 리스트가 들어감
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j]||map[i][j]==-1) continue;
                islands[islandNum] = bfs(new Node(i, j), visit, islandNum);
                islandNum++;
            }
        }

        // 2. 섬과 섬을 이어주는 다리를 찾고 가장 짧은 다리를 저장하기 -> 단순 탐색
        edges = new int[islandNum][islandNum];
        int[] parents = new int[islandNum]; // 나중에 union find 에서 사용될 연결된 섬의 부모 찾는 배열
        for (int[] edge : edges) {
            Arrays.fill(edge,Integer.MAX_VALUE); // 가장 짧은 다리만 저장해야하기 때문에 max로 초기화
        }
        for (int i = 0; i < islandNum; i++) {
            findEdges(islands[i]); // 다리 찾기 로직
            parents[i] = i; // 부모찾는 배열 초기화
        }

        // 3. 모든 섬이 연결 된 경우의 가장 짧은 거리를 찾기 -> 최소 스패닝 트리(크루스칼)
        ArrayList<Eg> egs = new ArrayList<>(); // 간선들이 들어감.
        for (int i = 0; i < islandNum; i++) {
            for (int j = 0; j < islandNum; j++) {
                if (edges[i][j]==Integer.MAX_VALUE) continue; // max일땐 연결된 간선이 없는것 -> 넘긴다.
                egs.add(new Eg(i,j,edges[i][j]));
            }
        }

        //크루스칼 알고리즘 사용하기 위해 간선리스트 정렬
        Collections.sort(egs,(o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int temp = 0;
        int bridgeCount = 0;
        for (Eg eg : egs) {//크루스칼
            if (union(parents, eg.left, eg.right))continue;
            temp += eg.cost;
            bridgeCount++;
        }

        if (bridgeCount == islandNum - 1){
            ans = temp;
        }

        System.out.println(ans);
    }
    static int find(int[] parents, int i){
        if (parents[i]==i) return i;
        return parents[i] = find(parents, parents[i]);
    }
    static boolean union(int[] parents, int a, int b){
        int pA = find(parents, a);
        int pB = find(parents, b);
        if (pA==pB)return true;
        if (pA>pB){
            parents[pA] = pB;
        }else {
            parents[pB] = pA;
        }
        return false;
    }
    

    //섬 찾고 라벨링하기
    static ArrayList<Node> bfs(Node startNode, boolean[][] visit, int islandNum){
        ArrayList<Node> island = new ArrayList<>(); // 섬의 노드들을 모아서 리턴
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(startNode);
        while (!q.isEmpty()){

            Node node = q.poll();

            if (visit[node.x][node.y]) continue;
            visit[node.x][node.y] = true;

            map[node.x][node.y] = islandNum;//map에 섬 번호로 라벨링
            island.add(node);//섬 넣기

            //다음 노드로
            for (int[] mv : move) {
                int dx = mv[0] + node.x;
                int dy = mv[1] + node.y;
                if (isOutOfRange(dx,dy)||visit[dx][dy]||map[dx][dy]==-1) continue;
                q.add(new Node(dx,dy));
            }

        }
        return island;
    }
    static boolean isOutOfRange(int x, int y){
        return (x<0||x>=N||y<0||y>=M);
    }
    
    //다리 찾기
    static void findEdges(ArrayList<Node> island){
        //각 섬 마다의 모든 노드
        for (Node node : island) {
            //다리 찾기 로직
            for (int[] mv : move) {//사방으로 다른섬을 만날때까지 찾는다.
                int dx = node.x + mv[0];
                int dy = node.y + mv[1];
                int length = 0;
                while ((!isOutOfRange(dx,dy))&&map[dx][dy]==-1){ //범위 내이면서 바다(-1)일때만 다리를 연결한다.
//                    System.out.println("( "+dx+", "+dy+" )"+"map[dx][dy] = "+map[dx][dy]);
                    length++;
                    dx += mv[0];
                    dy += mv[1];
                }
//                System.out.println("( "+node.x+", "+node.y+" )"+"시작 노드");
//                System.out.println("( "+dx+", "+dy+" )"+"일때 다리가 끊김");
                if (isOutOfRange(dx,dy)||length<=1) continue; //범위를 벗어나서 다리가 끊긴 경우는 다리로 처리하지 않는다. 또한 다리길이가 2 이하면 다리로 처리하지 않는다.
                //다리로 연결해준다. 항상 섬과 섬의 가장 짧은 다리만 다리로 연결된다.
                edges[map[node.x][node.y]][map[dx][dy]] = Math.min(edges[map[node.x][node.y]][map[dx][dy]], length);
            }
            //다리 찾기 끝
        }
    }
}
class Eg{
    int left;
    int right;
    int cost;

    public Eg(int left, int right, int cost) {
        this.left = left;
        this.right = right;
        this.cost = cost;
    }

}
class Node{
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
