import java.util.Scanner;

public class Main_15649_한정수 {
	

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n =sc.nextInt();
		int m = sc.nextInt();
//		System.out.println("n : "+n+"  m : "+m);
		boolean[] isSelected = new boolean[n+1];
		int[] perm_arr = new int[m];
		
		perm(0, m,n,isSelected, perm_arr);
		
		
		
	}
	public static void perm(int start, int cnt, int num, boolean[] isSelected, int[] perm_arr) {
//		System.out.println("perm");
//		System.out.println("start "+start+" cnt "+cnt+" num "+num);
		
		
		//즉, cnt가 m
		if (start == cnt) {
//			System.out.println("start == cnt");
			return;
		}
		for (int i = 1; i <= num ; i++) {
//			System.out.println("====="+i+"========");
			if (isSelected[i] == true) {
				continue;
			}
			perm_arr[start] = i;
			isSelected[i] = true;
			perm(start+1, cnt, num, isSelected, perm_arr);
			isSelected[i] = false;
//			System.out.println("is selected = false");
			if (start == cnt - 1) {
				for (int j = 0; j < perm_arr.length; j++) {
//					System.out.println(i+" "+j);
					System.out.print(perm_arr[j]+" ");
				}
				System.out.println();
				
			}
			
			
		}
//		System.out.println(perm_arr.toString());
//		System.out.println(perm_arr.length);
//		System.out.println(perm_arr[0]);
//		
	}

}
// 1 2 3 4 5 6