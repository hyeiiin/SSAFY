import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1233_조은서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		for(int tc=1; tc<=10; tc++) {
			boolean isPossible = true;
			int n = Integer.parseInt(br.readLine());
			
			for(int i=0; i<n; i++) {
				String[] str = br.readLine().split(" ");
				if("-+*/".indexOf(str[1]) >= 0) { // 루트가 연산자면 자식노드가 2개 있어야 함
					if(str.length != 4) {
						isPossible = false;
					}
				} else { // 루트가 숫자면 자식노드 없어야 함
					if(str.length > 2) {
						isPossible = false;
					}
				}
			}
			
			
			sb.append("#").append(tc).append(" ");
			if(isPossible) { 
				sb.append("1").append("\n");
			} else {
				sb.append("0").append("\n");
			}
		}
		System.out.println(sb);
		

	}

}
