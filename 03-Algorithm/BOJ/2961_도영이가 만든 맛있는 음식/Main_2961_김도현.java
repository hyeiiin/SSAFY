package baekjun;

import java.util.Scanner;

public class Main_2961_김도현 {
	
	static int N;
	static int min=Integer.MAX_VALUE;
	static int[][] arr;
	static boolean[] visited;
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		arr=new int[N][2];
		visited=new boolean[N];
		
		for(int i=0;i<N;i++) {
			arr[i][0]=sc.nextInt(); // 쓴 맛
			arr[i][1]=sc.nextInt(); // 단 맛
		}
		
		dfs(0);
		System.out.println(min);
	}
	
	static void dfs(int depth) {
		
		if(depth==arr.length) {
			int sour=1; 
			int bitter=0; 
			int count=0;
			for(int i=0;i<visited.length;i++) {
				if(visited[i]) {
					count++;
					sour*=arr[i][0]; // 쓴맛 
					bitter+=arr[i][1]; // 단맛
				}
			}
			
			// 아무 음식도 선택되지 않은 경우는 return
			if(count==0)
				return;
			
			// 최솟값 갱신
			min=Math.min(min, Math.abs(bitter-sour));
			return;
		}
		
		// 현재 음식은 선택한 경우 탐색
		visited[depth]=true;
		dfs(depth+1);
		
		// 현재 음식은 선택하지 않은 경우 탐색
		visited[depth]=false;
		dfs(depth+1);
	}
}