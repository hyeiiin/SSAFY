import java.util.*;
import java.io.*;

class Solution{
	private static int n,l,maxTaste;
	private static Pair[] ingredients;
	private static boolean[] visited;
	public static void main(String args[]) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++){
			sb.append("#").append(test_case).append(" ");
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			l=Integer.parseInt(st.nextToken());
			maxTaste=0;
			ingredients=new Pair[n];
			visited=new boolean[n];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				ingredients[i]=new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			for(int i=1;i<=n;i++) {
				find(0,i,0,0);
			}
			sb.append(maxTaste).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void find(int start,int target, int currCal,int currTaste) {
		//종료 조건
		if(currCal>l) {
			return;
		}
		if(start==target) {
			maxTaste=currTaste>maxTaste?currTaste:maxTaste;
			return;
		}
		for(int i=start;i<n;i++) {
			if(!visited[i]) {
				visited[i]=false;
				find(i+1,target,currCal+ingredients[i].cal,currTaste+ingredients[i].taste);
				visited[i]=false;
			}
		}
	}
	public static class Pair{
		int taste;
		int cal;
		public Pair(int taste, int cal) {
			this.taste=taste;
			this.cal=cal;
		}
	}
}
