package algo_0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_조은서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int cnt=0;
		if(N==4||N==7) {
			cnt = -1;
		} else if(N % 5 ==0) {
			cnt = N/5;
		} else if(N % 5 == 1 || N % 5 == 3) {
			cnt = N/5 + 1;
		} else if(N % 5 == 2 || N % 5 == 4) {
			cnt = N/5 + 2;
		}

	
			System.out.println(cnt);
		}

	}

