package swea;
import java.io.*;
import java.util.*;
public class Solution_1247_김형민 {

    static Node1247[] arr;
    static Node1247 startNode, endNode;
    static int ans;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\CodingTest\\src\\swea\\input (4).txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());//시작 x 좌표
            int startY = Integer.parseInt(st.nextToken());//시작 y 좌표
            startNode = new Node1247(startX,startY); //시작 노드 생성
            
            int endX = Integer.parseInt(st.nextToken());//도착 x좌표
            int endY = Integer.parseInt(st.nextToken());//도착 y좌표
            endNode = new Node1247(endX,endY);// 도착노드 생성

            arr = new Node1247[N];//방문해야할 노드들
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[i] = new Node1247(x,y);
            }
            ans = Integer.MAX_VALUE;
            perm(N,N,0,new boolean[N], new int[N]);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static void perm(int n, int r, int cnt, boolean[] visited, int[] result){
        if (r==cnt){
            Node1247 preNode = startNode;
            int sum = 0;
            for (int idx : result) {
                sum+=getDist(preNode, arr[idx]);
                preNode = arr[idx];
            }
            sum+=getDist(preNode,endNode);
            ans = Math.min(ans,sum);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            result[cnt] = i;
            perm(n,r,cnt+1,visited,result);
            visited[i] = false;
        }


    }

    static int getDist(Node1247 node1, Node1247 node2){
        return Math.abs(node1.x-node2.x)+Math.abs(node1.y-node2.y);
    }


}
class Node1247{
    int x;
    int y;


    public Node1247(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
