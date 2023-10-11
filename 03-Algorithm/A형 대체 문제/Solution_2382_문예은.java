import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2382_문예은 {
	static class Micro {
		int r,c,cnt,head; // 미생물 좌표, 마리수, 이동방향
		public Micro(int r, int c, int cnt, int head) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.head = head;
		}
	}
	static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 미생물 격리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // N*N 배열
			int M = Integer.parseInt(st.nextToken()); // 진행 시간
			int K = Integer.parseInt(st.nextToken()); // 미생물 군집수
			
			Queue<Micro> queue = new ArrayDeque<>(); // 시간별 미생물 상태 담을 큐
			// 동일 칸에 모인 미생물군집 파악하기 위한 pq, 각 미생물 수와 이동방향 담기
			ArrayList<PriorityQueue<Micro>> mergePQ = new ArrayList<>();
			for (int i = 0; i < (N-2)*(N-2); i++) {
				PriorityQueue<Micro> pq = new PriorityQueue<>((o1, o2) -> {
					return Integer.compare(o2.cnt, o1.cnt); // 미생물 수의 내림차순으로 정렬
				});
				mergePQ.add(pq);
			}
			for (int k = 0; k < K; k++) { // 초기 미생물 군집 상태 입력
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int head = Integer.parseInt(st.nextToken())-1; //상하좌우 : del 인덱스와 맞춤
				queue.add(new Micro(r, c, cnt, head));
			}
			
			// 미생물 이동 시작
			for (int m = 0; m < M; m++) { // M초 만큼 진행(+배열 방문처리 용도)
				int qSize = queue.size(); // 현재 존재하는 미생물군집 수만큼만 진행
				for (int q = 0; q < qSize; q++) {
					Micro cur = queue.poll(); // 이동할 하나의 미생물군집
					int nextR = cur.r + del[cur.head][0];
					int nextC = cur.c + del[cur.head][1];
					int idx = (nextR-1)*(N-2)+nextC-1; // pq 삽입할 리스트 위치
					// 약품처리(가장자리)된 곳에 도착했다면, 개체 절감 후 이동방향 반대로
					if (nextR == 0 || nextR == N-1 || nextC == 0 || nextC == N-1) {
						if ((int)cur.cnt/2 == 0) continue;
						int head;
						if (cur.head < 2) { // 상하
							head = (cur.head+1)%2;
						} else { // 좌우
							head = (cur.head+1)%2+2;
						}
						queue.add(new Micro(nextR, nextC, (int)cur.cnt/2, head));
					}
					// 가장자리 아닌 곳에서 이동, 동일칸 인덱스에 맞춰 우선순위 큐에 추가
					else {
						mergePQ.get(idx).add(new Micro(nextR, nextC, cur.cnt, cur.head));
					}
				}
				PriorityQueue<Micro> pq;
				for (int i = 0; i < mergePQ.size(); i++) {
					pq = mergePQ.get(i);
					if (pq.isEmpty()) continue; // 해당 칸에 아무 미생물도 없을 때
					if (pq.size() == 1) { // 해당 칸에 미생물 군집 하나만 있을 때
						Micro mi = pq.poll();
						queue.add(new Micro(mi.r, mi.c, mi.cnt, mi.head));
					} else if(pq.size() > 1){ // 해당 칸에 미생물 여러개 있을 때
						// 군집이 모였다면, 개체 합산 및 높은 개체수의 이동방향으로
						int r = pq.peek().r;
						int c = pq.peek().c;
						int head = pq.peek().head; // 가장 미생물 수 많은 군집의 방향으로 전환
						int sum = 0;
						while (!pq.isEmpty()) {
							sum += pq.poll().cnt;
						}
						queue.add(new Micro(r, c, sum, head));
					}
				}
			}
			int answer = 0;
			for (Micro m : queue) {
				answer += m.cnt;
			}
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.println(sb.toString());
	}
	/*
	 * N*N 정사각형 구역 (5~100)
	 * 가장자리 특수약품처리
	 * 
	 * K개의 미생물 군집 (5~1000)
	 * 위치좌표, 군집별 미생물 수, 이동방향
	 * 상1 하2
	 * 좌3 우4
	 * 1시간마다 이동
	 * - 가장자리 도착, 미생물 절반 사망(나머지버림), 이동방향 반대로 바뀜 
	 * - 미생물 모이는 경우, 미생물 합, 이전에 미생물 수가 많은 방향으로 통일
	 * 
	 * M시간동안 진행(1~1000)
	 * 남은 미생물 수의 총합 구하기
	 */
}
