import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1228_김나연 {
	
	static int n,m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			n = Integer.parseInt(br.readLine());
			ArrayList<Integer> a = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				a.add(Integer.parseInt(st.nextToken()));
			}
			
			m=Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				st.nextToken();
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				for(int j=0; j<y; j++) {
					a.add(x+j, Integer.parseInt(st.nextToken()));
				}

			}
			
			sb.append("#" + tc + " ");
			for(int i=0; i<10; i++) {
				sb.append(a.get(i)+ " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

