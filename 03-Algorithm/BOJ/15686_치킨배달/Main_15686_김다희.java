import java.io.*;
import java.util.*;

public class Main {
	static int n,m,storeSize,houseSize;
	static boolean[] visitedStore;
	static List<Point> store=new ArrayList<>();
	static List<Point> house=new ArrayList<>();
	static int[] dp;
	static final int INF=1000000000;
	static int minVal=INF;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int tmp;
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				tmp=Integer.parseInt(st.nextToken());
				if(tmp==2) {
					store.add(new Point(i,j));
				}else if(tmp==1) {
					house.add(new Point(i,j));
				}
			}
		}
		storeSize=store.size();
		houseSize=house.size();
		visitedStore=new boolean[storeSize];
		for(int i=1;i<=m;i++) {
			recur(0,i,0,new ArrayDeque<>());
		}
		System.out.println(minVal);
	}
	public static void recur(int start, int target,int depth,Queue<Point> stores) {
		Queue<Point>q;
		if(depth==target) {
			minVal=Math.min(getDistance(stores),minVal);
			return;
		}
		for(int i=start;i<storeSize;i++) {
			if(!visitedStore[i]) {
				visitedStore[i]=true;
				q=new ArrayDeque<>(stores);
				q.add(store.get(i));
				recur(i+1,target,depth+1,q);
				visitedStore[i]=false;
			}
		}
	}
	public static int getDistance(Queue<Point> stores) {
		int total=0;
		Point curr;
		dp=new int[houseSize];
		for(int i=0;i<houseSize;i++) {
			dp[i]=INF;
		}
		while(!stores.isEmpty()) {
			curr=stores.poll();
			for(int i=0;i<houseSize;i++) {
				dp[i]=Math.min(dp[i],Math.abs(curr.r-house.get(i).r)+Math.abs(curr.c-house.get(i).c));
			}
		}
		for(int i:dp) {
			total+=i;
		}
		return total;
	}
	public static class Point implements Cloneable{
		int r;
		int c;
		public Point(int r,int c) {
			this.r=r;
			this.c=c;
		}
	}
}
