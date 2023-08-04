import java.util.*;
import java.io.*;

class Solution{
	public static void main(String args[]) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		for(int test_case = 1; test_case <= 10; test_case++){
			int n=Integer.parseInt(br.readLine());
			String str=br.readLine();
			Deque<Character>stack=new LinkedList<>();
			int count=0;
			while(count<n) {
				if(!stack.isEmpty()
						&&Math.abs(stack.peekLast()-str.charAt(count))<5) {
					stack.pollLast();
					count++;
				}else {
					stack.addLast(str.charAt(count++));
				}
			}
			System.out.println("#"+test_case+" "+(stack.isEmpty()?1:0));
		}
	}
}
