import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_6808_김도현 {
	
	public static ArrayList<Integer> list_gyu;
	public static ArrayList<Integer> list_in;
	public static boolean[] visited;
	public static int result1,result2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			list_gyu = new ArrayList<>();
			list_in  = new ArrayList<>();
			visited = new boolean[9];
			result1 = 0;
			result2 = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				list_gyu.add(Integer.parseInt(st.nextToken()));
			}
			for (int i = 0; i < 18; i++) {
				if(!list_gyu.contains(i+1)){
					list_in.add(i+1);
				}
			}
			dfs(0,0,0);
			System.out.println("#"+tc+" "+result1+" "+result2);
		}
	}
	public static void dfs(int sum1, int sum2, int depth) {
		if(depth == 9) {
			if(sum1>sum2) {
				result1++;
			}else {
				result2++;
			}
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				if(list_gyu.get(i)> list_in.get(depth)) {
					dfs(sum1+list_gyu.get(i)+list_in.get(depth), sum2, depth+1);
				}
				else if(list_gyu.get(i) < list_in.get(depth)) {
					dfs(sum1, sum2+list_gyu.get(i)+list_in.get(depth), depth+1);
				}
				visited[i] = false;
			}
		}
	}
}
