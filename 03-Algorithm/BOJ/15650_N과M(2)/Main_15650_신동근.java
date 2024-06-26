package 백준;

import java.util.*;
import java.io.*;

public class Main_15650_신동근 {
	
	static int N;
	static int M;
	static int[] arr;	// 선택하고자 하는 대상 집합
	static int[] output;	// 대상 숫자를 담아올 배열
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		output = new int[N];

		// 대상 집합에 숫자 저장
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		
		backTracking(0, 0);
		System.out.print(sb);
	}
	
	// 백트래킹
	// 조합 메서드
	public static void backTracking(int depth, int idx) {
		// 해당 깊이가 선택횟수와 같아지면 결과를 출력하고 재귀 종료
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=idx; i<N; i++) {
			output[depth] = arr[i];
			// 자신 이전의 수를 중복선택하지 않도록 인덱스를 +1 하여 재귀 호출함
			backTracking(depth+1, i+1);
		}
	}

}
