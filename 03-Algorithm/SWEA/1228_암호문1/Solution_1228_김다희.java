import java.util.*;
import java.io.*;

class Solution{
	public static void main(String args[]) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st,stmp;
		StringBuilder sb=new StringBuilder();
		List<Integer> list;
		int T=10;
		for(int test_case = 1; test_case <= T; test_case++){
			sb.append("#").append(test_case).append(" ");
			int n=Integer.parseInt(br.readLine());
			st=new StringTokenizer(br.readLine());
			list=new LinkedList<>();
			for(int i=0;i<n;i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			int m=Integer.parseInt(br.readLine());
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<m;i++) {
				st.nextToken();
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				for(int j=0;j<y;j++) {
					list.add(x++,Integer.parseInt(st.nextToken()));
				}
			}
			for(int i=0;i<10;i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
