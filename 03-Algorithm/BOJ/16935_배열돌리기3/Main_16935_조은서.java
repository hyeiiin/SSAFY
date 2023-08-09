package algo_0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_조은서 {
	
	static int n, m, r;
	static int[][] map;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine()); // n, m, r 입력 받기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[n][m]; // 배열 입력 받기

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine()); // 수행할 연산 입력 받기
		while(st.countTokens()!=0) {
			operate(Integer.parseInt(st.nextToken()));
		}

		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}
	
	static void operate(int num) {
		switch(num) {
		case 1:
			first();
			break;
		case 2:
			second();
			break;
		case 3:
			third();
			break;
		case 4:
			fourth();
			break;
		case 5:
			fifth();
			break;
		case 6:
			sixth();
			break;
		}
	}
	
	static void first() {
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<m;j++) {
				int tmp = map[i][j];
				map[i][j] = map[n-i-1][j];
				map[n-i-1][j] = tmp;
			}
		}
	}
	
	static void second() {
		for(int j=0; j<m/2; j++) {
			for(int i=0; i<n; i++) {
				int tmp = map[i][j];
				map[i][j] = map[i][m-j-1];
				map[i][m-j-1] = tmp;
			}
		}
	}
	
	static void third() {

		int[][] tmpMap = new int[m][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmpMap[j][n-i-1] = map[i][j];
			}
		}
		
		int temp = n;
		n = m;
		m = temp;
		
		map = tmpMap;
	}
	
	static void fourth() {
		
		int[][] tmpMap = new int[m][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmpMap[m-j-1][i] = map[i][j];
			}
		}
		
		int temp = n;
		n = m;
		m = temp;
		
		map = tmpMap;
		
	}
	
	static void fifth() {
		
		int[][] tmpMap = new int[n][m];
		
		int dx[] = {0, n/2, 0, -(n/2)};
		int dy[] = {m/2, 0, -(m/2), 0};
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<m/2; j++) {
				int nx = i + dx[0];
				int ny = j + dy[0];
				tmpMap[nx][ny] = map[i][j];
			}
		}
		for(int i=0; i<n/2; i++) {
			for(int j=m/2; j<m; j++) {
				int nx = i + dx[1];
				int ny = j + dy[1];
				tmpMap[nx][ny] = map[i][j];
			}
		}
		for(int i=n/2; i<n; i++) {
			for(int j=m/2; j<m; j++) {
				int nx = i + dx[2];
				int ny = j + dy[2];
				tmpMap[nx][ny] = map[i][j];
			}
		}
		for(int i=n/2; i<n; i++) {
			for(int j=0; j<m/2; j++) {
				int nx = i + dx[3];
				int ny = j + dy[3];
				tmpMap[nx][ny] = map[i][j];
			}
		}
		
		map = tmpMap;
	}
	
	static void sixth() {
		int[][] tmpMap = new int[n][m];
		
		int dx[] = {n/2, 0, -(n/2), 0};
		int dy[] = {0, m/2, 0, -(m/2)};
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<m/2; j++) {
				int nx = i + dx[0];
				int ny = j + dy[0];
				tmpMap[nx][ny] = map[i][j];
			}
		}

		for(int i=n/2; i<n; i++) {
			for(int j=0; j<m/2; j++) {
				int nx = i + dx[1];
				int ny = j + dy[1];
				tmpMap[nx][ny] = map[i][j];
			}
		}
		
		for(int i=n/2; i<n; i++) {
			for(int j=m/2; j<m; j++) {
				int nx = i + dx[2];
				int ny = j + dy[2];
				tmpMap[nx][ny] = map[i][j];
			}
		}
		
		for(int i=0; i<n/2; i++) {
			for(int j=m/2; j<m; j++) {	
				int nx = i + dx[3];
				int ny = j + dy[3];
				tmpMap[nx][ny] = map[i][j];
			}
		}
		map = tmpMap;
	}
}
