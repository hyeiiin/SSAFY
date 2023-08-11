
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15686_이도훈 {

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static Pos[] selected;
    static int M;
    static int N;
    static ArrayList<Pos> chickenList;
    static ArrayList<Pos> houseList;

    static boolean[] chickenVisited;
    static int[][] map;

    static int answer ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로 세로 길이
        N = Integer.parseInt(st.nextToken());
        // 치킨집 최대 개수
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        // 치킨집들 위치
        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();
        selected = new Pos[M];
        answer = Integer.MAX_VALUE;

        // 지도 만들기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) chickenList.add(new Pos(j, i, 0));
                if(num == 1) houseList.add(new Pos(j, i, 0));
                map[i][j] = num;
            }
        }
        chickenVisited = new boolean[chickenList.size()];


        // 치킨집 C m 으로 조합 돌리기
        comb(0, -1);

        System.out.println(answer);
    }

    public static void comb(int depth, int prev) {
        // 치킨집 M개를 다 선택했다면
        if (depth == M) {
            // 점수 구하기 -> BFS
            Queue<Pos> queue = new ArrayDeque<>();
            int[][] point = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(point[i], Integer.MAX_VALUE);
            }


            // 선택된 치킨집부터 BFS 시작
            for (Pos chicken : selected) {
                queue.add(chicken);
            }

            while (!queue.isEmpty()) {
                Pos cur = queue.poll();

                if (point[cur.y][cur.x] <= cur.cnt) continue;

                point[cur.y][cur.x] = cur.cnt;

                // 사방 탐색
                for (int[] dir : dirs) {
                    int mX = dir[0] + cur.x;
                    int mY = dir[1] + cur.y;

                    // 배열 밖을 넘어가는지
                    if (mX < 0 || mY < 0 || mX >= N || mY >= N) continue;
                    // 이미 체크된 값이 더 작은지
                    if (point[mY][mX] <= cur.cnt + 1) continue;

                    queue.add(new Pos(mX, mY, cur.cnt + 1));
                }
            }
            int sum = 0;
            for (Pos house : houseList) {
                sum += point[house.y][house.x];
            }

            answer = Math.min(answer, sum);
            return;
        }

        // 조합
        // 이전 값보다 큰 값부터 돌아야 조합 완성
        for (int i = prev + 1; i < chickenList.size(); i++) {
            if (chickenVisited[i]) continue;
            chickenVisited[i] = true;
            selected[depth] = chickenList.get(i);
            comb(depth + 1, i);
            chickenVisited[i] = false;
        }
    }

    static class Pos {
        int x;
        int y;
        int cnt;

        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

