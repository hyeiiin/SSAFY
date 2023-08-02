
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static List<Integer> li = new ArrayList<>();
	
	static void roll(int s){
		if(li.size() == m) {
			StringBuilder sb = new StringBuilder();
			for(int a : li) {
				sb.append(a + " ");
			}
			System.out.println(sb.toString());
		}
		
		for(int i = s; i<= n; i++) {
			li.add(i);
			roll(i+1);
			li.remove(li.size()-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		roll(1);
	}

}