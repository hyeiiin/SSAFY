import java.io.*;
import java.util.*;

public class Main_11286_김현영 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine()); // 연산의 개수
		
		//우선순위 큐 사용
		PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				if(Math.abs(o1) == Math.abs(o2)) {
					return (int)(o1-o2);	//오름차순
				} 
				return (int) (Math.abs(o1) - Math.abs(o2)); //오름차순
			}
		});
		
		  
		
		for (int i = 0; i < n; i++) {
			long x = Long.parseLong(br.readLine());
			//0을 입력받을 경우 절댓값이 가장 작은 값 출력 후 삭제
			if (x == 0) {
				//큐가 비어있다면 0 출력
				if ( pq.size() == 0)
					sb.append(0).append("\n");
				//큐의 첫번째 값 출력
				else {
					sb.append(pq.poll()).append("\n");
				}
			} 
			//0이외의 숫자를 입력받을 경우 큐에 추가
			else {
				pq.offer(x);
			}
		}

		System.out.println(sb.toString());
	}

}
