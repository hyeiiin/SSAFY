import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_김도현 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] edgeCount = new int[N+1];
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			edgeCount[B]++;
		}
		
	     // 위상정렬에 사용할 큐
        Queue<Integer> q = new LinkedList<>();

        // 진입차수가 0인 값 큐에 넣기
        for (int i = 1; i < N+1; i++) {
            if (edgeCount[i] == 0) {
                q.offer(i);
            }
        }
        
        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            // 큐에서 노드번호 꺼내기
            int nodeNo = q.poll();
            // 꺼낸 노드번호 정렬 결과값에 저장
            sb.append(nodeNo + " ");
            // 꺼낸 노드의 인접한 노드들 찾기
            List<Integer> list = graph.get(nodeNo);
            // 인접한 노드의 개수만큼 반복문 실행
//            for (Integer i : graph.get(nodeNo)) {
//				edgeCount[i]--;
//				if(edgeCount[i]==0) {
//					q.offer(i);
//				}
//			}
            for (int i = 0; i < list.size(); i++) {
                // 인접한 노드 진입차수 갱신
                edgeCount[list.get(i)] -- ;
                // 갱신된 노드의 진입차수가 0이면 큐에 노드 넣기
                if (edgeCount[list.get(i)] == 0) {
                    q.offer(list.get(i));
                }
            }
        }
		System.out.println(sb.toString());
	}

}
