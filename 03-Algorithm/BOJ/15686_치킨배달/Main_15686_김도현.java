package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686_김도현 {

    static int res, M;
    static ArrayList<int[]> chList, homeList;
    static boolean visited[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 크기
        M = Integer.parseInt(st.nextToken()); // 치킨 집 선택 수

        int[][] map = new int[N][N]; // 도시 정보

        chList = new ArrayList<>();    // 치킨 집 리스트
        homeList = new ArrayList<>();  // 도시 리스트
        res = Integer.MAX_VALUE;       // 결과 값

        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map.length; j++) {
                int temp[] = new int[2];
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { // 치킨 집 리스트
                    temp[0] = i;
                    temp[1] = j;
                    chList.add(temp);
                }
                if (map[i][j] == 1) { // 집 리스트
                    temp[0] = i;
                    temp[1] = j;
                    homeList.add(temp);
                }
            }
        }

        visited = new boolean[chList.size()];
        selected(0, 0);
        System.out.println(res);

    }

    // 조합을 사용하여 치킨 집 선택하는 함수
    public static void selected(int start, int depth) {
        if (depth == M) {
            cal();
            return;
        }
        for (int i = start; i < chList.size(); i++) {
            visited[i] = true;
            selected(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    // 최솟값을 계산해야한다.
    public static void cal() {
        int sum = 0;
        for (int i = 0; i < homeList.size(); i++) {  // 특정 집에서
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < chList.size(); j++) { // 선택된 치킨 집들과의 거리 계산
                if (visited[j]) {
                    int num1 = Math.abs(homeList.get(i)[0] - chList.get(j)[0]);
                    int num2 = Math.abs(homeList.get(i)[1] - chList.get(j)[1]);
                    min = Math.min(min, num1 + num2);
                }
            }
            sum += min;  // 그렇게 해서 선정된 가장 가까운 치킨 거리를 더해줌
        }
        res = Math.min(sum, res);
    }
}
