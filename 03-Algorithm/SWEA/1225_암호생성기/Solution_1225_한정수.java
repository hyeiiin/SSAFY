import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1225_한정수 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[8];
		for(int test_case=1 ; test_case<=10 ; test_case++) {
			int t_num = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//queue에 입력
			Queue<Integer> queue= new LinkedList<>();
			for (int i=0; i<8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			// queue에 8개 숫자 입력 완료 =======
			
			int count = 1;
			int temp = 0;
			while(true) {
				temp = queue.poll();
				if(temp - count <= 0) {
					queue.add(0);
					break;
				}
				queue.add(temp-count);
				
				//사이클. 빼는 값이 5면 다시 1로
				if(count != 5) {
					count += 1;
				}
				else if(count == 5) {
					count = 1;
				}
				
			}
			System.out.printf("#%d ",test_case);
			while(!queue.isEmpty()) {
				System.out.print(queue.poll()+" ");
			}
			System.out.println();
			
			
			
			
		}
		
		
	}

}
