import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11659_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 전체 자연수 몇 개
		int M = Integer.parseInt(st.nextToken()); //몇 개 구할지
		
		st = new StringTokenizer(br.readLine());
		int[] input = new int[N];
		
//		for (int i=0 ; i < N ; i++) {
//			input[i] = Integer.parseInt(st.nextToken());
//		}
		
		//이제 각각 부분합 구하기
//		for (int i=0 ; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int start = Integer.parseInt(st.nextToken()) -1; // 주어진 구간을 배열 idx로 바꾸려면 -1.
//			int end = Integer.parseInt(st.nextToken()) -1;
//			
//			//처음,끝이 같으면 그 index 하나만 출력
//			if (start == end) {
//				System.out.println(input[start]); 
//			}
//			else {
//				int sum = 0;
//				for(int j=0 ; j < end-start +1 ; j++) {
//					sum += input[start+j];
//				}
//				System.out.println(sum);
//			}
//		}
		// 시간초과  for(M){ for(N) {} } 이므로 O(MN)
		
		
		// 이건 for(N){} for(M){} 이라 O(M+N)
		int[] input_sum = new int[N];
		for (int i=0 ; i < N; i++) {
			if(i==0) {
				input_sum[0] = Integer.parseInt(st.nextToken());
			}
			else {
				input_sum[i] = input_sum[i-1] + Integer.parseInt(st.nextToken());	
			}
			
		}
		for (int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) -1; // 주어진 구간을 배열 idx로 바꾸려면 -1.
			int end = Integer.parseInt(st.nextToken()) -1;
			if(start == end) {
				if(end == 0) {
					System.out.println(input_sum[end]);
				}
				else {
					System.out.println(input_sum[end] - input_sum[end-1]);
					
				}
			}
			else if(start == 0){
				System.out.println(input_sum[end]);
			}
			else {
				System.out.println(input_sum[end]-input_sum[start-1]);
			}

		}
	}

}
