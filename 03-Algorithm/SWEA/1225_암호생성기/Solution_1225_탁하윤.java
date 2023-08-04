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
			int T = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			for(int i=0; i<8; i++) {
				que.add(Integer.parseInt(st.nextToken()));
			}
			
			while(true) {
				cnt++;
				int cur = que.poll() - cnt;
				if(cur<=0) {
					cur = 0;
					que.offer(cur);
					break;
				} else {
					que.offer(cur);
				}
				if(cnt == 5) {
					cnt = 0;
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for(int i=0; i<8; i++) {
				sb.append(que.poll()+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		

	}

}
