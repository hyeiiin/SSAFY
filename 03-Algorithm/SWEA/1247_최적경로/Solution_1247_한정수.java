import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_한정수 {
	static int N;
	static int com_x;
	static int com_y; //회사 좌표
	static int home_x; 
	static int home_y; //집 좌표
	
	static int[] custom_x;
	static int[] custom_y;
	static int[] perm_result; //순열로 선택된 고객집의 index 순서쌍.
	
	
	static int min_sum = Integer.MAX_VALUE;
	
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case=1 ; test_case <=T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			custom_x = new int[N];
			custom_y = new int[N];
			selected = new boolean[N];
			st = new StringTokenizer(br.readLine());
			com_x = Integer.parseInt(st.nextToken());
			com_y = Integer.parseInt(st.nextToken());
			home_x = Integer.parseInt(st.nextToken());
			home_y = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				custom_x[i] = Integer.parseInt(st.nextToken());
				custom_y[i] = Integer.parseInt(st.nextToken());
			}
			//배열 입력 완료.
			
			
			//이제 각각의 고객을 순열로 골라서 거리를 구함.
			//이때, 순열로 고를때마다 그때그때 거리합을 넘겨주는데, 이 넘겨준 값이 이전 (전체 거리합)의 최솟값을 넘을 경우 즉시 종료.(백트래킹)
			
			//순열 실행
			perm_result = new int[N];
			perm(0, 0);
			
			//출력
			System.out.printf("#%d %d",test_case, min_sum);
			System.out.println();
			
			//전역변수 초기화
			min_sum = Integer.MAX_VALUE;
			
		}
		
		
		//
	}
	public static void perm(int cnt, int sum) {
		if (cnt == N) {
			//마지막에 집까지의 거리를 구해서 더해주고.
			int temp = sum;
			temp += Math.abs(custom_x[perm_result[cnt-1]] - home_x);
			temp += Math.abs(custom_y[perm_result[cnt-1]] - home_y);
			//최솟값 갱신.
			if(temp < min_sum) {
				min_sum = temp;
			}
			return;
		}
		//순열
		for (int i=0; i<N ; i++) {
			if(selected[i]) {
				continue;
			}
			selected[i] = true;
			perm_result[cnt] = i;
			
			//거리 계산
			int distance = 0;
			if(cnt == 0) {
				//최초엔 회사랑 거리.
				distance += Math.abs(com_x - custom_x[perm_result[cnt]]);
				distance += Math.abs(com_y - custom_y[perm_result[cnt]]);
			}
			else {
				//그다음부턴 이전 지점과의 거리
				distance += Math.abs(custom_x[perm_result[cnt-1]] - custom_x[perm_result[cnt]]);
				distance += Math.abs(custom_y[perm_result[cnt-1]] - custom_y[perm_result[cnt]]);
			}
			
			//이미 중간 과정에서 최솟값보다 크면 즉시 종료.			
			if(sum+distance > min_sum) {
				selected[i] = false;
				return;
			}
			else {
				//그게 아니라면 순열 계속해서 진행.
				perm(cnt+1, sum+distance);
				selected[i] = false;
			}
			
			
		}
	}

}
