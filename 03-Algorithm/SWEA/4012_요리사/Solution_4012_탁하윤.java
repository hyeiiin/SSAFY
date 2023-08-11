package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_탁하윤 {
	static int N, synergy[][], minDif;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());	// 총 테스트 케이스 개수
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());	// 식재료 수 N
			synergy = new int[N][N];	// 시너지 값들 넣을 배열
			visited = new boolean[N];	// 방문 처리 배열
			minDif = Integer.MAX_VALUE;	// 두 음식 간의 맛의 차이 최솟값
			
			// 시너지 값들 초기화
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			combi(0, 0); // 조합 뽑기
			System.out.printf("#%d %d\n", tc, minDif);
		}
	}
	
	private static void combi(int cnt, int start) {
		if(cnt==N/2) {
			// N/2까지 조합 뽑았으면 dif호출
			dif();
			return;
		}
		for(int i=start; i<N; i++) {
			visited[start] = true;
			combi(cnt+1, i+1);	// 중복 X
			visited[start] = false; 
		}
	}
	
	private static void dif() {
		int A = 0, B = 0;	// A음식, B음식 시너지 총합
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				if(visited[i] && visited[j]) {
					A += synergy[i][j];	// 뽑은 것 더하기
				}
				if(!visited[i] && !visited[j]) {
					B += synergy[i][j];	// 안 뽑은 것 더하기
				}
			}
		}
		int AB = Math.abs(A-B);	// AB 차이값 절댓값으로 구하기
		minDif = Math.min(minDif, AB);	// 두 음식(AB) 차이가 minDif보다 작다면 바꾸기
	}

}
