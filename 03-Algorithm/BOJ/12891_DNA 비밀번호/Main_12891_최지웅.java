package lesson.boj_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891_최지웅 {
	
	static int S, P;
	
	static char[] chArr;
	
	static int A, C, G, T;
	
	static boolean check(int start, int end) {
		
		
		int a = 0, c = 0, g = 0, t = 0;
		
		for (int i = start; i < end; i++) {
			if (chArr[i] == 'A') a++;
			else if (chArr[i] == 'C') c++;
			else if (chArr[i] == 'G') g++;
			else if (chArr[i] == 'T') t++;
		}
		
		return a >= A && c >= C && g >= G && t >= T;
	}

	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		int len = str.length();
		
		chArr = str.toCharArray();
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		
		
		char prevChar = chArr[0];
		
		int a = 0, c = 0, g = 0, t = 0;
		for (int i = 0; i < P; i++) {
			if (chArr[i] == 'A') a++;
			else if (chArr[i] == 'C') c++;
			else if (chArr[i] == 'G') g++;
			else if (chArr[i] == 'T') t++;
		}
		if (a >= A && c >= C && g >= G && t >= T) cnt++;
		
		for (int i = P; i < chArr.length; i++) {
			if (prevChar == 'A') a--;
			else if (prevChar == 'C') c--;
			else if (prevChar == 'G') g--;
			else if (prevChar == 'T') t--;
			
			prevChar = chArr[i - P + 1];
			
			if (chArr[i] == 'A') a++;
			else if (chArr[i] == 'C') c++;
			else if (chArr[i] == 'G') g++;
			else if (chArr[i] == 'T') t++;
			
			if (a >= A && c >= C && g >= G && t >= T) cnt++;
		}
		
		System.out.println(cnt);
	}

}
