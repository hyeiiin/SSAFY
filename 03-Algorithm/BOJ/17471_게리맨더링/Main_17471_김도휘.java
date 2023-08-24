import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] people;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int N;
    static int min = Integer.MAX_VALUE;
    static boolean isPossible;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //구역의 개수
        people = new int[N + 1]; //구역의 인구수
        list = new ArrayList[N + 1]; //구역마다 연결되어 있는 구역
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) { //i번째의 구역의 인구수
            people[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nearArea = Integer.parseInt(st.nextToken()); //인접한 구역의 개수
            for (int j = 0; j < nearArea; j++) {
                int node = Integer.parseInt(st.nextToken());
                list[(i + 1)].add(node); //무향 인접 그래프 노드 추가
            }
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            combi(1, 0, i);
        }
        if (isPossible) {
            System.out.println(min);
        }
        else {
            System.out.println(-1);
        }
    }

    public static void combi(int index, int depth, int cnt) {
        //A 구역은 방문 true, B 구역은 false
        if (depth == cnt) {
            List<Integer> Agroup = new ArrayList<>();
            List<Integer> Bgroup = new ArrayList<>();
            int aSum = 0;
            int bSum = 0;

            for (int i = 1; i <= N; i++) {
                if (visited[i]) { //A구역
                    Agroup.add(i);
                } else { //B구역
                    Bgroup.add(i);
                }
            }
            if (check(Agroup) && check(Bgroup)) {
                for (int num : Agroup) {
                    aSum += people[num];
                }
                for (int num : Bgroup) {
                    bSum += people[num];
                }
                min = Math.min(min, Math.abs(aSum - bSum));
                isPossible = true;
            }
            return;
        }
        for (int i = index; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combi(i + 1, depth + 1, cnt);
                visited[i] = false;
            }
        }
    }

    //리스트에 담긴 구역들이 연결되어 있는지 확인
    public static boolean check(List<Integer> areaList) {
        boolean[] visitedArea = new boolean[N + 1];
        if (areaList.isEmpty()) {
            return false;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(areaList.get(0));
        visitedArea[areaList.get(0)] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int n : list[now]) { //첫번째 구역과 인접한 구역들
                for (int areaNum : areaList) { //조합한 A 선거구안의 구역들
                    if (!visitedArea[areaNum]) {
                        if (n == areaNum) {
                            visitedArea[areaNum] = true;
                            queue.add(areaNum);
                        }
                    }
                }
            }
        }
        for (int num : areaList) {
            if (!visitedArea[num]) { //인접한 구역이 아니라면
                return false;
            }
        }
        return true;
    }
}
