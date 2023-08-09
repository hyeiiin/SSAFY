package swea;
import java.util.*;
import java.io.*;
public class Solution_1861_김태훈 {
	static int[][] map;		//맵
	static int[][] arr;		//정답 방 번호
	static int[] max;		//정답 인덱스의 연속 숫자 횟수 저장
	static int n;
	static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		//=======입력받기===============
		int T = Integer.parseInt(br.readLine());
		arr = new int[T][2];
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			max = new int[n*n+1];
			for (int x = 0; x < n; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y = 0; y < n; y++) {
					map[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			//=========여기까지 입력받기=========
			//=========여기부터 연산===========
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bfs(i, j, 1, map[i][j]);
				}
			}
			int max_index=0;
			int max_value=0;
			for (int i = 0; i < max.length; i++) {
				if(max_value<max[i]) {
					max_value = max[i];
					max_index = i;
				}
			}
			//======출력 저장====
			arr[t][0] = max_index;
			arr[t][1] = max_value;
		}
		//==========출력===========
		for (int t = 0; t < T; t++) {
			System.out.printf("#%d %d %d\n",t+1, arr[t][0], arr[t][1]);
		}
	}

	private static void bfs(int x, int y, int count, int start) {
		
		//4방향 탐색
		for(int i=0; i<4; i++) {
			
			//bfs 시작인덱스, 카운트 추가
			int nx = x+dx[i];
			int ny = y+dy[i];
			//벽 아니고 차이가 1일때
			if(nx>=0 && nx<n && ny>=0 && ny<n && map[nx][ny]-map[x][y] == 1) {
				bfs(x+dx[i], y+dy[i], count+1, start);
			}
		}
		//start에서 갈 수 있는 최대 방 수 최댓값이랑 비교하기
		if(max[start]<count) {
			max[start] = count;
		}
	}

}
