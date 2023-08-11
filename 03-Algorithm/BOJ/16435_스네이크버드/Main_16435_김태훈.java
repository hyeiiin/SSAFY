package baekjoon;
import java.util.*;
import java.io.*;
public class Main_16435_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int[] h = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(h);
		for (int i = 0; i < n; i++) {
			if(h[i]<=l) {
				l++;
			}
			else
				break;
		}
		System.out.println(l);
	}

}
