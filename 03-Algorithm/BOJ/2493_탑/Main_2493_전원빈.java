import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static Stack<Integer> s = new Stack<>();
	static Stack<Integer> sn = new Stack<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());

		for(int i = 1; i <= n; i++) {
			check(Integer.parseInt(st.nextToken()), i, sb);
		}
		System.out.println(sb.toString());
		
	}
	
	public static void check(int num, int i, StringBuilder sb) {
		if(s.size()== 0) {
			s.push(num);
			sb.append(0).append(" ");
			sn.push(i);
			return;
		}
		
		if(s.peek() > num) {
			s.push(num);
			sb.append(sn.peek()).append(" ");
			sn.push(i);
			return;
		}else {
			s.pop();
			sn.pop();
			check(num, i, sb);
		}
	}

}