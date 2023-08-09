import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Pair> pq=new PriorityQueue<>();
		StringBuilder sb=new StringBuilder();
		int n=Integer.parseInt(br.readLine());
		int x,popN;
		while(n-->0) {
			x=Integer.parseInt(br.readLine());
			if(x==0) {
				if(!pq.isEmpty()) {
					popN=pq.poll().second;
					sb.append(popN).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else {
				pq.add(new Pair(Math.abs(x),x));
			}
		}
		System.out.println(sb.toString());
	}
	public static class Pair implements Comparable<Pair>{
		int first;
		int second;
		public Pair(int first,int second) {
			this.first=first;
			this.second=second;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.first<o.first)
				return -1;
			else if(this.first==o.first&&this.second<o.second)
				return -1;
			return 1;
		}
	}
}
