import java.util.*;
import java.io.*;

public class Solution {
    static int N, ans, ansCount;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static ArrayList<Core> cores;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            boolean[][] visit = new boolean[N][N];
            cores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j]==1) {
                        visit[i][j] = true;
                        if (i==0||i==N-1||j==0||j==N-1) continue;
                        cores.add(new Core(i,j));
                    }
                }
            }
            ans = 999999;
            ansCount = -1;
            dfs(0,0,visit,0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int idx, int len, boolean[][] visit, int conCount){

        if (idx==cores.size()){
            if (ansCount<conCount){
                ansCount = conCount;
                ans = len;
            }
            if (ansCount==conCount){
                ans = Math.min(ans, len);
            }
            return;
        }

        Core core = cores.get(idx);

        //프로세스 연결하기
        for (int[] mv : move) {
            int dx = mv[0] + core.x;
            int dy = mv[1] + core.y;
            boolean con = false;
            ArrayList<Core> visitNode = new ArrayList<>();
            while (dx>=0&&dx<N&&dy>=0&&dy<N){
                if (visit[dx][dy]) { // 연결 못하는 경우
                    con = false;
                    break;
                }
                //연결 가능한 경우
                visitNode.add(new Core(dx,dy));
                con = true;
                dx += mv[0];
                dy += mv[1];
            }
            if (con){ // 연결가능한 경우
                for (Core node : visitNode) {// 연결 처리
                    visit[node.x][node.y] = true;
                }
                dfs(idx+1,len+visitNode.size(), visit, conCount+1); // 다음 코어 연결하러 가기
                for (Core node : visitNode) {// 다시 끊어주기
                    visit[node.x][node.y] = false;
                }
            }
        }
        dfs(idx+1, len, visit, conCount);
    }

}
class Core{
    int x;
    int y;

    public Core(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
