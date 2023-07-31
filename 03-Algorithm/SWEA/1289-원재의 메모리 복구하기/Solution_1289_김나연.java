import java.util.Scanner;

public class Solution_1289_김나연 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		String s;
		char ori;
		int ret;
		for (int tc = 1; tc <= t; tc++) {
			s = sc.next();
			ori = '0';
			ret = 0;
			for (int i = 0; i < s.length(); i++) {
				if (ori != s.charAt(i)) {
					ori = s.charAt(i);
					ret++;
				}
			}
			
			System.out.println("#" + tc + " "+ ret);
		}
	}
}
