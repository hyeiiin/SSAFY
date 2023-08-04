package baekjoon;
import java.util.*;
import java.io.*;
public class Main_2023_김태훈 {

	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		funtion(0, 0);
	}
	//==============소수 검사=============
	private static boolean isPrime(int num) {
		if(num<2) return false;
		for(int i=2; i*i<=num; i++) {
			if(num%i==0) return false;
		}
		return true;	
	}
	//=============연산===========
	private static void funtion(int num, int size) {
		//============입력받은크기 + 소수면 출력=======
		if(size == n && isPrime(num)) {
			System.out.println(num);
			return;
		}
		for(int i=0; i<10; i++) {
			int temp = num*10+i;
			if(isPrime(temp)) funtion(temp, size+1);
		}
	}

}
