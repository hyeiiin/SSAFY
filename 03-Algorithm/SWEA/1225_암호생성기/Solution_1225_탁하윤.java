package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1225_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		Queue<Integer> que = new ArrayDeque<>();
		
		for(int tc=1; tc<=10; tc++) {
			int T = Integer.parseInt(br.readLine());	// 테이스 케이스 번호
			st = new StringTokenizer(br.readLine());
			int cnt = 0;	// 싸이클 번호
			// 큐에 값 넣기
			for(int i=0; i<8; i++) {
				que.add(Integer.parseInt(st.nextToken()));
			}
			
			// 0이 될때 까지 while문 돌기
			while(true) {	
				cnt++;
				int cur = que.poll() - cnt;	// 큐 앞에서 꺼낸 값에서 현재 싸이클 값을 뺀것을 cur 변수에 저장한다.
				if(cur<=0) {	// 만약 cur가 0보다 작거나 같으면 cur를 0으로 바꿔주고 큐 끝에 값을 넣어주고 while문을 탈출한다.
					cur = 0;
					que.offer(cur);
					break;
				} else {
					que.offer(cur);	// 큐 끝에 값 넣어주기
				}
				if(cnt == 5) {	// 싸이클 값의 최대가 된다면 다시 0으로 초기화 해주기
					cnt = 0;
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for(int i=0; i<8; i++) {
				sb.append(que.poll()+" ");	// 큐에 있는 값을 빼면서 출력한다.
			}
            sb.append("\n");
		}
        
        System.out.println(sb);

	}

}
