package algorithm.swea;

import java.util.*;
import java.io.*;

//햄버거 다이어트
public class Solution_5215_문혜린 {
	static int N; //재료의 수
	static int L; //제한 칼로리
	static int[][] ingredient; //재료에 대한 맛 점수, 칼로리
	static int maxScore; //조합 중에서 가장 점수 높은 햄버거
	static boolean isSeleceted[]; //부분집합에 포함,비포함 여부
	
	public static void algorithm(int cnt) { //부분 집합
		if(cnt == N) { //부분 집합 완성
			int tmpScore = 0; //뽑은 조합의 점수 합
			int tmpL = 0; //뽑은 조합의 칼로리 합
			for (int i = 0; i < N; i++) {
				if(isSeleceted[i]) { //부분 집합에 포함되면
					tmpScore += ingredient[i][0]; //점수 더하기
					tmpL += ingredient[i][1]; //칼로리 더하기
				}
			}
			//제한 칼로리를 초과하지 않고 최대 점수 갱신 가능하면 갱신하기
			if(tmpL <= L && tmpScore > maxScore) {
				maxScore = tmpScore;
			}
		}
		else { //아직 부분 집합 완성 못했을 경우
			isSeleceted[cnt] = true;
			algorithm(cnt+1);
			isSeleceted[cnt] = false;
			algorithm(cnt+1);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //Test Case 갯수
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //재료의 수
			L = Integer.parseInt(st.nextToken()); //제한 칼로리
			
			//초기화
			ingredient = new int[N][2];
			isSeleceted = new boolean[N];
			maxScore = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken()); //점수
				ingredient[i][1] = Integer.parseInt(st.nextToken()); //칼로리
			}
			algorithm(0);
			sb.append("#"+t+" "+maxScore+"\n");
		}
		System.out.println(sb);
	}

}
