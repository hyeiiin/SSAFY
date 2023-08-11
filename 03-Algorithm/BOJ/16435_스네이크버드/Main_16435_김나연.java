import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16435_김나연 {
	
	static int n,l,h[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		l=Integer.parseInt(st.nextToken());
		
		h=new int[n];
		
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			h[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(h);
	
		for (int i = 0; i < n; i++) {
			if(h[i]<=l) {
				l++;
			}else {
				break;
			}
		}
		
		sb.append(l);
		System.out.println(l);
		
	}

}
