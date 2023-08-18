import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Integer> archerPositions;
    static int maxKill;
    static int kill;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        D = Integer.parseInt(st.nextToken()); //공격 거리 제한
        visited = new boolean[M];
        map = new int[N + 1][M]; //맨 마지막 행은 궁수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        position(0, 0);
        System.out.println(maxKill);

    }

    //궁수 자리 배치 (조합) mC3
    public static void position(int index, int cnt) {
        if (cnt == 3) { //궁수 3명 배치 끝
            archerPositions = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                if (visited[i]) { //궁수라면
                    archerPositions.add(i);
                }
            }
            castleDefense();
            return;
        }
        for (int i = index; i < M; i++) {
            if (!visited[i]) {
                visited[i] = true;
                position(i + 1, cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static void castleDefense() {

        ArrayList<Pos> list = new ArrayList<>();
        int num = 0;
        kill = 0;
        int[][] tempMap = new int[N][M];
        int tempKill = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.arraycopy(map[i], 0, tempMap[i], 0, M);
            }
        }

        while (num < N) {
            //궁수행 하나씩 올리기
            ArrayList<Pos> oneRowPos = new ArrayList<>();
            boolean isPossible = true;

            for (int arcP : archerPositions) {

                int arcX = N - num; //궁수 좌표
                int arcY = arcP;

                int minDistance = Integer.MAX_VALUE;
                int minX = -1;
                int minY = -1;

                for (int i = N - num - 1; i >= 0; i--) { //마지막 행부터
                    for (int j = 0; j < M; j++) {
                        if (tempMap[i][j] == 1) { //적이라면
                            int diff = Math.abs(arcX - i) + Math.abs(arcY - j);

                            if (diff > D) { //공격가능거리 벗어난다면
                                continue;
                            }

                            if (minDistance > diff) { //공격 최소 거리로 설정
                                minDistance = diff;
                                minX = i; //적의 최소좌표 갱신
                                minY = j;
                            } else if (minDistance == diff) {
                                if (minY > j) {
                                    minX = i;
                                    minY = j; //가장 왼쪽으로 설정
                                }
                            }
                        }
                    }
                }
                if (minX == -1 || minY == -1) {
                    continue;
                }
                oneRowPos.add(new Pos(minX, minY));
            }
            //한 행 끝
            for (int i = 0; i < oneRowPos.size(); i++) {
                if (tempMap[oneRowPos.get(i).x][oneRowPos.get(i).y] != 0) { //중복은 0 처리
                    tempKill++;
                    tempMap[oneRowPos.get(i).x][oneRowPos.get(i).y] = 0;
                }
            }
            num++;
        }
        maxKill = Math.max(maxKill, tempKill);

    }

}

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
