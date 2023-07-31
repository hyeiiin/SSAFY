import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int TC = 0;
	static int size = 0;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		TC = sc.nextInt();
		
		for (int tc = 1; tc < TC + 1; tc++) {
			///
			String str = sc.next();
			size = str.length();
			int[] lst = new int[size];
			
			for (int i = 0; i < size; i++) {
				lst[i] = str.charAt(i) - '0';
			}
			
			
			int[] bit = new int[size];
			int cnt = 0;
			
			for (int idx = 0; idx < size; idx++) {
				if (bit[idx] != lst[idx]) {
					int temp = lst[idx];
					
					for (int x = idx; x < size; x++) {
						bit[x] = temp;
					}
					cnt += 1;
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
