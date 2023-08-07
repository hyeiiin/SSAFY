import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1228_한정수 {
	public static void main(String[] args) throws IOException {
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		LinkedList<Integer> linkedList = new LinkedList<>();
		
		for (int test_case=1 ; test_case<=T ; test_case++) {
			//원본 개수
			st = new StringTokenizer(br.readLine());
			int origin_num = Integer.parseInt(st.nextToken());
			
			//원본 linkedlist에 등록
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<origin_num; i++) {
				linkedList.add(Integer.parseInt(st.nextToken()));
			}
			
			//삽입 개수
			st = new StringTokenizer(br.readLine());
			int insert_num = Integer.parseInt(st.nextToken());
			
			//삽입할 내용
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<insert_num; i++) {
				st.nextToken(); //input I 떼고
				int index = Integer.parseInt(st.nextToken()); //몇 번째 index에
				int num = Integer.parseInt(st.nextToken()); //몇 개를
				for (int j=0; j<num; j++) {
					linkedList.add(index++, Integer.parseInt(st.nextToken()));
					// 넣으면서 index++ 해줘서 순서대로 들어가게
				}
				
			}
			System.out.printf("#%d ", test_case);
			for (int i=0; i<10; i++) {
				System.out.print(linkedList.poll()+" ");
			}
			System.out.println();
			
			linkedList.clear(); //초기화
			
		}
		
		
	}

}
