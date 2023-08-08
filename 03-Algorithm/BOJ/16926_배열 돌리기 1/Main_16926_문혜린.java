package algorithm;

import java.util.*;
import java.io.*;

//배열 돌리기 1
public class Main_16926_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //행 수
		int M = Integer.parseInt(st.nextToken()); //열 수
		int R = Integer.parseInt(st.nextToken()); //회전의 수
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int start = Math.min(N, M)/2; //회전 시작 좌표 (0,0),(1,1),(2,2)...
		for (int i = 0; i < R; i++) { //회전 수만큼 반복
			for(int j=0; j<start; j++) {
				int temp = arr[j][j]; //왼쪽으로 한칸씩 회전하기 위해 시작 좌표 따로 저장(값 손실 방지)
				//상
				for (int top = j; top < M-1-j; top++) { //값 왼쪽으로 당기기
					arr[j][top] = arr[j][top+1];
				}
				//우
				for (int right = j; right < N-1-j; right++) { //값 위쪽으로 당기기
					arr[right][M-1-j] = arr[right+1][M-1-j];
				}
				//하
				for (int bottom = M-1-j; bottom > j; bottom--) { //값 오른쪽으로 당기기
					arr[N-1-j][bottom] = arr[N-1-j][bottom-1];
				}
				//좌
				for (int left = N-1-j; left > j; left--) { //값 아래쪽으로 당기기
					arr[left][j] = arr[left-1][j];
				}
				arr[j+1][j] = temp; //빈자리에 아까 저장한 시작 좌표 저장
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
