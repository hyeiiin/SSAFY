import java.io.*;
import java.util.*;

public class Solution_2115_김형민 {

    static int N, M, C, plusHoney, ans;
    static int[][] map;
    static ArrayList<Node> nodes;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 맵 크기
            M = Integer.parseInt(st.nextToken()); // 연속되는 수의 크기
            C = Integer.parseInt(st.nextToken()); // M개의 연속되는 배열의 수들의 합이 넘으면 안되는 수
            map = new int[N][N];
            nodes = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (j<N-M+1) nodes.add(new Node(i,j));
                }
            }
            // N*N 의 크기를 가진 배열 중
            // M개의 연속하는 배열의 모든 부분 집합의 합이 C를 넘지 않는 가장 큰 수 구하고
            // 가장 큰 수를 가진 조합 2개를 골라라 -> 수가 겹치면 안돼
            ans = 0;
            comb(nodes.size(),2,0,0,0,0,0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }
    /**
     * M개가 연속하는 배열을 2개 뽑는다. 이미 뽑은 곳은 중복을 허용하지 않고!
     * @param n n개는 항상 nodes의 크기가 된다.
     * @param r 항상 2개를 뽑을거임!
     * @param pickX //이전에 고른 x
     * @param pickY // 이전에 고른 y 끝 범위
     * @param start
     * @param cnt
     * @param sum // 합 트래킹
     */
    static void comb(int n, int r, int pickX, int pickY, int start, int cnt, int sum){
        if (cnt==r){//2개를 뽑았다면
            ans = Math.max(ans, sum); // max 값을 갱신하고 리턴
            return;
        }
        for (int i = start; i < n; i++) {
            Node node = nodes.get(i); // 이번 노드를 뽑아서
            int dx = node.x;
            int dy = node.y;
            if (dx==pickX && dy<pickY)continue; // 이미 뽑았던 범위 내가 아니라면
            plusHoney = 0; // 가장 큰 값이 들어감
            //해당하는 배열의 중복조합 구해서 가장 큰 값을 찾는다.
            for (int r2 = 1; r2 <= M; r2++) {
                comb2(dy+M, r2, 0, dx, dy, 0, 0);
            }
            //다음 배열 찾으러 간다.
            comb(n, r, dx,dy+M,start+1,cnt+1,sum+plusHoney);

        }
    }


    /**
     *  M개의 연속하는 배열의 모든 부분 집합의 합이 C를 넘지 않는 가장 큰 수 구하는 메서드
     * @param n n개 중에서 (항상 y~y+M 을 탐색하게 되니 항상 M개를 뽑는다.)
     * @param r 뽑는 개수
     * @param cnt cnt
     * @param x 현재 x
     * @param y 현재 y
     * @param sum 합을 트래킹
     * @param result 제곱의 합을 트래킹
     */
    static void comb2(int n, int r, int cnt, int x, int y, int sum, int result){
        if (sum>C) return; // 만약 C보다 크면 리턴시킨다.
        if (cnt==r){//r 개를 뽑았다면
            plusHoney = Math.max(plusHoney, result); // 가장 큰 제곱 합을 저장 한뒤 리턴
            return;
        }
        for (int i = y; i < n; i++) {
            comb2(n,r,cnt+1,x,i+1,sum+map[x][i], result+(map[x][i]*map[x][i]));
        }
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
