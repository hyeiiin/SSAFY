import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_김나연 {
	
	static int r,c, visited[][], res, pipe, flag;
	static char a[][];
	static int dy[]= {-1,0,1}, dx[]= {1,1,1};
	
	public static void go(int y, int x) {
		visited[y][x]=1;

		if(x==c-1) {
			res++;
			flag=1;
		}else {
			for(int i=0;i<3;i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];

				if(ny<0||nx<0||ny>=r||nx>=c) continue;
				if(a[ny][nx]=='x')continue;
				if(visited[ny][nx]!=0)continue;

				if(flag==1) return;
				go(ny, nx);
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		a=new char[r][c];
		
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			String str=st.nextToken();
			for (int j = 0; j < c; j++) {
				a[i][j]=(char) (str.charAt(j));
			}
		}

		visited=new int[r][c];
		for (int i = 0; i < r; i++) {
			flag=0;
			go(i,0);
		}
		
		sb.append(res);
		System.out.println(res);
	}

}
