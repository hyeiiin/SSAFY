import java.io.*;
import java.util.*;
public class Main {
	static int[][] dir= {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
	static boolean[][][] visited=new boolean[61][61][61];
	static int n;
	static int[] scv=new int[3];
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			scv[i]=Integer.parseInt(st.nextToken());
		}
		System.out.println(bfs());
	}
	public static int bfs() {
		Queue<int[]>q=new ArrayDeque<>();
		q.add(new int[] {scv[0],scv[1],scv[2],0});
		int[] curr;
		int n0,n1,n2,c0,c1,c2,count;
		while(!q.isEmpty()) {
			curr=q.poll();
			c0=curr[0];
			c1=curr[1];
			c2=curr[2];
			count=curr[3];
			for(int i=0;i<6;i++) {
				n0=0>c0-dir[i][0]?0:c0-dir[i][0];
				n1=0>c1-dir[i][1]?0:c1-dir[i][1];
				n2=0>c2-dir[i][2]?0:c2-dir[i][2];
				if(n0==0&&n1==0&&n2==0) {//모두 0이면 종료
					return count+1;
				}
				if(!visited[n0][n1][n2]) {//처음 만든 숫자 쌍이라면
					q.add(new int[] {n0,n1,n2,count+1});
					visited[n0][n1][n2]=true;
				}
			}
		}
		return 0;
	}
}

