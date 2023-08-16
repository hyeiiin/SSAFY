import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6987_한정수 {
	
	static int[] win_arr;
	static int[] lose_arr;
	static int[] draw_arr;
	
	static int[][] match_team;  // match_team[몇번째경기][0,1] 0은 나, 1은 상대
	static boolean back_tracking_res = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		PriorityQueue<Integer> max_heap = new PriorityQueue<>(Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		
		for (int n=0; n<4; n++) {
			back_tracking_res = false;
//			max_heap.clear();
			win_arr = new int[6];
			lose_arr = new int[6];
			draw_arr = new int[6];
			st = new StringTokenizer(br.readLine());
			for (int i=0 ; i<6; i++) {
				win_arr[i] = Integer.parseInt(st.nextToken());
				int temp = Integer.parseInt(st.nextToken());
				draw_arr[i] = temp;
				lose_arr[i] = Integer.parseInt(st.nextToken());
			}
		
			//배열 입력 받고.
			//판정해야됨.
			// 1. 총 합이 30이 나와야함.
			// 2. 모든 승의 합 = 모든 패의 합
			// 3. 각 나라의 승무패 합은 5여야함.
			// 4. 무승부의 합은 반드시 짝수
			//    4-2.  >> 무승부가 있으면 반드시 상대도 무승부가 있어야함. 최댓값에서 1빼고 나머지에서 1개 빼고.
			//             >> 무승부 개수를 정렬하고, 최댓값 -1,  그다음 최댓값 -1 하는 식으로 해서 그 결과 0 0 0 0 0 0 이면 끝. 4 0 0 0 0 0 이런식이면 안됨.
			//                 >>힙을 써볼까?     오케이 써보자. 최대 힙
			// 3.
			// ============================
			// 무조건 서로다른팀이랑 붙어야함. 즉, 붙었는지 안붙었는지 체크할 무언가가 필요함.
			
			//===========================
			//다 필요없고 그냥 무식하게 ABCDEF 6C2 경우의수 싹다 갈아넣어서 되는 경우의수만 찾는게 답????
			// 싹 다 뒤지는데, A/B 가 승/패인지 무/무 인지 패/승인지 모름
			// >> 전부 다 돌려보고 계속 진행하다가 중간에 결과 틀어지면 돌아나와야함.
			
			
			
			// match_team[몇번째경기][0,1] 0은 나, 1은 상대   
			// 0:A  1:B  2:C  3:D  4:E  5:F
			// [0][0] = 0  [0][1] = 1
			// [1][0] = 0  [1][1] = 2
			// [2][0] = 0  [2][1] = 3
			// [3][0] = 0  [3][1] = 4
			// [4][0] = 0  [4][1] = 5
			// [5][0] = 1  [5][1] = 2
			// [6][0] = 1  [6][1] = 3
			// [7][0] = 1  [7][1] = 4
			// [8][0] = 1  [8][1] = 5
			// [9][0] = 2  [9][1] = 3
			// [10][0] = 2 [10][1] = 4
			// [11][0] = 2 [11][1] = 5
			// [12][0] = 3 [12][1] = 4
			// [13][0] = 3 [13][1] = 5
			// [14][0] = 4 [14][1] = 5

			
			match_team = new int[15][2];
			int idx = 0;
			for (int i=0; i<=4; i++) {
				for (int j=i+1; j<=5; j++) {
					match_team[idx][0] = i;
					match_team[idx][1] = j;
					idx += 1;
				}
			}
			
			//1. 모든 승의 합 = 모든 패의 합
			//2. 총 합 30
			int win_sum = 0;
			int lose_sum = 0;
			int draw_sum = 0;
			boolean sum_5 = true;
			for (int i=0 ; i<6 ; i++) {
				win_sum += win_arr[i];
				lose_sum += lose_arr[i];
				draw_sum += draw_arr[i];
				
				//3. 승무패 합은 5
				if(win_arr[i] + lose_arr[i] + draw_arr[i] != 5) {
					sum_5 = false;
					break;
				}
			}
			if(!sum_5) {
				
				sb.append(0+" ");
				continue;
			}
			if(win_sum != lose_sum) {
				sb.append(0+" ");
				continue;
			}
			if(win_sum + lose_sum + draw_sum != 30) {
				sb.append(0+" ");
				continue;
			}
			
			//4. 무승부 합이 짝수여야함.
			boolean draw_result = true;
			if((draw_sum % 2) != 0) {
				sb.append(0+" ");
				continue;
			}
			
			
			//각 15개 매치 진행하면서 승패,무무,패승을 빼고 맞는지 계속 검사
			// 싹 다 뒤지는데, A/B 가 승/패인지 무/무 인지 패/승인지 모름
			//     >> 전부 다 돌려보고 계속 진행하다가 중간에 결과 틀어지면 돌아나와야함.
			//          >> 그렇게 해서 재귀 돌아간 횟수가 15번이면 매치가 15번 돌아간거니까 ok. 
			//백트래킹 실행.
			
//			System.out.println(n+" 백트래킹 시작");
			back_tracking(0, 0);
			if(back_tracking_res) {
				sb.append(1+" ");
			}
			else {
				sb.append(0+" ");
			}
			
			
			
			
//			else {
//				
//				//4-2.무승부 합이 짝수라면, 무승부 개수가 타당한지 봐야함.
//				//일단 
//				while (!max_heap.isEmpty()) {
//					int temp1 = -1;
//					int temp2 = -1;
//					temp1 = max_heap.poll();
////					if(temp1 -1 > 0) {
////						max_heap.add(temp1 -1);
////					}
//					//일단 1개 빼고,
//					//2번째 빼려할때 비어있으면 break. 잘못된거임.
//					if (max_heap.isEmpty()) {
//						max_heap.add(temp1);
//						draw_result = false;
//						break;
//					}
//					else {
//						temp2 = max_heap.poll();
//						if(temp1 -1 != 0) {
//							max_heap.add(temp1 -1);
//						}
//						
//						if(temp2 -1 != 0) {
//							max_heap.add(temp2 -1);							
//						}
//						
//					}
//					
//				}
//				if(draw_result) {
//					sb.append(1+" ");
//					
//				}
//				else {
//					sb.append(0+" ");
//				}
//				
//			}
			
			

			
			
			
			
		}
		System.out.println(sb.toString());
	}
	public static void back_tracking(int cnt, int match_num) {

		if(back_tracking_res) {
			return;
		}
		if (cnt == 15) {
			//매치 결과 가능함.
			back_tracking_res = true;
			return;
		}
		
		int team1_idx = match_team[match_num][0];  //1번팀 index
		int team2_idx = match_team[match_num][1];  //2번팀 index
		
		

		if (win_arr[team1_idx] - 1 < 0 || lose_arr[team2_idx] -1 < 0) {
			//즉, 1팀이 이기고 2팀이 졌다고 쳐서 개수 깠는데 음수가 나온다? 그럼 잘못된거니까 즉시 prune.
//			return; // return 해버리면 뒤에 패승,무무를 아에 안해버리니까 그냥 넘겨야됨.
		}
		else {
			//그게 아니라면 계속해서 진행.
			win_arr[team1_idx] -= 1;
			lose_arr[team2_idx] -= 1;
			back_tracking(cnt+1, match_num+1);
			win_arr[team1_idx] += 1;
			lose_arr[team2_idx] += 1;
		}
		
		//패승
		if (lose_arr[team1_idx] -1 < 0 || win_arr[team2_idx] -1 < 0) {
//			return;
		}
		else {
			lose_arr[team1_idx] -= 1;
			win_arr[team2_idx] -= 1;
			back_tracking(cnt+1, match_num+1);
			lose_arr[team1_idx] += 1;
			win_arr[team2_idx] += 1;
		}
		
		//무무
		if (draw_arr[team1_idx] -1 < 0 || draw_arr[team2_idx] -1 <0) {
//			return;
		}
		else {
			draw_arr[team1_idx] -= 1;
			draw_arr[team2_idx] -= 1;
			back_tracking(cnt+1, match_num+1);
			draw_arr[team1_idx] += 1;
			draw_arr[team2_idx] += 1;
		}
	}

}
