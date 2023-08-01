import java.io.*;
import java.util.*;


/*n까지 숫자 배열 / isUsed 배열
 * 재귀로 (개수m) 
 * */

public class Main_15649_김현영 {

	//수열을 담을 리스트
	static List<Integer> result= new ArrayList<>();
	static int n;
	static int m;
	
	
	public static void func( ) {
		//수열을 다 구했으면 출력
		if (result.size() == m) {
			StringBuilder sb = new StringBuilder();
			for (int r : result) {
				sb.append(r).append(" ");
			}
			System.out.println(sb);
			return;
		}
		
		for (int j = 1; j <=n; j++) {
			if(! result.contains(j)) {
				result.add(j);
//				System.out.printf("RR: %d %d\n", result[cnt], cnt);
				func();
				//마지막에 들어온 수열 삭제
				result.remove(result.size()-1);
			}else {
				continue;
			}
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//n,m 입력
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		func(); 
 
		
	}

}
