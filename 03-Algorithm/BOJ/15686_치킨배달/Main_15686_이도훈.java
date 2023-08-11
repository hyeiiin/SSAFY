import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15686_이도훈 {

       static int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static int[] selected;
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
        selected = new int[M];
        answer = Integer.MAX_VALUE;

        // 지도 만들기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) chickenList.add(new Pos(j, i));
                if(num == 1) houseList.add(new Pos(j, i));
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

            // 치킨집 -> 가정집 점수 구하기 (맨해튼 거리)
            int[] housePoint = new int[houseList.size()];
            Arrays.fill(housePoint, Integer.MAX_VALUE);

            for (int i = 0; i < selected.length; i++) {
                Pos chicken = chickenList.get(selected[i]);
                for (int j = 0; j < houseList.size(); j++) {
                    Pos house = houseList.get(j);
                    housePoint[j] = Math.min(housePoint[j], Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y));
                }
            }

            int sum = 0;

            for (int i = 0; i < housePoint.length; i++) {
                sum += housePoint[i];
            }

            answer = Math.min(answer, sum);
            return;
        }

        // 조합
        // 이전 값보다 큰 값부터 돌아야 조합 완성
        for (int i = prev + 1; i < chickenList.size(); i++) {
            if (chickenVisited[i]) continue;
            chickenVisited[i] = true;
            selected[depth] = i;
            comb(depth + 1, i);
            chickenVisited[i] = false;
        }
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

