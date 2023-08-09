import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11286_한정수 {
	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> minHeap_m = new PriorityQueue<>();
		PriorityQueue<Integer> minHeap_p = new PriorityQueue<>();
		
//		System.out.println(minHeap_m.peek());
		//비어있을때 peek 하면 null return
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		for (int i=0; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());
			if (temp == 0) {
				if(minHeap_p.isEmpty() && minHeap_m.isEmpty()) {
					//둘 다 비어있으면
					System.out.println(0);
				}
				else {
					if(minHeap_p.isEmpty()) {
						System.out.println(-1*minHeap_m.poll());
					}
					else if (minHeap_m.isEmpty()) {
						System.out.println(minHeap_p.poll());
					}
					else {
						//둘다 내용이 있으면
						int temp_p = minHeap_p.peek();
						int temp_m = minHeap_m.peek();
						
						if (temp_p < temp_m) {
							System.out.println(minHeap_p.poll());
						}
						else if (temp_p >= temp_m) {
							System.out.println(-1*minHeap_m.poll());
						}
					}
					
				}
			}
			else {
				if(temp > 0) {
					minHeap_p.add(temp);
				}else {
					//절댓값 생각해서 그냥 - 곱해서 넣기
					minHeap_m.add(-temp);
				}
			}
			
			
		}

	}

}
