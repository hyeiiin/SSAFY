import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<Integer> list = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= n; i++) {
			list.add(i);
		}
		int loc = 0;
		sb.append("<");
		for(int i = 0; i < n; i++) {
			loc= (loc+ k-1) % list.size();
			sb.append(list.get(loc)+", ");
			list.remove(loc);
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		System.out.println(sb.toString());
	}

}