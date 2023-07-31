package swea;

import java.util.Scanner;

public class Solution_1289_Á¶Àº¼­ {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		char initMemory;
		String realMemory;
		int count;
	
		for(int tc =1; tc <= test_case; tc++) {
			count = 0;
			initMemory = '0';
			realMemory = sc.next();
			for(int i = 0; i < realMemory.length(); i++) {
				if (initMemory != realMemory.charAt(i)) {
					initMemory = realMemory.charAt(i);
					count++;
				}
			}
			System.out.println("#" + tc + " " + count);
		}
		sc.close();
	}
}
