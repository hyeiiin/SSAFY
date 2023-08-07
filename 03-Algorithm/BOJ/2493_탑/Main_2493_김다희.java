import java.io.*;
import java.util.*;

public class Main {
	public static class Pair{
		int val;
		int index;
		public Pair(int val, int index) {
			this.val=val;
			this.index=index;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		String[] str=br.readLine().split(" ");
		StringBuilder sb=new StringBuilder();
		Deque<Pair>dq=new ArrayDeque<>();
		int[] arr=new int[n+1];
		int tmp;
		for(int i=n;i>=1;i--) {
			tmp=Integer.parseInt(str[i-1]);
			if(dq.isEmpty()) {
				dq.add(new Pair(tmp,i));
			}
			while(!dq.isEmpty()&&dq.peekLast().val<tmp) {
				arr[dq.pollLast().index]=i;
			}
			dq.add(new Pair(tmp,i));
		}
		while(!dq.isEmpty()) {
			arr[dq.pollLast().index]=0;
		}
		for(int i=1;i<=n;i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

}
