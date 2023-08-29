package todo.lesson._0829;

public class 연습문제2_최지웅 {

	public static void main(String[] args) {
		
		int[] f = new int[10];
		
		// blue & yellow
		f[1] = 2;
		
		// blue + blue / blue + yellow
		// yellow + blue / yellow + yellow
		// red
		f[2] = 5;
		

		//
		for (int n = 3; n < 10; n++) {
			// <n - 1> + blue / yellow
			// <n - 2> + red
			f[n] = 2 * f[n - 1] + f[n - 2];
		}
		
		System.out.println(f[6]);
		
	}

}
