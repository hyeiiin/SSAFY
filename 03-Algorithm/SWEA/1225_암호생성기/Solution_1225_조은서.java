import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1225_조은서 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Queue<Integer> queue = new LinkedList<>();
		
		for(int tc =1; tc<=10; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<8; i++) {
				queue.offer(Integer.parseInt(st.nextToken())); // queue에 입력 받은 8자리 숫자 삽입
			}
			
			boolean isPossible=true;
			while(isPossible) {
				for(int i=1; i<=5; i++) {
					int num = queue.poll();
					if(num - i <= 0) {
						queue.offer(0);
						isPossible = false;
						break;
					}
					queue.offer(num-i);
				}
			}

			StringBuilder sb = new StringBuilder();
			for(int i=0;i<8;i++) {
				sb.append(queue.poll()).append(" ");
			}
			
			System.out.println("#" + tc + " " + sb);
			
			queue.clear();
		}
	}
}