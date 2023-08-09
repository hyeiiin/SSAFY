package swea;
import java.io.*;
import java.util.*;
public class Solution_1861_김형민 {
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\CodingTest\\src\\swea\\input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int n2 = 0; n2 < N; n2++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[n][n2] = num;
                }
            }

            int[][] move = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
            int[][] result = new int[N][N];
            PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> {
                int compare = Integer.compare(o2.cost, o1.cost);
                if (compare == 0) {
                    return Integer.compare(o1.num, o2.num);
                }
                return compare;
            });
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {

                    ArrayDeque<Node> queue = new ArrayDeque<>();
                    queue.add(new Node(x,y,arr[x][y],1));
                    boolean[][] visited = new boolean[N][N];

                    while(!queue.isEmpty()){
                        Node node = queue.poll();
                        if (visited[node.x][node.y]) continue;
                        visited[node.x][node.y] = true;
                        for (int[] mv : move) {
                            int dx = mv[0]+node.x;
                            int dy = mv[1]+node.y;
                            if (dx<0||dx>=N||dy<0||dy>=N||(arr[dx][dy]-arr[node.x][node.y])!=1) continue;
                            result[x][y] = node.cost+1;
                            queue.addLast(new Node(dx,dy,arr[dx][dy],result[x][y]));
                        }
                    }
                    nodes.add(new Node(x,y,arr[x][y],result[x][y]));
                }
            }
            Node node = nodes.peek();
            sb.append(node.num).append(" ").append(node.cost).append("\n");

        }
        System.out.println(sb);
    }
}
class Node{
    int x;
    int y;
    int num;
    int cost;


    public Node(int x, int y,int num,int cost) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.cost = cost;

    }
}
