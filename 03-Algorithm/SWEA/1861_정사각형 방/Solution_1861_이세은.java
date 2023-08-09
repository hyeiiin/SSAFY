package ssafyPractice;

import java.io.*;
import java.util.*;

public class Solution_1861_이세은 {
	
	private static int n;
	private static int[][] arr;
	private static int[] moveR = {-1, 1, 0, 0};
	private static int[] moveC = {0, 0, -1, 1};
	private static int[] maxStart;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			//방 숫자 입력받기
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxStart = new int[n*n+1]; //모든 좌표에 대해서 생성
			//가장 많이 이동할 수 있는 출발점 찾기
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					dfs(i, j, 1, arr[i][j]); //출발점 포함 방 개수 구하기
				}
			}
			
			int max = 0, maxIdx=0;
			//최댓값을 가지는 방이 여러개면 수가 작은 것을 결과로
			for (int i = maxStart.length-1; i>0; i--) { //수가 작은 것을 저장하기 위해서 뒤에서부터 탐색
				if(max <= maxStart[i]) { //크거나 같으면 i작은 것으로 업데이트!
					max = maxStart[i];
					maxIdx = i;
				}
			}
			
			System.out.println("#"+test_case+" "+maxIdx+" "+max);
		}
		
	}
	
	public static void dfs(int i, int j, int cnt, int start) {
		
		if(cnt > maxStart[start]) //출발 좌표인 start에서 갈수 있는 방 수 업데이트
			maxStart[start] = cnt;
		
		for(int k=0; k<4; k++) {
			int newI = i + moveR[k];
			int newJ = j + moveC[k];
			
			if(newI>=0 && newJ>=0 && newI<n && newJ<n) {//범위 나가지 않고
				if(arr[newI][newJ] == arr[i][j]+1) { //차이가 1
					dfs(newI, newJ, cnt+1, start);
				}
			}
		}
		
		
	}

}
