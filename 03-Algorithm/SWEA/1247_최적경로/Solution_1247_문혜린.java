package algorithm.swea;

import java.util.*;
import java.io.*;

//[S/W 문제해결 응용] 3일차 - 최적 경로
public class Solution_1247_문혜린 {
	static int N; //고객의 수
	static int[] numbers; //조합 저장
	static boolean[] isSelected; //선택 여부
	static int comX, comY; //회사 좌표 (출발)
	static int houseX, houseY; //집 좌표 (도착)
	static int cus[][]; //고객 좌표
	static int min = Integer.MAX_VALUE; //최적 경로
	
	public static void permutation(int cnt) { //순열
		if(cnt == N) { //순열 완성
			int tmp = 0;
			//처음 고객 좌표와 회사 사이 거리
			tmp += Math.abs(comX-cus[numbers[0]][0])+Math.abs(comY-cus[numbers[0]][1]);
			//고객 좌표 간 이동 거리
			for (int i = 1; i < N; i++) {
				tmp += Math.abs(cus[numbers[i-1]][0]-cus[numbers[i]][0]) + Math.abs(cus[numbers[i-1]][1]-cus[numbers[i]][1]);
			}
			//마지막 고객 좌표와 집 사이 거리
			tmp += Math.abs(houseX-cus[numbers[N-1]][0])+Math.abs(houseY-cus[numbers[N-1]][1]);
			
			//이동거리 가장 짧은 경로 구하기
			if(min > tmp) {
				min = tmp;
			}
		}
		else {
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					continue;
				}
				isSelected[i] = true;
				numbers[cnt] = i;
				permutation(cnt+1);
				isSelected[i] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); //고객의 수
			numbers = new int[N];
			isSelected = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			//회사 좌표
			comX = Integer.parseInt(st.nextToken());
			comY = Integer.parseInt(st.nextToken());
			//집 좌표
			houseX = Integer.parseInt(st.nextToken());
			houseY = Integer.parseInt(st.nextToken());
			//고객 좌표
			cus = new int[N][2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++) {
					cus[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//최단 경로 구하기
			min = Integer.MAX_VALUE;
			permutation(0);
			sb.append("#"+t+" "+min+"\n");
		}
		System.out.println(sb);
	}

}
