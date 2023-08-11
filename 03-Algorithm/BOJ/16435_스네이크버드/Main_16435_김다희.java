import java.io.*;
import java.util.*;
public class  Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int l=Integer.parseInt(st.nextToken());
		PriorityQueue<Integer>pq=new PriorityQueue<>();
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		while(!pq.isEmpty()&&pq.peek()<=l) {
			l++;
			pq.poll();
		}
		System.out.println(l);
	}
}
