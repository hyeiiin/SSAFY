import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_문예은 {

	public static void main(String[] args) throws IOException {
		/*
		 * 현재 점 N
		 * 목표 점 K
		 * 걷기 : x-1 또는 x+1
		 * 순간이동 : 2*x 위치
		 * 목표점 도달하는 가장 빠른 시간
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 현재점
		int K = Integer.parseInt(st.nextToken()); // 목표점
		int time = 0; // 걸린 시간 카운트
		Queue<Integer> queue = new ArrayDeque<Integer>(); // 현재점에서 1초뒤 갈 수 있는 모든 좌표 넣을 큐
		boolean[] visit = new boolean[100001]; // 가지치기를 위한 방문배열(이전에 방문했던 좌표에 다시 도달하면 싸이클 생기므로 다시 볼 필요없음)
		
		queue.offer(N); // 현재점 큐에 넣기
		while(true) { // 큐를 차례로 poll 하다가, 목표점인 정수를 발견하면 종료
			int size = queue.size();
			for (int s = 0; s < size; s++) { // 현재 큐 내부에 있는 좌표개수만큼 반복, 반복되는 한 턴이 1 depth
				if(queue.peek()==K) { // 목표점 도달하면
					System.out.println(time); // 걸린 시간 출력
					return;
				}
				if (visit[queue.peek()]) { // 이전에 방문한적 있는 숫자는 가지치기
					queue.poll(); // 큐 추가 없이 그냥 꺼내 버리기
					continue;
				}
				
				int current = queue.poll(); // 현재 위치
				if (current == 0) { // 가장 왼쪽 끝에 도착
					queue.offer(current+1); // 오른쪽으로만 이동 가능, 2배 해도 0이므로 생략
				} else if (current > 50000 && current < 100000 ) { // 현재 좌표의 2배수가 가장 오른쪽 끝 범위를 넘어가면
					queue.offer(current+1); // 한칸씩 이동만 가능
					queue.offer(current-1);
				} else if (current == 100000) { // 가장 오른쪽 끝에 도착
					queue.offer(current-1); // 왼쪽으로만 이동 가능
				} else {
					queue.offer(current*2);
					queue.offer(current+1);
					queue.offer(current-1); // 현재로부터 이동가능한 좌표 모두 큐에 넣기
				}
				visit[current] = true; // 방문한 좌표는 방문배열 넣어 이후에 재방문 여부 체크하기
			}
			time += 1; // 1 depth 끝날 때마다 시간 증가
		}
	}
}