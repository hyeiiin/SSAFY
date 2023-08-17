import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1247_김도현 {

	public static int N,minDis,homeX,homeY; // 고객의 수 , 최단이동거리, 집의 좌표
	public static ArrayList<int[]> map; // 회사, 집, N명의 고객들의 집의 좌표
	public static boolean visitedHome[]; // 방문한 집확인용도
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			minDis = Integer.MAX_VALUE;
			visitedHome = new boolean[N];
			map = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int companyX = Integer.parseInt(st.nextToken());
			int companyY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				int cusX = Integer.parseInt(st.nextToken());
				int cusY = Integer.parseInt(st.nextToken());
				map.add(new int[] {cusX, cusY});
			}
			
			dfs(companyX,companyY,0,0);
			
			System.out.println("#"+test_case+" "+minDis);
			
		}
		

	}
	public static void dfs(int x,int y, int customerCnt, int dis) {
		
		if(customerCnt == N) {
				dis += (Math.abs(x-homeX)+Math.abs(y-homeY));
				minDis = Math.min(dis, minDis);
				return;
		}
		
		
		for (int i = 0; i < map.size(); i++) {
			if(!visitedHome[i]) {
				visitedHome[i] = true;
				dfs(map.get(i)[0],map.get(i)[1],customerCnt+1,dis+(Math.abs(x-map.get(i)[0])+Math.abs(y-map.get(i)[1])));
				visitedHome[i] = false;
			}
		}
	}

}
