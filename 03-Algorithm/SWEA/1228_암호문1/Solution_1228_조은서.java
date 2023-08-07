import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1228_조은서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1; tc<=10; tc++) {
			LinkedList<Integer> list = new LinkedList<>();
			
			int N = Integer.parseInt(br.readLine()); // 원본 암호문 길이
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			 int K = Integer.parseInt(br.readLine()); // 명령어 개수
			 st = new StringTokenizer(br.readLine());
			 for(int i=0; i<K; i++) {
				 if(st.nextToken().equals("I")) {
					 int x = Integer.parseInt(st.nextToken());
					 int y = Integer.parseInt(st.nextToken());
					 
					 for(int j =0; j<y; j++) {
						 list.add(x, Integer.parseInt(st.nextToken()));
						 x++;
					 }
					 
				 }
					 
			 }
			 System.out.print("#"+tc+" ");
			 for(int i=0; i<10; i++) {
				 System.out.print(list.get(i)+" ");
			 }
			 System.out.println();
			
		}

	}

}
