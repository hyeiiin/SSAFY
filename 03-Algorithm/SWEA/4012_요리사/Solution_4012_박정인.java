package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH
 * @author SSAFY
 *
 */
public class Solution_4012_박정인 {
	static int N, S[][], result;	// S: 시너지 배열, result : 결과값(A, B차이 절댓값의 최솟값)
	static boolean[] isSelectedA;	// 음식 A에 선택된 재료인지 체크하는 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
				
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			result = Integer.MAX_VALUE;	
			
			S = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			isSelectedA = new boolean[N];
			selectA(0, 0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	// 음식 A에 들어가는 재료의 조합 (A가 정해지면 B는 자동으로 정해짐 >> N/2로 나누기때문에)
	private static void selectA(int cnt, int start) {
		if (cnt == N / 2) {	// N/2이면 해당 조합의 A, B 차이가 최소인지 갱신
			result = Math.min(result, getDiff());
			return;
		}				
		
		for (int i = start; i < N; i++) {
			isSelectedA[i] = true;
			selectA(cnt + 1, i + 1);
			isSelectedA[i] = false;
		}
	}
	
	// 음식 A, B의 차이
	private static int getDiff() {
		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < N; i++) {	// 1,2,3 이  A조합인 경우 >> 1, 2 / 1, 3 / 2, 3 | 2, 1 / 3, 1 / 3, 2 합계
			for (int j = i + 1; j < N; j++) {				
				if (isSelectedA[i] && isSelectedA[j]) {	// A 합계
					sumA += S[i][j];
					sumA += S[j][i];			
				} else if(!isSelectedA[i] && !isSelectedA[j]) { // B 합계
					sumB += S[i][j];
					sumB += S[j][i];
				}
			}
		}
			 
		// A, B 차이 절대값
		return Math.abs(sumA - sumB);
	}
}
