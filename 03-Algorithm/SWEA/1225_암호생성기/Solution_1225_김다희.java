import java.util.*;
import java.io.*;

class Solution{
	public static void main(String args[]) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		for(int test_case = 1; test_case <= 10; test_case++){
			int n=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			Deque<Integer>dq=new LinkedList<>();
			for(int i=0;i<8;i++) {
				dq.add(Integer.parseInt(st.nextToken()));
			}
			boolean isZero=true;
			int left;
			while(isZero) {
				for(int j=1;j<6;j++) {
					left=dq.poll()-j;
					left=left<0?0:left;
					dq.addLast(left);
					if(left==0) {
						isZero=false;
						break;
					}
				}
			}
			System.out.print("#"+test_case+" ");
			for(int i=0;i<8;i++) {
				System.out.print(dq.pollFirst()+" ");
			}
			System.out.println();
		}
	}
}
