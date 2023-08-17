import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int min;
    static Pos home, company;
    static Pos[] arr;
    static boolean[] visited;
    static int[] customer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new Pos[N + 2];
            visited = new boolean[N + 2];
            customer = new int[N];
            min = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());
            company = new Pos(cx, cy); //회사

            int hx = Integer.parseInt(st.nextToken());
            int hy = Integer.parseInt(st.nextToken());
            home = new Pos(hx, hy); //집
            arr[0] = company;

            for (int j = 0; j < N; j++) { //고객
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[j + 1] = new Pos(x, y);

            }
            arr[N + 1] = home;
            permutation(0);
            sb.append("#" + (t + 1)).append(" ");
            sb.append(min).append("\n");

        }
        System.out.println(sb);

    }

    public static void permutation(int cnt) {
        if (cnt == N) {
            distance();
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                customer[cnt] = i;
                permutation(cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static void distance() {
        int sum = 0;
        sum += Math.abs(arr[customer[0]].x - company.x) + Math.abs(arr[customer[0]].y - company.y);
        for (int i = 1; i < N; i++) {
            sum += Math.abs(arr[customer[i]].x - arr[customer[i - 1]].x) + Math.abs(arr[customer[i]].y - arr[customer[i - 1]].y);
        }
        sum += Math.abs(home.x - arr[customer[N - 1]].x) + Math.abs(arr[customer[N - 1]].y - home.y);
        min = Math.min(min, sum);
    }
}
class Pos
{
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
