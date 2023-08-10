package swea;
import java.util.*;
import java.io.*;
public class Solution_5215_김태훈 {
	static int[][] nl;		//맛점수, 칼로리 저장 배열
	static int n, l;
	static int r;			//4C2에서 2부분
	static int max_sum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {	//테스트 케이스 입력받기
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());	//재료의수  4C2에서 4부분
			l = Integer.parseInt(st.nextToken());	//제한 칼로리
			nl = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				nl[i][0] = Integer.parseInt(st.nextToken());	//맛점수
				nl[i][1] = Integer.parseInt(st.nextToken());	//칼로리
			}
			max_sum = 0;			//제한 조건 내에서 최댓값 저장
			
			for (int i = 0; i < n; i++) {
				permuation(0, 0, 0, 0, i+1);			//재귀로 조합 생성
			}
			sb.append("#"+(t+1)+" "+max_sum+"\n");
		}
		System.out.println(sb);
	}
	private static void permuation(int count, int flag, int sum_cal, int sum_mat, int R) {
		if(sum_cal>l) {
			return;			
		}
		if(count == R) {
			if (sum_mat > max_sum) {
				max_sum = sum_mat;
			}			
			return;
		}
			
		for (int i = 0; i < n; i++) {
			//중복체크
			if((flag & 1<<i) !=0) break;
			//수 선택
			permuation(count+1, flag | 1<<i, sum_cal+nl[i][1], sum_mat+nl[i][0], R);
		}
	}

}
