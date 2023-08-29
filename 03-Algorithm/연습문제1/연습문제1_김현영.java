
public class 연습문제1_김현영 {
	
	static int[] f;

	public static void main(String[] args)  {
		
		f = new int[9];
		f[1] = 2;
		f[2] = 3;
		System.out.println(func(8));

	}
	
	static int func(int n) {
		for (int i = 3; i <= n; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f[n];
	}

}
