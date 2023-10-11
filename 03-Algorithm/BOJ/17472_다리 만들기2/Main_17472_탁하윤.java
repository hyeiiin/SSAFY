package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_탁하윤 {
    static int N, M, map[][], land, min, parent[];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());    // 지도 세로 크기
        M = Integer.parseInt(st.nextToken());    // 지도 가로 크기
        map = new int[N][M];    

        for (int i = 0; i < N; i++) {   // 지도 정보 넣기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        land = 2;
        // 섬 구분하기 (bfs)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==1) makeLand(i, j);    // 섬이라면 섬 번호로 갱신
            }
        }

        // 섬과 다리 연결하기 (dfs)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]>=2) dfs(i, j, map[i][j]);  // 섬이라면 다리 만들기
            }
        }

        // 모든 섬을 연결하는 다리 길이의 최솟값 구하기 (크루스칼 알고리즘)
        min = 0;
        parent = new int[land]; // 섬들 부모 초기화
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int bridge = 0; // 연결된 다리 개수
        int size = pq.size();   // for문 돌 개수 (pq사이즈)
        for (int i = 0; i < size; i++) {
            int[] now = pq.poll();  // 현재 확인 다리
            int landFrom = find(now[0]);
            int landTo = find(now[1]);

            if( landFrom == landTo) continue;   // 부모가 같다면 이미 연결되어있거나 같은 섬

            min += now[2];  // 다리의 길이 더하기
            union(now[0], now[1]);  // 다리 연결 처리
            bridge++;   // 연결다리 수 증가
        }
        
        if(min == 0 || bridge != land-3) {   // min값이 초기값이거나 다리가 모든 섬에 연결되어 있지않다면 불가능
                                            // 현재 land의 값은 2부터 시작했기때문에 land-3이 모든섬의 수 -1 값
            System.out.println("-1");
        }else {
            System.out.println(min);
        }

    }

    /**
     * 섬에 번호 붙이기
     * @param i : x좌표
     * @param j : y좌표
     */
    private static void makeLand(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[] {i, j});
        visited[i][j] = true;
        map[i][j] = land;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int k = 0; k < 4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];

                if(nx<0||nx>=N||ny<0||ny>=M) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 1) {  // 섬이라면 번호 섬번호 갱신
                    q.offer(new int[] {nx, ny});
                    map[nx][ny] = land;
                    visited[nx][ny] = true;
                }
            }
        }

        land++; // 섬 번호 증가
    }

    /**
     * 섬과 다리 연결하기 :: 상하좌우 중 한 방향으로 계속 이동, 다른 섬이 나올 때까지 반복
     * @param i : x좌표
     * @param j : y좌표
     * @param landNum : 섬 번호
     */
    private static void dfs(int i, int j, int landNum) {
        int nx = i;
        int ny = j;
        int len = 0;

        for (int k = 0; k < 4; k++) {
            while(true) {   // 한쪽 방향으로 진행
                nx += dx[k];
                ny += dy[k];

                if(nx>=0&&nx<N&&ny>=0&&ny<M) {  // 범위안이라면
                    if(map[nx][ny] == landNum) {    // 같은 섬일 경우 다음 방향
                        nx = i;
                        ny = j;
                        len = 0;
                        break;
                    } else if(map[nx][ny] == 0) {   // 바다라면 다리 짓기
                        len++;
                    } else {
                        if(len > 1) {   // 다른 섬을 만났고, 다리 길이가 1 초과라면 pq에 담기
                            // pq :: landFrom, landTo, len
                            pq.offer(new int[] {landNum, map[nx][ny], len});
                        }
                        nx = i;
                        ny = j;
                        len = 0;
                        break;
                    }
                } else {
                    nx = i;
                    ny = j;
                    len = 0;
                    break;
                }
            }
        }
    }

    /**
     * a 부모 찾기
     * @param a
     * @return a의 부모 갱신
     */
    public static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    /**
     * 합치기
     * @param x
     * @param y
     */
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y) parent[x] = y;
        else return;
    }

}
