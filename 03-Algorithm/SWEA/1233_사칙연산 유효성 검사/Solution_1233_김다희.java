import java.util.*;
import java.io.*;

class Solution{
	public static void main(String args[]) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=10;
		for(int test_case = 1; test_case <= T; test_case++){
			sb.append("#").append(test_case).append(" ");
			int n=Integer.parseInt(br.readLine());
			int i=0,status=0;
			String[] arr=new String[n+1];
			for(i=1;i<=n;i++) {
				st=new StringTokenizer(br.readLine());
				st.nextToken();
				arr[i]=st.nextToken();
				if(status==0&&!st.hasMoreTokens()&&(arr[i].equals("-")||arr[i].equals("+")||arr[i].equals("/")||arr[i].equals("*")))
				{
					sb.append(0);
					status=1;
				}
			}
			if(status==0) {
				sb.append(1);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
