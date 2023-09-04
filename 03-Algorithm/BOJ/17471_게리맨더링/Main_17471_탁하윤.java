package ssafyalgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17471_탁하윤 {
    static int N, person[], result, total;
    static ArrayList<Integer>[] adj;
    static boolean selected[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());    // 구역 수
        result = Integer.MAX_VALUE; // 인구 차이 최솟값을 담을 변수
        total = 0;  // 전체 인구수
        selected = new boolean[N+1];    // 선택된 선거구
        adj = new ArrayList[N+1];   // 인접 리스트로 인접 구역 받기
        for (int i = 1; i <= N; i++) {  // 인접 리스트 초기화
            adj[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        person = new int[N+1];  // 각 구역의 인구 수를 담을 배열
        for (int i = 1; i <= N; i++) {  // 각 구역의 인구 수와 총 인구수 담기
            person[i] = Integer.parseInt(st.nextToken());
            total += person[i];
        }

        for (int i = 1; i <= N; i++) {  // 인접 리스트로 인접 구역 담기
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= N/2; i++) {    // 총 2구역만 나누면 되기 떄문에 절반만 뽑기
            combi(0, 1, 0, i);
        }
        if(result == Integer.MAX_VALUE){    // 만약 결과값이 초기값이라면 구역을 나눌 수 없는 경우
            result = -1;
        }
        System.out.println(result);
    }

    /**
     * 구역 뽑기
     * @param cnt : 현재 봅힌 구역 수
     * @param start : 뽑기 시작할 구역 번호
     * @param sum   : 현재까지 뽑힌 구역의 인구수 총합
     * @param end   : 뽑을 구역 수
     */
    static void combi(int cnt, int start, int sum, int end){
        if(cnt>=1 && cnt == end){   // 현재 뽑힌 구역이 존재하고 뽑을 구역 수 만큼 뽑았다면
            if(result > Math.abs((total-sum)-sum)) {    // 현재까지 뽑은 구역의 인구수 차이의 최솟값보다 작다면
                List<Integer> trueList = new ArrayList<>(); // 뽑힌 1구역
                List<Integer> falseList = new ArrayList<>();    // 안뽑힌 2구역
                for (int i = 1; i <= N; i++) {  // 뽑힌 구역은 trueList에 담고, 안 뽑힌 구역은 falseList에 담기
                    if(selected[i]) {
                        trueList.add(i);
                    }
                    else falseList.add(i);
                }
                if(checked(trueList) && checked(falseList)) {   // 뽑힌 구역과 안 뽑힌 구역이 모두 연결되어 있다면
                    result = Math.abs((total-sum)-sum); // 최솟값 갱신
                }
            }
            return;
        }
        for (int i = start; i <= N; i++) {  // 조합 뽑기
            selected[i] = true;
            combi(cnt+1, i+1, sum+person[i], end);
            selected[i] = false;
        }
    }

    /**
     * 각 구역이 연결되어 있는지 bfs로 확인하기
     * @param list : 각 구역 리스트
     * @return 연결되어 있다면 true, 연결되어 있지 않다면 false 리턴
     */
    static boolean checked(List<Integer> list) {
        boolean[] visited = new boolean[N+1];   // 방문 처리 배열
        Queue<Integer> q = new ArrayDeque<>();
        visited[list.get(0)] = true;    // 첫번째 구역 방문 처리
        q.offer(list.get(0));   // q에 넣기

        int cnt = 1;    // 한 구역 확인했으니 1부터 시작

        while (!q.isEmpty()){   // q가 공백 q가 될 때 까지
            int now = q.poll(); // 현재 확인할 구역 번호

            for (int i = 0; i < adj[now].size(); i++) { // 현재 확인할 구역의 인접한 구역을 모두 돌면서
                int next = adj[now].get(i);
                if(list.contains(next) && !visited[next]) { // 구역에 다음 구역이 포함되어있고 방문하지 않은 구역이라면
                    q.offer(next);  // q에 넣고 방문처리, 확인 구역 수 +1 하기
                    visited[next] = true;
                    cnt++;
                }
            }
        }

        if(cnt == list.size()) {    // 확인된 구역이 list의 사이즈만큼이라면, 모두 연결되어 있으므로 true
            return true;
        } else {
            return false;   // 확인된 구역이 list의 사이즈만큼이 아니라면, 연결되어 있지 않는 경우
        }
    }
}
