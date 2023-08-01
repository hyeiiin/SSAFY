package swea;
import java.util.*;
import java.io.*;
public class Solution_1210_김태훈 {
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[] n = new int[10];
		int[][] map = new int[100][100];
		int[] arr = new int[10];
		
		for(int i=0; i<10; i++) {
			n[i] = Integer.parseInt(br.readLine());
			int start_y = 0;
			int start_x = 0;
			for (int j = 0; j < 100; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 100; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					if(map[j][k]==2) {
						start_x = j;
						start_y = k;
					}
				}
			}
			int index_x = start_x;
			int index_y = start_y;
			while(true) {
				//끝
				if(index_x==0) {
					arr[i] = index_y;
					break;
				}
				//왼쪽
				if(index_y>0 && map[index_x][index_y-1]==1) {
					index_y--;
					map[index_x][index_y+1] = 0;
				}
				//오른쪽
				else if(index_y<99 && map[index_x][index_y+1]==1) {
					index_y++;
					map[index_x][index_y-1] = 0;
				}
				//위로
				else {
					index_x--;
				}
				
			}
				
			
		}
		for (int i = 0; i < 10; i++) {
			System.out.printf("#%d %d\n",i+1, arr[i]);
		}
	}

}
