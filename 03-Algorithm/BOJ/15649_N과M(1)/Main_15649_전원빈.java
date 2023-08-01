import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n = 0;
	static int m = 0;
	static List<Integer> li = new ArrayList<>();
	static int[] s = { 0, 1, 2, 3, 4, 5, 6, 7, 8};
	
	static void comcom() {
		if(li.size() == m) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j<m;j++) {
				sb.append(li.get(j));
				sb.append(" ");
			}
			System.out.println(sb.toString());
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(s[i] == 0)continue;
			li.add(i);
			s[i] = 0;
			comcom();
			li.remove(li.size()-1);
			s[i] = i;
		}
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		comcom();
	}
}