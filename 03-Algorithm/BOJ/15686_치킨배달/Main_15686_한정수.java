import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15686_한정수 {
	static int N;
	static int M;
	static int[][] arr;
	static int[] chick_x_idx;
	static int[] chick_y_idx;
	static int[] house_x_idx;
	static int[] house_y_idx;
	static boolean[] chick_selected;
	static boolean[] house_selected;
	static int[] distance;
	static int c_idx;
	static int h_idx;
	static int sum_distance;
	static int temp_sum_distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		arr = new int [N][N];
		chick_x_idx = new int[13]; //M 최댓값은 13
		chick_y_idx = new int[13];
		//selected[i] 를 탐색하면서, i가 선택될경우 i번째 치킨집을 소환.
		//즉, i번째 치킨집 좌표는
		// if selected[i] == true :  (x,y) = (chick_x_idx[i], chick_y_idx[i]);
		// 만약 메모리 터지면 그때가서 생각
		house_x_idx = new int[2*N]; //집 최대 개수는 2N개
		house_y_idx = new int[2*N];
		
		c_idx = 0;
		h_idx = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N ; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 2) {
					chick_x_idx[c_idx] = i;
					chick_y_idx[c_idx] = j;
					c_idx += 1;
				}
				else if (temp == 1) {
					house_x_idx[h_idx] = i;
					house_y_idx[h_idx] = j;
					h_idx += 1;
				}
//				arr[i][j] = Integer.parseInt(st.nextToken()); // 굳이 원본 배열을 들고 다닐 필요는 없음.
			}
		}
		
		chick_selected = new boolean[c_idx];  //c_idx = 치킨집 개수
		house_selected = new boolean[h_idx];  //h_idx = 그냥집 개수
		distance = new int[h_idx];
		Arrays.fill(distance, Integer.MAX_VALUE);
		sum_distance = Integer.MAX_VALUE;
		
		//입력 끝 ==========================
		
		
		//음... 그러니까 일단 2가 들어있는 위치를 찾아야겠음  >> x,y 좌표 저장할 배열과 조합 탐색 방문체크 배열 추가.
		//할튼 selected로 체크하면서 cnt == M이 될때까지 돌리고? 
		//        >> 실제 치킨집 수가 M보다 똑같을수도, 많을수도.
		//돌릴때마다         bfs를 돌려야되나?
		//======================================
		//그러지말고 1이 들어있는 위치도 그냥 싹다 받고
		//일단 치킨집을 M개 고르고
		//     >>단, 1개고른 경우, 2개고른경우 전부 다 고려해야하니까 모든 부분집합(0 빼고) 고려해야함. 즉, 현재 데이터를 포함시켜서 comb할지, 빼고 comb할지 결정하란거.
		//     
		// 각각의 M개에 m1 m2 m3... 라하면
		// m1과 집까지의 거리를 [o o o o o o] 저장하고, m2와 집까지의 거리를 구해서 최솟값으로 싹 갱신시킴. M까지 다 돌리고
		// 마지막에 저 거리 배열을 합한 값을 구하면 끝.

		
		comb(0, 0, 0);
		System.out.println(sum_distance);
		
	}
	public static void comb(int cnt, int start, int sel_num) {
		//c_idx >= M이니 M 됬는지만 확인.
		//선택 할 수 있는 최대개수 M 도달시
		if (sel_num == M) {
			//각 집과의 거리 측정.
			//하기 전에 각 M조합 내부에서의  distance 최솟값이니깐 각 조합마다 distance 초기화.
			Arrays.fill(distance, Integer.MAX_VALUE);
			for(int i=0; i<c_idx; i++){
				//전체 c_idx개 집을 도는데.
				if(chick_selected[i] == true) {
					//체크된 애들만 한해서
					for (int j=0 ; j<h_idx ; j++) {
						//각 집 0~h_idx 까지의 거리를 다 구해주고 최솟값으로 갱신.
						int temp = Math.abs(chick_x_idx[i] - house_x_idx[j]) + Math.abs(chick_y_idx[i] - house_y_idx[j]);
						//각 집까지의 거리를 최솟값으로 갱신.
						if (temp < distance[j]) {
							distance[j] = temp;
						}
					}
				}
				else {
					continue;
				}
			}
			
			//최솟값으로 완전히 갱신된 distance의 합을 구하고
			for (int j=0; j<h_idx ; j++) {
				temp_sum_distance += distance[j];
			}

			//최솟값 갱신
			if(temp_sum_distance < sum_distance) {
				sum_distance = temp_sum_distance;
			}
			//temp값 초기화 해주고.
			temp_sum_distance = 0;
			return;
			
		}
		if (cnt == c_idx) {
			//M개를 못채웠지만, 일단 골라낸 경우. 최대 M개까지 선택한 경우라서 다 해줘야함.
			//그래도 계산은 해야됨.
			
			//그래도 0개는 빼고.
			if (sel_num == 0) {
				return;
			}
			
			//최솟값 구하고 갱신은 위랑 동일
			Arrays.fill(distance, Integer.MAX_VALUE);
			for(int i=0; i<c_idx; i++){
				if(chick_selected[i] == true) {
					for (int j=0 ; j<h_idx ; j++) {
						int temp = Math.abs(chick_x_idx[i] - house_x_idx[j]) + Math.abs(chick_y_idx[i] - house_y_idx[j]);
						//각 집까지의 거리를 최솟값으로 갱신.
						if (temp < distance[j]) {
							distance[j] = temp;
						}
					}
				}
				else {
					continue;
				}
			}
			for (int j=0; j<h_idx ; j++) {
				temp_sum_distance += distance[j];
			}
			
			if(temp_sum_distance < sum_distance) {
				sum_distance = temp_sum_distance;
			}
			temp_sum_distance = 0;
			return;
		}
		
		
		//조합====================================
		for (int i=start; i<c_idx ; i++) {
			//전체 c_idx 개 에서 M개를 골라야함.
			
			comb(cnt+1, i+1, sel_num); //지금 i는 고르지 말고 넘기고
			if(sel_num < M) { //이미 고른 개수가 M개 이상이면 더 선택하면 안됨.
				chick_selected[i] = true;
				comb(cnt+1, i+1, sel_num +1); //지금 i를 고르고 넘기고
				chick_selected[i] = false; //끝나면 다시 체크 풀어주고.
			}
			
			
		}
	}

}
