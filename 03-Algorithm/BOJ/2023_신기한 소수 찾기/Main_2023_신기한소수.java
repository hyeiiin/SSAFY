package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 2023 신기한 소수
 *
 */
public class Main_2023_신기한소수 {
	static int N;//N자리 숫자
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		//앞의 자리에 올 수 있는 소수는 2,3,5,7뿐
		interPrime(1, 2);
		interPrime(1, 3);
		interPrime(1, 5);
		interPrime(1, 7);
		
		System.out.println(sb.toString());
		
		br.close();
	}
	//에라토스테네스의 체를 통한 소수 판별 X(이것만으론 시간 초과)
	//2~루트N까지 소수확인하기
	static boolean isPrime(int num) {
		if(num == 0 || num == 1) { //0 or 1이면 소수 X
			return false;
		}
		
		for(int i=2; i*i<=num; i++) { 
			if(num%i==0) return false;// num을 i로 나누었을 때 나머지가 0이면, 1과 자기 자신 외 배수가 있음
			//소수 x므로 return false
		}
		return true;
	}
	//현재 입력받은 num이 소수로 판별되면, 자리수를 늘리면서 소수인지 확인하기
	static void interPrime(int depth, int num) { //자릿수 늘려가면서 소수 체크.
		if(depth == N) {
			sb.append(num+"\n");
			return;
		}
		
		//소수는 2를 제외하면 모두 홀수. 따라서 i를 홀수로 증가시켜 시간 복잡도 줄이기.
		for(int i=1; i<=9; i+=2) {
			int next = num*10+i;
			if(isPrime(next)) {
				interPrime(depth+1, next);
			}
		}
	}

}
