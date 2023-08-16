import java.io.*;
import java.util.*;

public class Main {

	static int r, c, ans, real;
	static char map[][];
	static int visited[][];
	static int dy[] = {-1, 0, 1};
	
	static void gasgasgas(int y, int x) {
		if(x == c-1) {
				ans = 1;
				return;
		}
		
		for(int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			int nx = x + 1;
			if(ny < 0 || ny >= r)continue;
			if(map[ny][nx] == 'x')continue;
			if(visited[ny][nx] == 1)continue;
			if(ans == 1)continue;
			visited[ny][nx] = 1;
			gasgasgas(ny, nx);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visited = new int[r][c];
		for(int i = 0; i < r; i++) {
			String tempstring = bf.readLine();
			for(int j = 0; j < c; j++) {
				map[i][j] = tempstring.charAt(j);
			}
		}
		for(int i = 0; i < r; i++) {
			ans = 0;
			gasgasgas(i, 0);
			if(ans == 1)real++;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(real);
		System.out.println(sb.toString());
	}

}