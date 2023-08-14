package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1074_김민석 {
	public static int N, r, c, answer, curX, curY, num;
	//첫번째칸, 두번째칸, 세번째칸, 네번째칸
	public static int[] dx = {0, 0, 1, 1};
	public static int[] dy = {0, 1, 0, 1};
	public static void DFS(int x, int y, int size, int end) {
		//길이가 2라면
		if(size==2) {
			//해당 정사각형 첫번째 구하기
			int tmp = end-3; 
			for (int i = 0; i < 4; i++) {
				//좌표가 맞으면 리턴
				if(x+dx[i]==r && y+dy[i]==c) {
					answer = tmp;
					return;
				}
				tmp++;
			}
			return;
		} else {
			//반으로 줄이기
			int half = size/2;
			N--;
			//첫번쨰 구역
			if(x+half>r && y+half>c) {
				DFS(x, y, half, end-(int)(Math.pow(4, N))*3); 
			//두번째 구역
			} else if(x+half>r && y+half<=c) {
				DFS(x, y+half, half, end-(int)(Math.pow(4, N))*2); 
			//세번째 구역
			} else if(x+half<=r && y+half>c) {
				DFS(x+half, y, half, end-(int)(Math.pow(4, N))); 
			//네번쨰 구역
			} else if(x+ half<=r && y+half<=c) {
				DFS(x+half, y+half, half, end);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		DFS(0, 0, (int)Math.pow(2, N), (int)Math.pow(2, N)*(int)Math.pow(2, N)-1);
		System.out.println(answer);			
	}
}