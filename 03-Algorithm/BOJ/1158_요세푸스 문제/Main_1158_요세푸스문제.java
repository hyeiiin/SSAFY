package boj;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main_1158_요세푸스문제 {

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		Scanner s = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = s.nextInt(); //숫자의 개수
		int K = s.nextInt(); // K번째 수
		
		Queue<Integer> q = new LinkedList<>();
		
		
		for(int i=1;i<=N;i++) {
			q.offer(i); //숫자 세팅
		}
		
		//출력 
		sb.append("<");
		int num;	
		
		//N개의 숫자를 K번 dequeue할 것. N*K 만큼 반복
		for(int i=1;i<=N*K;i++) {
			
			num = q.poll();	
			// K의 배수번째인지 아닌지 확인
			if(i%K==0) {
				sb.append(num); // 숫자 출력
				if(!q.isEmpty()) sb.append(", "); //큐가 비어있는 경우는 출력하지 않음
			}
			//K의 배수가 아니기 때문에 다시 뒤로 보냄.
			else q.offer(num); 
		}
		// 출력 >
		sb.append(">");
		
		System.out.println(sb);
	}

}
