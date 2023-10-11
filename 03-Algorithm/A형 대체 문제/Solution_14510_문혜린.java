package swea;

import java.util.*;
import java.io.*;

public class Solution_14510_문혜린 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테케 개수
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); //나무 개수
			
			int[] tree = new int[N]; //나무 높이
			
			int result = 0; //최소 날짜 수
			int max = Integer.MIN_VALUE; //제일 높은 나무 높이
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, tree[i]);
			}
			
			int even = 0; //2높이 만큼 남은 횟수
			int odd = 0; //1높이 만큼 남은 횟수
			for (int i = 0; i < N; i++) {
				if(max != tree[i]) {
					//남은 나무 높이 (홀짝) 구하기
					even += (max-tree[i])/2;
					odd += (max-tree[i])%2;
				}
			}
			
			//남은 1, 2 개수가 같을 경우
			if(even == odd) {
				result = even+odd;
			}
			//1이 더 많을 경우
			else if(even<odd) {
				//홀짝 쌍 빼주기
				result += even*2;
				odd -= even;
				
				//쌍 뺀 후 바로 다음날 홀수번째 날이므로 홀수 하나 제거
				result += 1;
				odd -= 1;
				//아직 홀수 남아있을 경우 이틀씩 기다려야함
				if(odd>0) {
					result += odd*2; 
				}
			}
			//2가 더 많을 경우
			else {
				//홀짝 쌍 빼주기
				result += odd*2;
				even -= odd;
				
				//3개 짝지어서 0 남은 경우
				//ex) 2 2 2
				if(even%3 == 0) {
					result += (even/3)*4;
				}
				//3개 짝지어서 1 남은 경우
				//ex) 2 2 2 2
				else if(even%3 == 1) {
					result += (even/3)*4;
					result += 2;
				}
				//3개 짝지어서 2 남은 경우
				else if(even%3 == 2) {
					result += (even/3)*4;
					result += 3;
				}
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}
