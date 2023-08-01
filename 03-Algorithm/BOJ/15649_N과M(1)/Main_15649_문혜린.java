package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_15649_문혜린 {
	public static int N, M;
	public static int numbers[];
	public static boolean isSelected[];
	
	public static void algorithm(int cnt) {
		if(cnt == M) { //길이에 맞는 수열이 만들어졌을 경우
			for (int i = 0; i < M; i++) { //길이까지
				System.out.print(numbers[i]+ " "); //수열 출력
			}
			System.out.println();
		}
		else { //아직 길이를 만족하는 수열이 안 만들어졌을 경우
			for (int i = 1; i <= N; i++) { //사용할 수 있는 자연수 내에서
				if(isSelected[i]) { //이미 사용한 수일 경우
					continue; //그냥 넘어가기
				}
				numbers[cnt] = i; //cnt 인덱스에 사용 가능한 수 삽입
				isSelected[i] = true; //중복 체크
				algorithm(cnt+1); //다음 순서로 넘어가기
				isSelected[i] = false; //다른 경우도 체크하기 위해 방문 취소
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //1~N까지 자연수 사용
		M = Integer.parseInt(st.nextToken()); //길이가 M인 수열 
		
		numbers = new int[M]; //최종 수열
		isSelected = new boolean[N+1]; //중복 여부 (1~N까지 자연수이므로 배열 크기 N+1)
		for (int i = 1; i <= N; i++) {
			isSelected[i] = false; //초기화
		}
		
		algorithm(0);
		
	}

}
