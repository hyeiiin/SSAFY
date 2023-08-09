package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1861_문예은 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[] delta_x = {-1,1,0,0};
		int[] delta_y = {0,0,-1,1};
		int T = Integer.parseInt(br.readLine()); // 테케 개수
		for (int t = 1; t <= T; t++) {
			sb.append("#"+t+" ");
			N = Integer.parseInt(br.readLine()); // 배열 크기
			int[][] result = new int[N*N][2]; // [0]시작점 별로 최대 이동가능 수(정렬용), [1]방 번호
			int[][] arr = new int[N][N]; // 배열 생성
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			int num = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// bfs 시작
					ArrayDeque<int[]> queue = new ArrayDeque<>();
					queue.offer(new int[] {i,j}); // 현재 시작점 큐에 넣기
					int count = 1; // 최대 이동가능 방 개수
					while(!queue.isEmpty()) { // 사방탐색 결과가 있으면
						int[] check = queue.poll();
						for (int k = 0; k < 4; k++) { // 사방탐색
							int dx = delta_x[k] + check[0];
							int dy = delta_y[k] + check[1];
							if(isIn(dx, dy) && arr[dx][dy] == arr[check[0]][check[1]]+1) { // 범위 내에 있고, 현재 좌표보다 1 큰값이면
									queue.offer(new int[] {dx, dy});
									count++;
									break;
							}
						}
					}
					result[num][0] = count;
					result[num][1] = arr[i][j];
					num++;
				}
			}
			Arrays.sort(result, (o1,o2)->{
				if(o1[0]==o2[0]) { // 최대 이동횟수가 같으면
					return Integer.compare(o1[1], o2[1]); // 방번호 순으로 정렬
				} else { // 이동횟수 다르면
					return Integer.compare(o2[0], o1[0]); // 이동횟수 큰순으로 정렬
				}
			});
			sb.append(result[0][1]+" "+result[0][0]+"\n");
		}
		System.out.print(sb.toString());
	}
	private static boolean isIn(int dy, int dx) {
		if(dy < 0 || dy >= N || dx < 0 || dx >= N) return false;
		return true;
	}
}
