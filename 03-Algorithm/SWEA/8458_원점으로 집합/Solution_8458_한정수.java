package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8458_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case=1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr_x = new int[N];
			int[] arr_y = new int[N];
			
			//어차피 x,y의 움직이는 거리(절댓값)가 중요하기 때문에, 입력값이 -인경우 +로 바꿔버림.
			for (int i=0; i<N ; i++) {
				st = new StringTokenizer(br.readLine());
				arr_x[i] = Integer.parseInt(st.nextToken());
				if (arr_x[i] < 0) {
					arr_x[i] *= -1;
				}
				arr_y[i] = Integer.parseInt(st.nextToken());
				if (arr_y[i] < 0) {
					arr_y[i] *= -1;
				}
			}
			
			
			
			
			boolean even = false;
			boolean odd = false;
			boolean cannot_solve = false;
			// 어떤 점이든, 점 한 개만 주어지면 무조건 (0, 0)으로 보낼 수 있다.
			// 그런데 모든 점이 동시에 움직이면서 동시에 (0, 0)으로 가야한다.
			// 위 조건을 만족하려면 애초에, 모든 점들의 (x+y) 값이 전부 홀수이거나, 전부 짝수여야함.
			// 어떤 점은 먼저 도착하고 어떤 점은 나중에 도착할텐데, 먼저 도착한 점은 그냥 제자리에서 와리가리 치기만 하면 됨.
			// 그래서 제일 멀리 있는 점이 도착할때까지 기다리면 되는데, 문제는 왜 전부 (x+y)가 홀수이거나, 짝수이어야 하냐면....
			// ex) (0,0) (0,1) 의 경우 두 점을 동시에 i 만큼 움직여서 전부 원점에 넘기는 방법은 절대 없음.
			//    >> i == 4일때, (0, 0)(짝수) 는 와리가리쳐서 (0,0)으로 올 수 있지만,
			//                  (0, 1)(홀수) 는 와리가리쳐도 (0,1)로 가야됨.
			//    >> i == 5일때, (0, 0)(짝수) 는 4번 와리가리 치고 1번 남아서 (0,1)로 가고
			//                  (0, 1)(홀수) 는 4번 와리가리 치고 1번 남아서 (0,0)으로 가고
			//  .... 무한반복 ......
			// 따라서 N개 점 싹 다 돌려서 짝수/홀수 둘 다 존재한다면 그냥 -1 출력하고 넘겨버림.
			for (int i=0; i<N ; i++) {
				if ((arr_x[i] + arr_y[i]) % 2 == 0) {
					even = true;
				}
				else {
					odd = true;
				}
				
				if (even && odd) {
					cannot_solve = true;
					break;
				}
			}
			
			
			boolean[] finished = new boolean[N];
			int finished_cnt = 0;
			int cnt = 0;
			int result = 0;
			
			// cnt(== 문제에서 i) 세면서, (x, y) 에서, x -= cnt 한다.
			// cnt > x일경우, 일단 x가 0이 되게 빼주고, y에서 (cnt-x)만큼 빼준다.
			//   >> temp = cnt-x, x=0, y -= temp
			// 이후 x==0 이고, y>0 인 상태일텐데, temp(== cnt - x 인데, x=0이니까 사실상 cnt와 동일) < y인 경우 그냥 y -= temp 해주고,
			// 언젠가 temp >= y인 상태가 온다면
			// temp -= y, y = 0
			// 이때, (x,y) == (0,0)인 상태이지만 움직여야할 횟수가 아직 temp 만큼 남아있는 상태이므로, 제자리 와리가리하면 됨.
			// if temp % 2 == 0 이면 y =0. temp가 짝수면 와리가리해서 다시 0으로 올 수 있으니까 끝.
			// if temp % 2 == 1 이면 y =1, temp가 홀수면 와리가리해도 한 칸 남으니까 y=1찍고 다음 cnt+=1 턴에 끝내버릴 수 있게.
			
			
			// x+y  가 홀수/짝수 다 존재하는 경우
			if (cannot_solve) {
				int num = -1;
				System.out.println("#"+test_case+" "+num);
			}
			// x+y 가 전부 홀수이거나, 전부 짝수라면
			else {
				while(true) {
					for(int i=0; i<N ; i++) {
						if (finished[i]) {
							continue;
						}
						int x = arr_x[i];
						int y = arr_y[i];
						if(cnt >= x) {
							int temp = cnt - x;
							x = 0;
							if (temp >= y) {
								temp -= y;
								y = 0;
								if (temp % 2 == 0) {
									y = 0;
								}else {
									y = 1;
								}
							}else {
								// cnt < y
								y -= temp;
							}
							
						}else {
							// cnt < x
							x -= cnt;
						}
						
						// N개 점중 i번째 점은 0으로 왔으니까 더 계산할 필요 없음.
						if (x == 0 && y == 0) {
							finished[i] = true;
							finished_cnt += 1;
						}
						
						// x, y를 cnt만큼 움직인 결과로 갱신해주고 다시 while문 반복.
						arr_x[i] = x;
						arr_y[i] = y;
					}
					
					
					if(finished_cnt == N) {
						// N개 점이 모두 0,0 으로 왔으면 종료.
						result = cnt;
						break;
					}
					cnt += 1;
				} //while 끝
				
				System.out.println("#"+test_case+" "+result);
				
			}
			
		}
	}

}
