import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1260_탁하윤 {
    static int N;
    static LinkedList<Integer>[] adjList;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 정점 개수
        int M = Integer.parseInt(st.nextToken());   // 간선 개수
        int V = Integer.parseInt(st.nextToken());   // 탐색을 시작할 정점 번호


        adjList = new LinkedList[N+1];  // 인접 리스트
        for (int i = 1; i <= N; i++) {
            adjList[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adjList[v1].add(v2);    // 무향 그래프 이기 때문에 인접 리스트에 둘 다 넣기
            adjList[v2].add(v1);
        }

        for (int i = 1; i <=N ; i++) {
            Collections.sort(adjList[i]);   // 방문 순서를 위해 오름차순 정렬
        }

        visited = new boolean[N+1]; // 방문 여부 검사
        dfs(V); // dfs 호출
        System.out.println();
        bfs(V); // bfs 호출

    }

    static void dfs(int v){ // v: 시작 정점
        System.out.print(v + " ");
        visited[v] = true;  // 방문 처리

        for (int i = 0; i < adjList[v].size(); i++) {   // 정점의 인접 정점들 탐색
            int next = adjList[v].get(i);   // 다음 정점이 방문하지 않았다면 재귀를 통해 호출
            if(!visited[next]){
                dfs(next);
            }
        }
    }

    static void bfs(int v){ // v: 시작 정점
        visited = new boolean[N+1]; // 방문 여부 검사 배열 새로 만들기
        Queue<Integer> q = new ArrayDeque<>();  // 탐색할 정점을 넣을 큐
        visited[v] = true;  // 현재 정점 방문처리
        q.offer(v); // 큐에 현재 정점 넣기

        while(!q.isEmpty()){    // 공백 큐가 될 때 까지
            v = q.poll();   // 현재 정점 꺼내기
            System.out.print(v+" ");

            for (int i = 0; i < adjList[v].size(); i++) {   // 정점의 인접 정점들 탐색 
                if(!visited[adjList[v].get(i)]){    // 인접 정점 아직 방문 안했다면 큐에 넣기
                    q.offer(adjList[v].get(i));
                    visited[adjList[v].get(i)] = true;  // 현재 정점 방문 처리
                }
            }
        }
    }
}
