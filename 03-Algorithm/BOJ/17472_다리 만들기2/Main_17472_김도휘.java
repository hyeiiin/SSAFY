import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

public class Main {
    static int N, M, islandCnt;
    static List<Pos> islandList;
    static List<List<Pos>> allIslandList;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] parent;

    /**
     * 방향 중간에 바뀌면 안됨
     * 다리 길이 2 이상
     * 다리의 (가로, 세로) 방향과 같은 방향으로 섬이 있어야함
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        visited = new boolean[N][M];
        allIslandList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int index = 1;
        //섬 좌표 리스트에 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    islandList = new ArrayList<>(); //한 개의 섬
                    setIslandList(map, i, j, index);
                    index++;
                    allIslandList.add(islandList); //모든 섬에 추가
                    islandCnt++; //섬 개수
                }
            }
        }
        parent = new int[islandCnt + 1];
        for (int i = 1; i <= islandCnt; i++) {
            parent[i] = i;
        }
        PriorityQueue<Island> queue = new PriorityQueue<>();
        for (int p = 0; p < allIslandList.size(); p++) {
            List<Pos> nowIslandList = allIslandList.get(p);
            for (Pos now : nowIslandList) { //하나의 섬 중 한 좌표
                int x = now.x;
                int y = now.y;
                int start = map[x][y];

                //방향
                for (int i = 0; i < 4; i++) {
                    int tempR = dx[i];
                    int tempC = dy[i];
                    int bridge = 0;

                    while (x + tempR >= 0 && x + tempR < N && y + tempC >= 0 && y + tempC < M) {
                        if (map[x + tempR][y + tempC] == start) { //같은 섬 좌표
                            break;
                        } else if (map[x + tempR][y + tempC] != 0) { //다른 섬 만났다면
                            if (bridge >= 2) { //다리 길이가 2이상
                                queue.add(new Island(start, map[x + tempR][y + tempC], bridge));
                            }
                            break;
                        } else {
                            bridge++;
                        }

                        if (tempR > 0) {
                            tempR++;
                        } else if (tempR < 0) {
                            tempR--;
                        }
                        if (tempC > 0) {
                            tempC++;
                        } else if (tempC < 0) {
                            tempC--;
                        }
                    }
                }
            }
        }
        int useEdge = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            Island now = queue.poll();
            //아직 연결되어있지 않은 섬이라면
            if (find(now.start) != find(now.end)) {
                union(now.start, now.end);
                result += now.bridgeCnt; //다리 길이
                useEdge++;
            }
        }
        if (useEdge == islandCnt - 1) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }

    }

    public static void setIslandList(int[][] map, int x, int y, int index) {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(x, y));
        islandList.add(new Pos(x, y));
        map[x][y] = index;
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        map[nx][ny] = index;
                        islandList.add(new Pos(nx, ny));
                        queue.add(new Pos(nx, ny));
                    }
                }
            }

        }
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
        }

    }

    public static int find(int x) {
        if (x != parent[x]) {
            return parent[x] = find(parent[x]);
        }
        return x;
    }

}

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Island implements Comparable<Island> {
    int start, end, bridgeCnt;

    public Island(int start, int end, int bridgeCnt) {
        this.start = start;
        this.end = end;
        this.bridgeCnt = bridgeCnt;
    }

    @Override
    public int compareTo(Island o) {
        return this.bridgeCnt - o.bridgeCnt;
    }
}
