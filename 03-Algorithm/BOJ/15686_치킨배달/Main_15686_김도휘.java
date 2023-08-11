import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<Pos> chickenList;
    static ArrayList<Pos> homeList;
    static ArrayList<Pos> choice = new ArrayList<>();
    static boolean[] visitedChicken;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chickenList = new ArrayList<>();
        homeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homeList.add(new Pos(i, j));
                }
                if (map[i][j] == 2) { //치킨집 정보저장
                    chickenList.add(new Pos(i, j));
                }
            }
        }
        visitedChicken = new boolean[chickenList.size()];
        DFS(0, 0);
        System.out.println(result);

    }

    public static void DFS(int start, int depth) {
        if (depth == M)
        {
            int sum = 0;
            for (Pos home : homeList) {
                int min = Integer.MAX_VALUE;
                for (Pos store : choice) {
                    int distance = Math.abs(home.x - store.x) + Math.abs(home.y - store.y);
                    min = Math.min(min, distance);
                }
                sum += min;
            }
            result = Math.min(result, sum);
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            if (!visitedChicken[i]) {
                visitedChicken[i] = true;
                choice.add(chickenList.get(i));
                DFS(i + 1, depth + 1);
                choice.remove(chickenList.get(i));
                visitedChicken[i] = false;
            }
        }
    }

}

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
