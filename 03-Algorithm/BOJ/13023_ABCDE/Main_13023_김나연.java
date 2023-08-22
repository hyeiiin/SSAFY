import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13023_김나연 {
	
	static int n,m,a,b,flag;
	static boolean visited[];
	static List<Integer>[] list;
	
	static void go(int here, int depth) {
		visited[here]=true;
		
		if(depth>=5) {
			flag=1;
			return;
		}
		
		if(flag==0) {
			for(int v:list[here]) {
				if(visited[v]==true) continue;
				go(v, depth+1);
				visited[v]=false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		visited=new boolean[n];
		list = new ArrayList[n];
		for (int i = 0; i < n; i++) {
	        list[i] = new ArrayList<>();
	    } 
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		

		for (int i = 0; i < n; i++) {
			if(flag==0) {
				visited=new boolean[n];
				go(i,1);
			}
		}
		
		System.out.println(flag);
		
	}

}
