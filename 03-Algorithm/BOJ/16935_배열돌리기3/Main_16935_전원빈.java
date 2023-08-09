import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int r;
	static int run;
	static int box[][];

	static void updown() {
		int temp[][] = new int[n][m];
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				temp[n-1-i][j] = box[i][j];
			}
		}
		box = temp;
	}
	
	static void leftright() {
		int temp[][] = new int[n][m];
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				temp[i][m-1-j] = box[i][j];
			}
		}
		box = temp;
	}
	
	static void right90() {
		int temp[][] = new int[m][n];
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				temp[j][n-1-i] = box[i][j];
			}
		}
		box = temp;
	}
	
	static void left90() {
		int temp[][] = new int[m][n];
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				temp[m-1-j][i] = box[i][j];
			}
		}
		box = temp;
	}
	
	static void d4() {
		int temp[][] = new int[n][m];
		for(int i = 0 ; i < 4; i++) {
			for(int j = 0; j < n/2; j++) {
				for(int k = 0; k < m/2; k++) {
					temp[j][k] = box[n/2+j][k];
					temp[j][m/2+k] = box[j][k];
					temp[n/2+j][m/2+k] = box[j][m/2+k];
					temp[n/2+j][k] = box[n/2+j][m/2+k];
				}
			}
		}
		box = temp;
	}
	
	static void d44() {
		int temp[][] = new int[n][m];
		for(int i = 0 ; i < 4; i++) {
			for(int j = 0; j < n/2; j++) {
				for(int k = 0; k < m/2; k++) {
					temp[j][k] = box[j][m/2+k];
					temp[j][m/2+k] = box[n/2+j][m/2+k];
					temp[n/2+j][m/2+k] = box[n/2+j][k];
					temp[n/2+j][k] = box[j][k];
				}
			}
		}
		box = temp;
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		box = new int[n][m];
		for(int i  = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < r; i++) {
			run = Integer.parseInt(st.nextToken());
			if(run == 1)updown();
			else if(run == 2)leftright();
			else if(run == 3) {
				right90();
				int temp = 0;
				temp = n;
				n = m;
				m= temp;
			}else if(run == 4) {
				left90();
				int temp = 0;
				temp = n;
				n = m;
				m= temp;
			}else if(run == 5)d4();
			else if(run == 6)d44();
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(box[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
