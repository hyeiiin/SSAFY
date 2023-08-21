package boj;
import java.io.*;
import java.util.*;
public class Main_2252_김형민 {
    static int N, M;
    static int[] inEdgeCnt;
    static StringBuilder sb;
    static ArrayList[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());//학생 수
        M = Integer.parseInt(st.nextToken());//키를 비교한 회수
        graph = new ArrayList[N+1];// 그래프
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        inEdgeCnt = new int[N+1]; // 진입차수 개수 배열
        inEdgeCnt[0] = -1;// 없어도 되는 코드임 그냥 0은 안쓰는걸 표기하고 싶었음

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int stuA = Integer.parseInt(st.nextToken());
            int stuB = Integer.parseInt(st.nextToken());
            inEdgeCnt[stuB]++;//진입차수 기록
            graph[stuA].add(stuB);//그래프 연결
        }

        topologySort();//위상정렬 수행
        System.out.println(sb);// 출력
    }
    static void topologySort(){
        //위상 정렬에 사용할 큐
        ArrayDeque<Integer> q = new ArrayDeque<>();
        //진입 차수가 0인 모든 노드를 큐에 넣는다.
        for (int i = 1; i <= N; i++) {
            if (inEdgeCnt[i]==0){
                q.addLast(i);
            }
        }
        // 큐가 빌때 까지 반복한다.
        while (!q.isEmpty()){
            //큐에서 노드를 꺼낸다.
            Integer nodeNum = q.pollFirst();
            //그 노드를 적는다.
            sb.append(nodeNum).append(" ");
            //인접한 노드를 탐색한다.
            ArrayList<Integer> nextNodes = graph[nodeNum];
            for (Integer nextNode : nextNodes) {
                //이번 노드를 떼어 났으니 인접한 노드의 진입 차수를 1을 줄인다.
                inEdgeCnt[nextNode]--;
                if (inEdgeCnt[nextNode]==0){// 인접 노드의 진입 차수가 0이면
                    q.addLast(nextNode);//큐에 넣고 돌린다.
                }
            }
        }
        sb.append("\n");
    }
}