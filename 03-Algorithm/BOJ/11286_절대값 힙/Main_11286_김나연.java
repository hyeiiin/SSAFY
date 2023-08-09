import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286_김나연 {
	
	static int n, x, a[];
	
	public static void main(String[] args) throws  IOException {
		PriorityQueue <int []> pq=new PriorityQueue<>(new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0]==o2[0]) return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
		 });
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n=Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			x=Integer.parseInt(br.readLine());;
			
			if(x==0) {
				if(pq.isEmpty()) sb.append("0\n");
				else {
					int[] info=pq.peek();
					int num = info[1];
					sb.append(num).append("\n");
					pq.poll();
				}
			}else {
				pq.offer(new int[] {Math.abs(x),x});
			}
		}
		
		System.out.println(sb);
	}
	


	
}
