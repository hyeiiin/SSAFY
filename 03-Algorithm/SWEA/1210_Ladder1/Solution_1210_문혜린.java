package algorithm.swea;

import java.util.*;
import java.io.*;

public class Solution_1210_문혜린 {
	static int[][] ladder;
	static boolean[][] visit;
	static int result = 0;
	
	public static void algorithm(int x, int y) {
		if(x==0) { //끝까지 갔을 경우
			result = y; //해당 열 반환
			return;
		}
		//좌,우,상
		int dx[] = {0, 0, -1};
		int dy[] = {-1, 1, 0};
		
		int nx, ny;
		
		for(int i=0; i<3; i++) { //좌, 우, 상으로 이동
			//방향에 맞게 이동
			nx = x + dx[i];
			ny = y + dy[i];
			//움직이려는 곳이 범위 안이고 사다리가 맞고(ladder[nx][ny]==1) 아직 방문하지 않았을 경우
			if(nx>=0 && nx<100 && ny>=0 && ny<100 && ladder[nx][ny]==1 && !visit[nx][ny]) {
				visit[nx][ny] = true; //방문처리
				algorithm(nx, ny); //해당 위치로 이동하기
				break; //사다리 끝까지 갔을 경우 for문 더 돌지 않기 위한 break문
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ladder = new int[100][100]; //사다리
		visit = new boolean[100][100]; //방문 여부
		
		for(int t=1; t<=10; t++) {
			int TC = Integer.parseInt(br.readLine());
			
			//사다리 입력받기
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//방문 여부 초기화
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					visit[i][j] = false;
				}
			}
			
			int x = 0, y = 0; //2가 있는 인덱스 (시작 인덱스)
			
			for(int i=0; i<100; i++) {
				if(ladder[99][i] == 2) { //X가 있는 도착 지점 인덱스 저장
					x = 99;
					y = i;
				}
			}
			visit[x][y] = true; //시작 인덱스 방문처리
			algorithm(x, y);
			System.out.println("#" + TC + " " + result);
			
		}
		
	}

}
