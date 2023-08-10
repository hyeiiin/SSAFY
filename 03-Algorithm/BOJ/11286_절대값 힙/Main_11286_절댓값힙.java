
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286_절댓값힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1)==Math.abs(o2)) { //절대값이 같으면, 음수를 앞으로 
					return o1-o2;
				}
				return Math.abs(o1)-Math.abs(o2); //절대값 기준 오름차순
			}
		});
		
		int N = Integer.parseInt(br.readLine()); //연산의 개수 N
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num==0) {
				if(pq.isEmpty()) { //비어있는 경우 0이 출력 되도록 추가
					sb.append("0").append("\n");
				}else { //가장 앞에 있는 값 꺼내기(comparator에 의해서 이미 정렬되어 있음)
				sb.append(pq.poll()).append("\n");
				}
				pq.offer(num);
			}
		}
		System.out.println(sb);
	}

}
