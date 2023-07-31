package practice;

import java.io.*;
import java.util.*;

public class Solution_1289_이세은 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			//모든 비트가 0인 초기값에서 복구하기
			String answer = br.readLine();
			int[] bits = new int[answer.length()];
			int cnt=0;
			boolean turn1 = false;
			
			for(int i=0; i<answer.length(); i++) {
				String sub = answer.substring(i,i+1);
				//같지 않을 경우
				if(!sub.equals(String.valueOf(bits[i]))) {
					cnt++;
					if(sub.equals("1")) {
						for(int j=i; j<answer.length(); j++) {
							bits[j]=1; //1채워주기
							turn1 = true; //1로 바뀌었음을 알려주는 플래그 변수
						}
					}
					if(sub.equals("0") && turn1 == true) {
						//플래그 되돌리기
						turn1 = false;
						for(int j=i; j<answer.length(); j++) {
							bits[j]=0; //1채워주기
						}
					}
				}
			}
			System.out.println("#"+test_case+" "+cnt);
			
		}
	}

}
