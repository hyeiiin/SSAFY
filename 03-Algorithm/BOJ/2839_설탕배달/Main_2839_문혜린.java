package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//설탕 배달
public class Main_2839_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		while(N>0) {
			if(N%5 == 0) { //5로 나누어 떨어질 경우
				cnt += N/5; //봉지 개수 더하기
				break;
			}
			N -= 3; //5로 나누어 떨어지지 않으면 3 빼기
			cnt++; //봉지 개수 증가
			if(N<0) { //3으로도 나누어 떨어지지 않으면 N이 음수
				cnt = -1;
				break;
			}
		}
		System.out.println(cnt);

	}

}
