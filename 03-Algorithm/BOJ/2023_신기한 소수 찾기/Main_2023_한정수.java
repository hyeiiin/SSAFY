import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2023_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		
		
		int start = 1;
		int temp = 0;
		boolean result = false;
		
		for(int i=1 ; i<num; i++) {
			start *= 10;
		}
		
		
		isSubPrime(0, num);
		
		
//  에라토스테네스 쓰면 터짐
////		int[] arr = new int[100000000];
//		boolean[] arr = new boolean[start*10];
//		// false >> 소수,  true >> not 소수
//		arr[0] = true;
//		arr[1] = true;
////		for(int i=2; i<=10000 ; i++) {
////			if(!arr[i]) {
////				int temp = 2;
////				while(i*temp < 100000000) {
////					arr[i*temp] = true;
////					temp += 1;
////				}
////			}
////		}
//		for(int i=2; i*i<start*10 ; i++) {
//			if(arr[i] == true) {
//				continue;
//			}
//			for(int j=i+i; j<start*10 ; j += i) {
//				arr[j] = true;
//			}
//		}
//		
//		
//		
//		
//		
//		
//		
//		for(int i=start; i<start*10 ; i++) {
//			if(arr[i]) {
//				//소수가 아니라면
//				continue;
//			}
//			else {
//				//만약 소수라면
//				temp = i;
//				for(int j=1 ; j<=num-1 ; j++) {
//					temp = (int)(temp / 10);
//					if(arr[temp]) {
//						result = false;
//						break;
//					}
//					else {
//						result = true;
//						continue;
//					}
//				}
//				if(result) {
//					System.out.println(i);
//				}
//				else {
//					continue;
//				}
//			}
//		}
		
	}
	
	
	private static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		for (int i=2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	
	// 4자리수 ABCD가 있으면 결국 A, AB, ABC, ABCD가 소수인지 확인하는거니까 A부터 검사, A*10 + B 검사 .....
	private static void isSubPrime(int num, int cnt) {
		if (cnt == 0) {
			System.out.println(num/10);
		}
		for (int i=0; i<10; i++) {
			if(isPrime(num+i)) {
				isSubPrime((num+i)*10, cnt-1);
			}
		}
	}
}
