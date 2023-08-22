package boj;

import java.io.*;
import java.util.*;


public class Main_13023_김형민 {
    static int N, M;
    static ArrayList[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            if (graph[node1]==null){
                graph[node1] = new ArrayList<Integer>();
            }
            if (graph[node2]==null){
                graph[node2] = new ArrayList<Integer>();
            }

            graph[node1].add(node2); //양방향
            graph[node2].add(node1); // 그래프 연결

        }
        int ans = 0;
        for (int node = 0; node < N; node++) {
            if (dfs(node, new boolean[N], 0)) {//만약 5개의 노드가 일직선으로 연결되어있다면 true
                ans = 1;
                break;
            }
        }
        System.out.println(ans);
    }
    static boolean dfs(int node, boolean[] visited, int cnt){
        if (cnt==5){//깊이가 5까지 간다면 일렬로 친구관계가 이어져있다는 것임
            return true;
        }

        ArrayList<Integer> nextNodes = graph[node]; //다음 노드들
        if (graph[node]==null) return false;//연결되어 있을때만, 그래프를 생성해서 리스트가 없을수도 있음
        for (Integer nextNode : nextNodes) {
            if (visited[nextNode]) continue;// 방문한곳은 갈필요 없어
            visited[nextNode] = true;// 방문처리
            if (dfs(nextNode, visited, cnt+1)) return true;//만약 일직선을 찾으면 더 할필요 없어 true 리턴
            visited[nextNode] = false;//그게아니면 방문 처리 풀고 
        }
        return false;//false 리턴
    }

}
