import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	
	/*
	 * [deque method 정리]
	 *  [삽입]
	 *  				용량초과 시 예외 발생			삽입 성공 시 True, 실패 시 False 반환
	 *   - 앞에 원소 삽입 : addFirst()			/ 		offerFirst()
	 *   - 뒤에 원소 삽입 : addLast(), add()		/ 		offerLast(), offer()
	 *  
	 *  [삭제]
	 *  				덱이 비어 있으면 예외 발생				덱이 비어 있는 경우 null 리턴
	 *   - 앞에 원소 삭제 : removeFirst(), remove()	/ 	pollFirst(), poll
	 *   - 뒤에 원소 삭제 : removeLast()				/ 	pollLast()
	 * 
	 *  [확인]			덱이 비어있는 경우 예외			덱이 비어있는 경우 null
	 *   - 앞에 원소 확인 : getFirst()		/	peekFirst(), peek()
	 *   - 뒤에 원소 확인 : getLast()			/ 	peekLast()
	 *   
	 *  [기타]
	 *   - 
	 */
	
	static Deque<Integer> deque;
	static int N, LEN;
	static String input;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int tc = 1; tc < 11; tc++) {
			N = Integer.parseInt(br.readLine());
			input = br.readLine();
			st = new StringTokenizer(input);
			deque = new LinkedList<>();
			
			// 스택 배열에 입력받은 수 집어넣기
			for (int i = 0; i < 8; i++) {
				deque.offer(Integer.parseInt(st.nextToken()));
			}
			
			// 한자리 수 될 때까지 반복 ^^
			while (deque.peekLast() != 0) {
				for (int j = 1; j < 6; j++) {
					if (deque.getFirst() - j > 0) {
						deque.offer(deque.removeFirst() - j);
					} else {
						deque.removeFirst();
						deque.add(0);
						break;
					}
				}
			}
			
			System.out.print("#" + N + " ");
			for (int i = 0; i < 8; i++) {
				System.out.print(deque.remove() + " ");
			}
			System.out.println();
		}
		
	}
}
