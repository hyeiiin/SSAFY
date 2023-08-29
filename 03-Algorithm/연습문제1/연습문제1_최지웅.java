package todo.lesson._0829;

public class 연습문제1_최지웅 {

	public static void main(String[] args) {

		int[] yellow = new int[10];
		int[] blue = new int[10];
		
		yellow[1] = 1;
		blue[1] = 1;
		
		yellow[2] = 2;
		blue[2] = 1;
		
		for (int n = 3; n <= 8; n++) {
			yellow[n] = yellow[n - 1] + blue[n - 1];
			blue[n] = yellow[n - 1];
		}
		
		System.out.println(yellow[8] + blue[8]);
	}

}
