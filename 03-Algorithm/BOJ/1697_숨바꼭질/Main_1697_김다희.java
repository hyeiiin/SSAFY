import java.io.*;

import java.util.*;

public class Main {

	static final int INF=Integer.MAX_VALUE;	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int size=n>m?n:m;
		int[] arr=new int[size+2];
		for(int i=0;i<=size+1;i++) {
			arr[i]=INF;
		}
		arr[n]=0;
		bfs(n,size+1,arr);
		System.out.println(arr[m]);
	}

	public static void bfs(int start,int size,int[] arr) {
		Queue<Integer> q=new LinkedList<>();
		q.add(start);
		int curr;
		while(!q.isEmpty()) {
			curr=q.poll();
            if(curr*2<size+1) {
				if(arr[curr*2]>arr[curr]+1) {
					q.add(curr*2);
					arr[curr*2]=arr[curr]+1;
				}
			}
			if(curr+1<size+1) {
				if(arr[curr+1]>arr[curr]+1) {
					q.add(curr+1);
					arr[curr+1]=arr[curr]+1;
				}
			}
            if(curr-1>=0) {
				if(arr[curr-1]>arr[curr]+1) {
					q.add(curr-1);
					arr[curr-1]=arr[curr]+1;
				}
			}
		}
	}
}
