
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //수빈이 위치
        int K = Integer.parseInt(st.nextToken()); //동생 위치
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[100001];
        int[] dx = {1, -1};
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(N, 0));
        Arrays.fill(arr, -1); //방문했는지 안했는지 구분하기 위해 -1로 채움
        arr[N] = 0; //시작위치 0으로 구분
        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            if (now.x == K) { //수빈이 위치에 도달했다면
                sb.append(arr[K]);
                break;
            }
            int nx = 2 * now.x; //순간이동
            if (nx >= 0 && nx <= 100000) {
                if (arr[nx] == -1) { //아직 방문하지 않았다면
                    queue.offer(new Pos(nx, now.time + 1));
                    arr[nx] = now.time + 1;
                }
            }
            for (int i = 0; i < 2; i++) { //좌우
                int nx2 = now.x + dx[i];
                if (nx2 >= 0 && nx2 <= 100000) {
                    if (arr[nx2] == -1) { //아직 방문하지 않았다면
                        queue.offer(new Pos(nx2, now.time + 1));
                        arr[nx2] = now.time + 1;
                    }
                }
            }
        }
        System.out.println(sb.toString());

    }
}

class Pos {
    int x, time;

    public Pos(int x, int time) {
        this.x = x;
        this.time = time;
    }
}
