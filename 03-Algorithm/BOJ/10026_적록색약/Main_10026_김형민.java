package boj;
import java.io.*;
import java.util.*;
public class Main_10026_김형민 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        char [][] good = new char[N][N];//색약이 아닌사람 색 저장
        char [][] weakness =  new char[N][N];//색약 색 저장

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                char color = input.charAt(j);
                good[i][j] = color;//색약이 아닌경우는 정확하게 색 저장
                if (color=='G'||color=='R'){//색약일 경우 
                    weakness[i][j] = 'G';// G, R 을 G로만 저장
                }
            }
        }
        System.out.println(bfs(good)+" "+bfs(weakness));//답안 출력


    }
    static int bfs(char[][] arr){//bfs로 탐색
        int result = 0;
        ArrayDeque<Color> q = new ArrayDeque<Color>();
        int[][] move = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 모든 정점을 탐색한다.
                if (visited[i][j]) continue;//단 이미 탐색한 정점은 탐색 x

                q.addFirst(new Color(i,j));//탐색되지 않은 정점은 bfs 탐색 한다.
                while (!q.isEmpty()){// bfs 시작

                    Color node = q.pollLast();
                    if (visited[node.x][node.y]) continue;
                    visited[node.x][node.y] = true;

                    for (int[] mv : move) {
                        int dx = mv[0] + node.x;
                        int dy = mv[1] + node.y;
                        //범위를 벗어나거나, 이미 탐색했거나, 색이 같지 않은 경우는 넘어간다.
                        if (dx<0||dx>=N||dy<0||dy>=N||visited[dx][dy]||arr[node.x][node.y]!=arr[dx][dy])continue;
                        q.addFirst(new Color(dx,dy));
                    }
                }
                result++;
            }
        }
        return result;
    }


}
class Color{
    int x;
    int y;

    public Color(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
