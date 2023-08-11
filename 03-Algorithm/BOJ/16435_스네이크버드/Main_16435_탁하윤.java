package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16435_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 과일 개수
		int L = Integer.parseInt(st.nextToken());	// 스네이크버드 길이
		int[] fruit = new int[N];	// 과일 높이 들
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {	// 과일 높이 초기화
			fruit[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(fruit);	// 과일 높이들 오름차순 정렬
		
		for(int i=0; i<N; i++) {
			if(L > fruit[i]) {	// 스네이크버드 길이가 과일 높이보다 길다면, 먹이를 먹고 +1 길어짐
				L++;
				continue;
			}else if(L == fruit[i]) {	// 스네이크버드 길이가 과일 높이와 같다면 먹이를 먹고 +1 길어짐
				L++;
				continue;
			}
			if(L < fruit[i]) {	// 스네이크버드 길이가 과일 높이 -1 한것보다 작다면 while문 탈출
				break;
			}
		}
		System.out.println(L);

	}

}
