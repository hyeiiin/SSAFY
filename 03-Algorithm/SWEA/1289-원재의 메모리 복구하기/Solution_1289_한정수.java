import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SWEA_1289_한정수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		
		//결국 첫번째 1을 찾고, 0 , 1 , 0, 1 교대되는 횟수를 세면 됨,
		for (int test_case = 1; test_case <= T; test_case++) {
			int start = 1;
			int change_count = 0;
			char cur_num = '3';
					
			
			char[] arr = sc.next().toCharArray();
			
			
			for(int j = 0; j < arr.length; j++) {
				//첫번째 1 찾고
				if (start == 1) {
					if(arr[j] == '0') {
						continue;
					}
					else {
						start = 0;
						change_count += 1;
						cur_num = '1';
					}
				}
				//이제 교대되는 횟수 카운팅
				else {
					if(arr[j] != cur_num) {
						change_count += 1;
						if(arr[j] == '0') {
							cur_num = '0';
						}
						else {
							cur_num = '1';
						}
					}
				}
			}
			System.out.printf("#%d %d\n",test_case,change_count);
			
		}
		
	}

}
