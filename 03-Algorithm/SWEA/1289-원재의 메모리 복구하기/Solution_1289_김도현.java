import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1289_김도현 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringBuffer sb = new StringBuffer();
			
			String str = br.readLine();
			
			int result = 0;
			
			for (int i = 0; i < str.length(); i++) {
				sb.append("0");
			}
			
			for (int i = 0; i < str.length(); i++) {
				
				char ch1 = str.charAt(i);
				char ch2 = sb.charAt(i);
				
				if(ch1==ch2) {
					continue;
				}
				else {
					result ++;
					if(ch1=='1') {
						for (int j = i; j < str.length(); j++) {
							sb.insert(j,"1");
							sb.deleteCharAt(j+1);
						}
					}else if(ch1=='0') {
						for (int j = i; j < str.length(); j++) {
							sb.insert(j,"0");
							sb.deleteCharAt(j+1);
						}
					}
					if(sb.toString().equals(str)) {
						break;
					}
				}
				
			}
			System.out.println("#"+test_case+" "+result);
		}
		
	}
}
