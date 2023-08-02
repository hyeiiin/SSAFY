import java.util.*;
import java.io.*;

public class Main_11659_김현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<Integer> numbers = new ArrayList<>();
		
		//n개의 수 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			//인덱스 0 제외 누적합 구하기
			if(i != 0)
				numbers.add(num + numbers.get(i-1) );
			else
				numbers.add(num);
		}

		//m입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) -1 ;
			int end = Integer.parseInt(st.nextToken()) -1 ;
			int sum =0;
			
			//시작인덱스가 0일 때 
			if(start == 0)
				sum = numbers.get(end);
			else {
				sum = numbers.get(end) - numbers.get(start-1);
			}
			 
			System.out.println(sum);
		}
		
		
	}

}
