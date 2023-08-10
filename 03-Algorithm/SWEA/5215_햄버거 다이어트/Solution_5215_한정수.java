import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_5215_한정수 {
	static int max_cal;
	static int max_score; //출력은 max_score
	static int[] score;
	static int[] cal;
	static int N;
	static int L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int T = Integer.parseInt(st.nextToken());
		for (int test_case=1 ; test_case<=T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[] score = new int[N];
			int[] cal = new int[N];
			
			for(int i=0 ; i<N ; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			//데이터 입력 끝.
			
			//칼로리 배열로 조합 써서 하나씩 만들고,  (정확히는 index = new int[N]을 조합 돌리고, 나온 조합의 index를 cal[]에 넣어서)
			//                                  아니면 비트플래그  쓰던가? >> 조합은 근데 어차피 안겹치게 고를거라 굳이?
			// >> 각 조합에 대해
			//  >> 칼로리 합이 L보다 작은가.
			//     >>크다면 continue
			//     >>작다면 max_score 갱신.
			comb(0, N, L, 0, 0, score, cal, 0);
			System.out.printf("#%d %d",test_case,max_score);
			System.out.println();
			//전역변수 테케마다 초기화
			max_cal = 0;
			max_score = 0;
			
			
		}
		
	}
	public static void comb(int cnt, int N, int L , int sum_cal,int sum_score, int[] score, int[] cal, int idx_i) {
		//nCr 이 아니고, 차라리 매번 돌리면서 calorie 합을 바로바로 계산/
		
		if(sum_cal > L) {
			//칼로리 합이 넘었으면, 마지막에 넣은거 1개 빼고 그 안에서 합을 구하고 갱신.
			// cal[0]+cal[1]+cal[2]를 읽고 여길 들어왔으면, 현재 idx_i = 3 따라서 cal[(idx_i-1)]을 빼면 마지막에 넣은거 1개를 뺀 결과가 나옴.
			sum_score -= score[idx_i -1];
			if(max_score < sum_score) {
				max_score = sum_score;

			}
			
			return;
		}
		if (cnt == N) {
			//다 읽었으나, 칼로리 합이 제한을 안넘었으면, 칼로리 합 구해서 갱신.
			if(max_score < sum_score) {
				max_score = sum_score;

			}
			return;
		}
		
		for (int i=idx_i; i<N; i++) {
			comb(cnt+1, N, L, sum_cal+cal[i], sum_score+score[i], score, cal, i+1);
		}
		//for문 다 돌렸을때 한번 다시 갱신.
		if(max_score < sum_score) {
			max_score = sum_score;

		}
		return;
		
	}

}
