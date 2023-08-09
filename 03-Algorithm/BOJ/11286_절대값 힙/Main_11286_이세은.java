package ssafyPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286_이세은 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); //연산 개수
		PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				// TODO Auto-generated method stub
				if(Math.abs(o1)==Math.abs(o2))
					return (int)(o1-o2);
				return (int)(Math.abs(o1)-Math.abs(o2)); //절댓값이 작은 값 순으로 정렬
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		//연산 n번 실행
		for (int i = 0; i < n; i++) {
			long x = Long.parseLong(br.readLine());
			if(x != 0) { //배열에 추가
				pq.add(x);
			}
			else { //배열에서 꺼내기(제거)
				if(pq.isEmpty())
					sb.append("0\n");
				else
					sb.append(pq.poll()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
