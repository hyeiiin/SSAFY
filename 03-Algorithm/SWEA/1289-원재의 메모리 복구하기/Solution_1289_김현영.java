import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1289_김현영  {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		char[] check = {'0', '1'}; //현재 메모리가 0인지 1인지 체크
		
		for (int i = 0; i < T; i++) {
			int index = 0; 	//check 배열에서 사용할 메서드
			int cnt = 0;	//초기화 상태에서 원래 상태로 돌아가는 데 필요한 횟수
			String memory = br.readLine();	//원래 상태 메모리
			//메모리를 비트 1개씩 확인하며 앞선 비트와 같은 값을 가지고 있는 지 확인
			//다른 값이라면 원래 상태로 돌아가는데 필요한 횟수 +1
			for (int j = 0; j < memory.length(); j++) {
				if(check[index] != memory.charAt(j)) {
					cnt++;
					index = (index+1)%2;
				}
			}
			
			System.out.printf("#%d %d\n", i+1, cnt);
			
		}
		
	}

}

