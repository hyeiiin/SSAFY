package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//N과 M(2)
public class Main_15650_문혜린 {
	static int N, M;
	static int numbers[];
	static int input[];
	
	public static void algorithm(int cnt, int start) {
		if(cnt == M) { //수열 다 만들어졌을 경우
			for(int n:numbers) {
				System.out.print(n + " "); //결과 출력
			}
			System.out.println();
		}
		else {
			for (int i = start; i <= N; i++) { //중복없이 하기 위해 수열 내 오름차순 지키기
				numbers[cnt] = input[i]; //cnt 인덱스에 자연수 넣기
				algorithm(cnt+1, i+1); //자리수 밀기, 이전 수들보다 큰 수 넣기
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N+1]; //1~N까지 수
		numbers = new int[M]; //조합 개수
		
		//1~N 저장
		for (int i = 1; i <= N; i++) {
			input[i] = i;
		}
		
		algorithm(0, 1);

	}

}
