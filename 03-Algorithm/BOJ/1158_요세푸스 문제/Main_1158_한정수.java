import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i=1; i<=N ; i++) {
			queue.add(i);
		}
		System.out.print("<");
		//1개 남을때까지. 
		int count = 0;
		while(queue.size() != 1) {
			if (count == K-1) {
				count = 0;
				System.out.print(queue.poll()+", ");
			}
			
			//K-1번 돌리고 삭제 반복
			int temp = queue.poll();
			queue.add(temp);
			count += 1;
		}
		System.out.print(queue.poll()+">\n");

	}

}

// 1 2 3 4 5 6 7
// 2 3 4 5 6 7 1
// 3 4 5 6 7 1 2  '3 삭제' 
// >> 4 5 6 7 1 2
// 5 6 7 1 2 4
// 6 7 1 2 4 5  '6삭제'
// >> 7 1 2 4 5
// 1 2 4 5 7
// 2 4 5 7 1 '2삭제'
// >> 4 5 7 1
// 5 7 1 4
// 7 1 4 5  '7삭제'
// >> 1 4 5
// 4 5 1
// 5 1 4  ' 5삭제'
// >> 1 4
// 4 1
// 1 4  ' 1삭제'
// >> 4
// 하나 남으면 마지막 출력