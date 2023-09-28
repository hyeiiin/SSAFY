import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int ans = Integer.MAX_VALUE;
    static boolean[][] canMove;
    static int[][] dp;
    static int[][] move = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 미로의 세로 크기
        M = Integer.parseInt(st.nextToken()); //미로의 가로 크기
        canMove = new boolean[N][M]; //미로 임
        dp = new int[N][M]; //0,0에서 각 좌표까지 갈 수있는 경로의 수를 저장함
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j)=='1'){//갈수있는곳이면 true
                    canMove[i][j] = true;
                    dp[i][j] = -1;
                }
            }
        }

//        dfs(0,0,1,new boolean[N][M]);

        System.out.println(bfs(new Node(0,0,0)));

    }


    static int bfs(Node startNode){
        ArrayDeque<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.addLast(startNode);
        while(!q.isEmpty()){
            Node node = q.pollFirst();
            if (node.x==N-1&&node.y==M-1){
                return node.cost+1;
            }
            if (visited[node.x][node.y]) continue;
            visited[node.x][node.y] = true;
            for (int[] mv : move) {
                int dx = node.x + mv[0];
                int dy = node.y + mv[1];
                if (dx<0||dx>=N||dy<0||dy>=M||visited[dx][dy]||!canMove[dx][dy]) continue;
                q.addLast(new Node(dx,dy, node.cost+1));
            }
        }
        return -1;
    }



    static void dfs(int x, int y, int cnt, boolean[][] visited){

        if (x==N-1&&y==M-1){//도착 좌표까지 왔다면
            ans = Math.min(ans,cnt);// cnt와 저장된 ans를 비교해서 작은값 저장
            return;
        }

        for (int[] mv : move) {
            int dx = x+mv[0];//이동 x
            int dy = y+mv[1];//이동 y

            //범위 밖이거나, 이미 탐색한 좌표거나, 갈수없는 곳이거나, 현재 저장된 최소거리보다 이동을 많이 했다면 탐색 x
            if (dx<0||dx>=N||dy<0||dy>=M||visited[dx][dy]||!canMove[dx][dy]||(cnt)>ans) continue;
            visited[dx][dy] = true; // 방문체크
            dfs(dx,dy,cnt+1,visited);//다음 좌표로 이동
            visited[dx][dy] = false; // 방문 체크해제
        }
    }
}
class Node{
    int x;
    int y;
    int cost;
    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}
