import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[][] li = new int[1025][1025];
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i<= n; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 1; j<= n; j++) {
				li[i][j] = Integer.parseInt(st.nextToken())+li[i][j-1]+li[i-1][j]-li[i-1][j-1];
			}
		}
		
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int ans = li[c][d] - li[a-1][d] - li[c][b-1] + li[a-1][b-1];
			
			
			StringBuilder sb = new StringBuilder();
			sb.append(ans);
			System.out.println(sb.toString());
		}
	}