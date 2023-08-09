import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; //오른쪽 -> 아래 -> 왼쪽 -> 위
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //가로
        M = Integer.parseInt(st.nextToken()); //세로
        R = Integer.parseInt(st.nextToken()); //회전 횟수
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= R; i++) {
            turn(map);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void turn(int[][] map) {

        for (int i = 0; i < Math.min(N, M) / 2; i++) { //한번 돌리는데 돌려야하는 판의 수
            int x = i;
            int y = i;
            int temp = map[x][y]; //넣을 값 미리 빼놓기

            int index = 0;
            while (index < 4) {
                int nx = x + dx[index];
                int ny = y + dy[index];

                if (nx >= i && nx < N - i && ny >= i && ny < M - i) {
                    map[x][y] = map[nx][ny];
                    x = nx; //이돟한 좌표로 설정
                    y = ny;
                }
                else {
                    index++;
                }
            }
            map[i + 1][i] = temp; //map[1][0] = map[0][0], map[2][1] = map[1][1]
        }
    }
}
