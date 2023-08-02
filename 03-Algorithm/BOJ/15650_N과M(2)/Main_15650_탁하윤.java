package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 순열 저장 배열과 방문 저장 배열에 크기 지정
		numbers = new int[M];
		
		// 순열 구현 재귀함수 호출: count, 시작점 start, 자연수 N, 순열 크기 M 전달
		perm(0, 1, N, M);
		
		
	}
	static int[] numbers;	// 순열 저장 배열
	
	// 순열 호출 함수
	public static void perm(int count, int start, int N, int M) {
		StringBuilder sb = new StringBuilder();
		
		// 현재까지 뽑은 순열 수의 개수가 만들고 싶은 순열 개수가 같다면 출력
		if(count == M) {
			for(int n : numbers) {
				sb.append(n+" ");
			}
			System.out.println(sb);
			return;
		} else {
			for(int k=start; k<=N; k++) {
				numbers[count] = k;	// start부터 입력받은 자연수 넣기
				perm(count+1, k+1, N, M);	// 중복 없는 순열 하나를 완성, 다음 순열을 뽑으러 재호출
		
			}
		}
	}

}
