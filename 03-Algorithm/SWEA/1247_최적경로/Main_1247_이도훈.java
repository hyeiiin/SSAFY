

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {

    static int N;
    static Pos company;
    static Pos house;
    static ArrayList<Pos> customers;
    static boolean[] visited;
    static int answer;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {

            // 고객의 수
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            // 회사 좌표
            company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            house = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));


            customers = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                customers.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            visited = new boolean[N];
            answer = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");

        }
        System.out.println(sb);
    }

    // 순열
    public static void dfs(int depth, int prev, int distance) {
        if(distance >= answer) return;
        if (depth == N) {
            answer = Math.min(answer, distance + Math.abs(customers.get(prev).x - house.x) + Math.abs(customers.get(prev).y - house.y));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (depth == 0) {
                dfs(depth + 1, i, Math.abs(company.x - customers.get(i).x) + Math.abs(company.y - customers.get(i).y));
            } else {
                dfs(depth + 1, i, distance + Math.abs(customers.get(prev).x - customers.get(i).x) + Math.abs(customers.get(prev).y - customers.get(i).y));
            }

            visited[i] = false;
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
