package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11286_전상혁 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
			// 음수, 0 - 자리 유지, 양수 - 자리 바꿈
			//첫번째 객체가 왼쪽에 위치 --> 올림
			//첫번째 객체가 오른쪽에 위치 --> 내림
			@Override
			public int compare(Integer o1,Integer o2) {
				//절대값이 더 큰 숫자가 우선순위 높고 작은 숫자가 낮음
				if (Math.abs(o1) > Math.abs(o2)) {
					return 1;
				}else if (Math.abs(o1) < Math.abs(o2)) {
					return -1;
				}else if (Math.abs(o1) == Math.abs(o2)) {
					//o1-o2가 음수면 자리 유지, o1-o2가 양수면 자리바꿈
					return o1 - o2;
				}
				return 0;
			}
		});

		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			//x가 0이 아닐 경우
			if (x!=0) {
				//x 삽입
				heap.add(x);
//				System.out.println(que);
				
			}else {
				//x가 0인 경우 
				if (!heap.isEmpty()) {
					//x가 0인데 배열이 비어있지 않으면 가장 작은 값 출력 및 삭제
					System.out.println(heap.poll());
				}else
					//배열이 비어있으면 0 출력
					System.out.println(0);
			}
			
		}
		
	}
}
