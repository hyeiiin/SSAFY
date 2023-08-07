import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1228_김도현 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int tesc = 1; tesc <= 10; tesc++) {
			int N  = Integer.parseInt(br.readLine());
			ArrayList<String > list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				list.add(st.nextToken());
			}
			int C = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while(true) {
				String start = st.nextToken();
				if(start.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						list.add(x++,st.nextToken());
					}
				}
				if(!st.hasMoreTokens()) break;
			}
			System.out.print("#"+tesc+" ");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i)+" ");
			}
			System.out.println();
		}
	}
}
