package algo_0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_조은서 {

	static int n;
	static int[][] ingredients; // 재료를 입력 받아 저장
	static int[] subSets; // 부분집합의 원소 저장
	static boolean[] isSelected; // 방문 여부
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		
		ingredients = new int[n][2];
		isSelected = new boolean[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken()); // 신맛
			ingredients[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛
		}
		
		min = Integer.MAX_VALUE;
		genSubset(0);
		System.out.println(min);
	}
	
	private static void genSubset(int cnt) {
		if(n == cnt) {
			
			int tCnt= 0;
			int sour = 1;
			int bitter = 0;
			for(int i=0; i<n; i++) {
				if(isSelected[i]) {
					tCnt++;
					sour *= ingredients[i][0];
					bitter += ingredients[i][1];
				}
			}
			
			if(tCnt==0) return;

			if(min > Math.abs(sour-bitter)) {
				min = Math.abs(sour-bitter);
			}
			
			return;
		}
		
		isSelected[cnt] = true;
		genSubset(cnt+1);
		isSelected[cnt] = false;
		genSubset(cnt+1);
	}

}
