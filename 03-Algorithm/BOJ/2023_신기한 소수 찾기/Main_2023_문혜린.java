package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//신기한 소수
public class Main_2023_문혜린 {
	static int N; //N자리 신기한  소수
	static int num[] = {1, 2, 3, 5, 7, 9}; //N자리 소수 만들때 사용하는 소수
	static String result = ""; //재귀문 돌면서 만들어질 숫자 저장
	static StringBuilder sb;
	
	public static boolean isPrime(int n) {
		if(n==1) { //처음 값 1이면 소수 아님
			return false;
		}
		for(int i=2; i*i<=n; i++) {
			if(n%i==0) { //소수 아닌 경우
				return false;
			}
		}
		return true;
	}
	public static void algorithm(int cnt) {
		if(cnt == N) { //N자리 숫자 만들어졌을 경우
			if(!isPrime(Integer.parseInt(result))) { //마지막 소수 검사
				return; //재귀 종료
			}
			sb.append(result+"\n");
			return;
		}
		else {
			//자리수 올라가다가 소수 아닐 경우
			if(!result.isEmpty() && !isPrime(Integer.parseInt(result))) {
				return; //재귀 종료
			}
			else { //소수일 경우
				for(int i=0; i<6; i++) {
					result += Integer.toString(num[i]); //숫자 만들기
					algorithm(cnt+1); //자리수 밀기
					result = result.substring(0, result.length()-1); //백트래킹(다른 수 찾기 위해서 자리수 당겨주기)
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		algorithm(0);
		System.out.println(sb);

	}

}
