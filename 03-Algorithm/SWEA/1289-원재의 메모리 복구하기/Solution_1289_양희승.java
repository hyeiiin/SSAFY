import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		// 변수 선언
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		char[] arr;
		int cnt;
		
		// tc 개수 입력 및 반복
		int t = sc.nextInt();
		for (int tc=0; tc < t; tc++) {
			
			// 메모리 입력
			str = br.readLine();
			arr = str.toCharArray();
			
			// 전환 횟수 초기화
			cnt = 0;
			
			for (int i=0; i<arr.length; i++) {
				// 메모리 복구
				if (arr[i] != '0') {
					cnt++;
					for (int j = 0; j < arr.length - i; j++) {
						if (arr[i+j] == '1') {
							arr[i+j] = '0';
						} else {
							arr[i+j] = '1';
						}
					}
				}
			}
			// 출력
			System.out.printf("#%d %d", tc+1, cnt);
			System.out.println();	
		}
	}
}
