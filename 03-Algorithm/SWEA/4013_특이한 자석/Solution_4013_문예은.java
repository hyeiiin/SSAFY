import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_4013_문예은 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 특이한 자석(백준 톱니바퀴)
		/*
		 * k번 회전
		 * 3번7번 톱니의 자성이 다를 경우, 회전
		 * 1번~4번째의 1번톱니가 s극인 경우: 1 2 4 8점 획득
		 * 시계방향 1, 반시계 -1
		 * n극 0, s극 1
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			int K = Integer.parseInt(br.readLine()); // 회전 수
			// 자석 4개를 각각 linkedlist로 설정 (한번 회전할때마다 앞뒤로의 삭제삽입 가능하도록)
			LinkedList<Integer>[] magnet = new LinkedList[4];
			
			// 자석 초기 설정
			for (int i = 0; i < 4; i++) { 
				magnet[i] = new LinkedList<>(); // 자석 리스트 초기화
				st = new StringTokenizer(br.readLine()); // 자성 정보 입력
				for (int j = 0; j < 8; j++) {
					magnet[i].add(Integer.parseInt(st.nextToken())); // 각 톱니의 자성 입력
				}
			}
			
			// 회전 작업리스트 담을 큐 (왼쪽 오른쪽 탐색)
			Queue<int[]> turnList = new ArrayDeque<int[]>(); 
			// 회전리스트 생성 : 돌릴 자석번호, 돌릴 방향 담을 K개의 회전정보 배열
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int target = Integer.parseInt(st.nextToken())-1; // 회전시킬 자석번호
				int direc = Integer.parseInt(st.nextToken()); // 회전방향(시계방향 1)
				turnList.add(new int[] {target, direc}); // 초기에 주어진 자석번호는 무조건 회전한다.
				
				// 회전시킬 자석과 맞닿은 자석 및 2,6번 톱니 확인
				// target 자석의 왼쪽
				int temp = direc;
				for (int j = 1; j <= target; j++) {
					if (magnet[target-j].get(2) == magnet[target-j+1].get(6)) {
						break;
					} else {
						temp = temp*-1;
						turnList.add(new int[] {target-j, temp});
					}
				}
				// target 자석의 오른쪽
				int temp2 = direc;
				for (int j = target; j < 3; j++) {
					if (magnet[j].get(2) == magnet[j+1].get(6)) {
						break;
					} else {
						temp2 = temp2*-1;
						turnList.add(new int[] {j+1, temp2});
					}
				}
				
				// 자석 회전
				while (!turnList.isEmpty()) {
					int[] turnMagnet = turnList.poll();
					if (turnMagnet[1] == 1) { // 시계방향 회전이면, 맨 뒤 톱니가 맨 앞으로
						magnet[turnMagnet[0]].addFirst(magnet[turnMagnet[0]].pollLast());
					} else { // 반시계방향 회전이면, 맨 앞 톱니가 맨 뒤로
						magnet[turnMagnet[0]].addLast(magnet[turnMagnet[0]].pollFirst());
					}
				}
			}
			
			int answer = 0; // 최종 점수
			for (int i = 0; i < 4; i++) { // 긱 자석의 0번 톱니 극성
				if (magnet[i].get(0) == 1) { // 0번 톱니가 s극일때만
					answer += Math.pow(2, i);
				}
			}
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.println(sb.toString());
	}
}
