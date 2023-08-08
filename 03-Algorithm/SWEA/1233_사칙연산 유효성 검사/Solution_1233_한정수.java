import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_한정수 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//어찌됬든, 정점에 연산이 오냐, 숫자가 오냐인데, 숫자가 왔을때 자식이 있으면 안됨.
		// 굳이 트리를 만들어야되나?????
		for(int test_case=1 ; test_case<=10 ; test_case++) {
			boolean result = true;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int i=0; i<num ; i++) {
				if(result) {
					st = new StringTokenizer(br.readLine());
					int node_num = Integer.parseInt(st.nextToken());
					// 주어진 정보가 3개면 data, left번호, right번호.
					// data가 연산기호 외에 오면 안됨.
					// 문제에서 완전이진트리라고 조건을 줬고 그에 맞춰 노드번호를 주기 때문에 노드간 연결이 잘 되어있나 확인할 필요도 없음.
					if (st.countTokens() == 3) {
						Object temp = st.nextToken();
						if (temp.equals("-") || temp.equals("+") || temp.equals("*") || temp.equals("/")) {
							continue;
						}
						else {
							result = false;
						}
					}
					// 연산기호든 숫자든 개수가 (피연산자개수)+(연산자개수)가 2개는 있을 수 없음.
					else if(st.countTokens() == 2) {

						result = false;
					}
					// 숫자가 안오면 잘못됨.
					else {
						Object temp = st.nextToken();
						if (temp.equals("-") || temp.equals("+") || temp.equals("*") || temp.equals("/")) {
							result = false;
						}
						
					}
					
				}
				//result == false
				//결과가 끝났지만, 일단 주어진 테케에 대한 input은 싹 다 읽어야됨.
				else {
					br.readLine();
				}
				
				
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			if(result) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
			System.out.println(sb.toString());
			
			
		}
				
	}

}


