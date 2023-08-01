package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 순열 저장 배열과 방문 저장 배열에 크기 지정
		numbers = new int[M];
		isVisited = new boolean[N];
		
		// 순열 구현 재귀함수 호출: count, 자연수 N, 순열 크기 M 전달
		perm(0, N, M);
		
		
	}
	static int[] numbers;	// 순열 저장 배열
	static boolean[] isVisited;	// 방문 저장 배열
	
	// 순열 호출 함수
	public static void perm(int count, int N, int M) {
		StringBuilder sb = new StringBuilder();
		
		// 현재까지 뽑은 순열 수의 개수가 만들고 싶은 순열 개수가 같다면 출력
		if(count == M) {
			for(int n : numbers) {
				sb.append(n+" ");
			}
			System.out.println(sb);
			return;
		} else {
			for(int k=1; k<=N; k++) {
				if(isVisited[k-1]==true) {
					// 방문한 수라면 다시 for문 돌기
					continue;
				} else {
					numbers[count] = k;	// 방문하지 않은 수라면 현재 뽑은 순열 인덱스에 자연수 대입
					isVisited[k-1]=true;	// 뽑은 수라면 true
					
					perm(count+1, N, M);	// 순열 하나를 완성했으니 다음 순열을 뽑으러 ㄱㄱ
					
					isVisited[k-1]=false;	// 재귀호출이 끝나고 방문했던 수를 다시 false로
				}
			}
		}
	}

}
