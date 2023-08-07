import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int cnt=1;
		StringBuilder sb=new StringBuilder();
		Deque<Integer>q=new ArrayDeque<>();
		for(int i=1;i<=n;i++) {
			q.add(i);
		}
		sb.append("<");
		while(q.size()>1) {
			if(cnt!=k) {
				q.add(q.peek());
				cnt++;
			}else {
				cnt=1;
				sb.append(q.peek()).append(", ");
			}
			q.poll();
		}
		sb.append(q.peek()).append(">");
		System.out.println(sb.toString());
	}

}
