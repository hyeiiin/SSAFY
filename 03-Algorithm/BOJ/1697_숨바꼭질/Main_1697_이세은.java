import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_이세은 {

    private static int n, m;
    private static int[] arr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[100001];
        visited = new boolean[100001];
        if (n == m) {
            System.out.println(0);
        }
        else {
            bfs(n);
            System.out.println(arr[m]);
        }


    }

    public static void bfs(int i) {

        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        visited[i] = true;

        while (!q.isEmpty()) {
            int loc = q.poll();

            for (int k = 0; k < 3; k++) {
                int newLoc = 0;
                if (k == 0)
                    newLoc = loc - 1;
                else if (k == 1)
                    newLoc = loc + 1;
                else newLoc = loc * 2;
                //범위 체크, 아직 찾지 못하는 동안 진행
                if (newLoc >= 0 && newLoc < 100001 && !visited[newLoc] && arr[m] == 0) {
                    q.add(newLoc);
                    visited[newLoc] = true;
                    arr[newLoc] = arr[loc]+1; //턴 체크
                }
                if(newLoc == m) //도착
                    return;
            }
        }
    }
}
