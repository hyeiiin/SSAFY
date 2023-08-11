package algo_0811;

import java.io.*;
import java.util.*;

public class Main_15686_조은서 {

	static int N, M;
	static int[][] city;
	static ArrayList<int[]> cityList;
	static ArrayList<int[]> chickenList;
	static ArrayList<int[]> choice;
	static boolean[] isSelected;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); // 남길 치킨 집 수
		
		// 도시 정보 입력
		city = new int[N][N];
		cityList = new ArrayList<int[]>();
		chickenList = new ArrayList<int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				city[i][j] = num;
				if(num == 1) cityList.add(new int[] {i, j}); // 도시 좌표 저장
				if(num == 2) chickenList.add(new int[] {i, j}); // 치킨집 좌표 저장
				
			}
		}
		// 도시 정보 입력 끝
		
		// 치킨 집 M개 고르기..(조합) + M개의 치킨집에 대한 도시의 치킨 거리 구하고 최솟값 찾기
		isSelected = new boolean[chickenList.size()];
		choice= new ArrayList<int[]>();
		result = Integer.MAX_VALUE;
		
		combi(0,0);
		System.out.println(result);
		
		
	}
	
	private static void combi(int cnt, int init) {
		if(cnt == M) {
			// M개의 치킨집에 대한 도시의 치킨 거리 구하기
			int sum = 0;
			for(int[] city: cityList) {
				int min = Integer.MAX_VALUE;
				for(int[] chicken: choice) {
					int dist = Math.abs(city[0]-chicken[0]) + Math.abs(city[1]-chicken[1]);
					min = Math.min(dist, min);
				}
				sum += min;
			}
			result = Math.min(sum, result);
			return;
		}
		for(int i=init; i<chickenList.size(); i++) {
			if(isSelected[i] == true) continue;
			choice.add(chickenList.get(i));
			isSelected[i] = true;
			combi(cnt+1, i+1);
			choice.remove(choice.size()-1);
			isSelected[i] = false;
		}
	}

}
