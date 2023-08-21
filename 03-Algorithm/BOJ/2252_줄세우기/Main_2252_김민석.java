package BOJ;

import java.io.*;
import java.util.*;

public class Main_2252_김민석 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		//입력
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//큐선언
		Queue<Integer> queue = new ArrayDeque<>();
		//정점에서 가는 진출차수 담을 map
		Map<Integer, List<Integer>> map = new HashMap<>();
		//진입차수 배열
		int[] inDegree = new int[N];
		//횟수만큼 반복
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			//출발
			int from = Integer.parseInt(st.nextToken())-1;
			//도착
			int to = Integer.parseInt(st.nextToken())-1;
			//이미 출발점으로 하는 리스트가 존재한다면
			if(map.containsKey(from)) {
				//get해서 넣어주기
				List<Integer> list = map.get(from);
				list.add(to);
				map.put(from, list);				
			//그렇지 않다면
			} else {
				//새로운 리스트 생성
				List<Integer> list = new ArrayList<>();
				list.add(to);
				map.put(from, list);
			}
			//진입차수 갱신
			inDegree[to]++;
		}
		
		//진입차수 0인것만 큐에 넣기
		for (int i = 0; i < N; i++) {
			if(inDegree[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			//현재 정점에서 나가는 간선들 리스트 불러오기
			List<Integer> list = map.get(cur);
			if(list != null) {
				for (Integer num : list) {
					//진입차수가 0이된게 있다면 큐에 추가
					if(--inDegree[num] == 0) {
						queue.offer(num);
					}
				}
			}
			//현재 진입차수0이어서 큐에서 뽑은 것 출력
			sb.append(cur+1).append(" ");
		}
		System.out.println(sb);
	}
}
