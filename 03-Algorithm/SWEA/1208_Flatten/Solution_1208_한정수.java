import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_1208_한정수 {
	public static void main(String[] args) throws IOException {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int test_case=1 ; test_case<=10 ; test_case++) {
			int dump = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			

			int[] arr = new int[100];
			int diff = -1;
			for(int j=0 ; j<100; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			
			for (int i=0 ; i<dump ; i++) {
				arr[arr.length-1] -= 1;
				arr[0] += 1;
				Arrays.sort(arr);
				if(arr[arr.length-1] - arr[0] <= 1) {
					diff = arr[arr.length-1] - arr[0];
					break;
				}
			}
			Arrays.sort(arr);
			diff = arr[arr.length-1] - arr[0];
			System.out.printf("#%d %d",test_case, diff);
			System.out.println();
			
			
			
		}
		
	}

}
