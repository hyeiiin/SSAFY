import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4012_한정수 {
	
	static boolean[] selected;
	static int diff_min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		
		for (int test_case=1 ; test_case<=T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];
			
			
			for (int i=0 ; i<N ; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());

				}
			}
			//데이터 입력 완료.
			//일단 N개중 N/2 개 선택해야되니깐 선택했는지 아닌지 여부 확인할 배열 필요.

			selected = new boolean[N];
			
			comb(0,0,N,arr);
			System.out.println(diff_min);
			diff_min = Integer.MAX_VALUE;
			
			
			
		}
		
	}
	public static void comb(int cnt, int idx, int N, int[][] arr) {
		if (cnt == N/2) {
			int taste_A = 0;
			int taste_B = 0;
			for (int i=0; i<N ; i++) {
				if(selected[i]) {
					for(int j=i+1; j<N ; j++) {
						
						if(selected[j]) {
							taste_A += arr[i][j] + arr[j][i];
						}
					}
				}
				else {
					for(int j=i+1; j<N ; j++) {
						if(!selected[j]) {
							taste_B += arr[i][j] + arr[j][i];
						}
					}
				}
			}
			//최솟값 갱신.
			int temp = Math.abs(taste_A - taste_B);
			if(diff_min > temp) {
				diff_min = temp;
			}
			return;
		}
		
		for(int i=idx; i<N ; i++) {
			selected[i] = true;
			comb(cnt+1, i+1, N, arr);
			selected[i] = false;
		}
	}

}
