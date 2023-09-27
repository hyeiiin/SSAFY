import java.io.*;
import java.util.*;

public class Main{
    static int needBeerDist = 50;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            //편의점 개수
            int N = Integer.parseInt(br.readLine());
            
            //시작 노드 좌표
            st = new StringTokenizer(br.readLine());
            int startX = (Integer.parseInt(st.nextToken())+32768);
            int startY = (Integer.parseInt(st.nextToken())+32768);
            
            //편의점 좌표들
            Node[] stores = new Node[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int storeX = Integer.parseInt(st.nextToken())+32768;
                int storeY = Integer.parseInt(st.nextToken())+32768;
                stores[i] = new Node((storeX), (storeY));
            }
            
            //도착 x, y
            st = new StringTokenizer(br.readLine());
            int endX = (Integer.parseInt(st.nextToken())+32768);
            int endY = (Integer.parseInt(st.nextToken())+32768);
            
            //스토어 방문 체크
            boolean[] visitedStore = new boolean[N];
            
            //큐 생성
            ArrayDeque<Node> q = new ArrayDeque<>();
            //시작좌표 (상근이)
            q.add(new Node(startX,startY));

            String ans = "sad";
            while (!q.isEmpty()){
                Node node = q.pollLast();
                // 맥주를 사지않고 도착할수 있는 경우
                int minDist = getMinDist(node.x, node.y, endX, endY);
                if (minDist<=20*needBeerDist){
                    ans="happy";
                    break;
                }
                //중간에 맥주를 사야하는 경우
                for (int storeIdx = 0; storeIdx < N; storeIdx++) {
                    Node storeNode = stores[storeIdx];
                    minDist = getMinDist(node.x, node.y, storeNode.x, storeNode.y);

                    //편의점이 갈수 없는곳(맥주 한박스로 갈수 없는곳) 이면 패스한다.
                    if (minDist>20*needBeerDist||visitedStore[storeIdx]) continue;

                    visitedStore[storeIdx] = true;
                    q.add(storeNode);
                }

            }
            sb.append(ans).append("\n");

        }

        System.out.println(sb);
    }

    private static int getMinDist(int startX, int startY, int endX, int endY) {
        return Math.abs(startX-endX)+Math.abs(startY-endY);
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
