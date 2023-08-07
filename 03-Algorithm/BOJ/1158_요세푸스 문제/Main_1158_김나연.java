import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1158_김나연 {
	
	
	static int n,k;
	
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> a = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer sto = new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(sto.nextToken());
		k=Integer.parseInt(sto.nextToken());
		
		
		for (int i = 1; i <= n; i++) {
			a.add(i);
		}
		
		int idx=0;
		
		StringBuilder sb=new StringBuilder();
		sb.append("<");
		
		while(!a.isEmpty()) {
			idx+=(k-1);
			
			while(true) {
				if(idx>=a.size()) {
					idx = idx-a.size();
				}else break;
			}
			sb.append(a.get(idx));
			
			if(a.size()!=1) {
				sb.append(", ");
			}
			a.remove(idx);
			
		}
		sb.append(">");
		System.out.println(sb);
	}
}
