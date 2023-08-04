import java.io.*;
import java.util.*;

public class Solution_1218_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//key-괄호종류 value-종류에 따른 숫자 
		//value가 짝수면 여는 괄호, 홀수면 닫는 괄호
		HashMap<Character, Integer> pair = new HashMap<>();
		pair.put('{', 0);
		pair.put('}', 1);
		pair.put('(', 2);
		pair.put(')', 3);
		pair.put('[', 4);
		pair.put(']', 5);
		pair.put('<', 6);
		pair.put('>', 7);

		for (int T = 1; T <= 10; T++) {
			int n = Integer.parseInt(br.readLine());
			Deque<Character> bracket = new ArrayDeque<>();
			int result = 1;

			sb = new StringBuilder();
			sb.append(br.readLine());
			for (int i = 0; i < n; i++) {
				// 큐에 넣기 : 해시맵에서 value가 짝수라면 여는 괄호
				if (pair.get(sb.charAt(i)) % 2 == 0) {
					bracket.add(sb.charAt(i));
				}
				// 큐에서 빼기 : 해시맵에서 value가 짝수라면 닫는 괄호
				else  {
					//현재닫는괄호와 큐의 last가 같은 종류라면(value를 2로나눈 몫이 같다면) last poll
					if (pair.get(bracket.peekLast())/2 == pair.get(sb.charAt(i))/2 ) {
						bracket.pollLast();
					} 
					//아니라면 결과값 0으로 저장 후 출력 -> 다음 테스트케이스 확인
					else {
						result = 0;
						break;
					}
				}
			}

			sb = new StringBuilder();
			sb.append("#").append(T).append(" ");
			sb.append(result);
			System.out.println(sb.toString());
		}
	}

}
