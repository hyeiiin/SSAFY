package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class Main_2961_문혜린 {
	static long s[]; //신맛
	static long ss[]; //쓴맛
	static int N; //재료 개수
	static boolean isSelected[]; //중복 제거하기 위한 선택 여부 배열
	static long min = 1000000000; //신맛,쓴맛 차이
	static int zeroCheck; //공집합 체크
	
	public static void algorithm(int cnt) {
		if(cnt == N+1) { //부분 집합 완성
			zeroCheck = 0; //공집합 체크 변수 초기화
			int tempS = 1, tempSS = 0; //신맛은 곱, 쓴맛은 합이므로 1, 0으로 초기화
			
			for (int i = 1; i <= N; i++) { //완성한 부분 집합 안에서
				if(isSelected[i]) { //선택된 부분 집합일 경우
					tempS *= s[i]; //신맛 곱하기
					tempSS += ss[i]; //쓴맛 더하기
				}
				else { //선택 안됐을 경우
					zeroCheck++; //공집합 여부 체크
				}
			}
			if(zeroCheck == N) { //공집합일 경우
				return; //돌아가기
			}
			else {
				if(min > Math.abs(tempSS-tempS)) { //차이 적으면
					min = Math.abs(tempSS-tempS); //갱신
				}
			}
		}
		else {
			isSelected[cnt] = true; //부분 집합 포함
			algorithm(cnt+1);
			isSelected[cnt] = false; //부분 집합 취소
			algorithm(cnt+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		isSelected = new boolean[N+1]; //중복 제거하기 위한 선택 여부 배열
		
		s = new long[N+1]; //신맛
		ss = new long[N+1]; //쓴맛
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			s[i] = Long.parseLong(st.nextToken());
			ss[i] = Long.parseLong(st.nextToken());
		}
		
		algorithm(1);
		
		System.out.println(min);
	}

}
