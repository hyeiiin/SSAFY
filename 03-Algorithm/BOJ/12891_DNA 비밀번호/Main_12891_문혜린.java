package algorithm;

import java.util.*;
import java.io.*;

//DNA 비밀번호
public class Main_12891_문혜린 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken()); //원본 문자열 길이
		int P = Integer.parseInt(st.nextToken()); //부분문자열 길이
		st = new StringTokenizer(br.readLine());
		String dna = st.nextToken(); //DNA 문자열
		st = new StringTokenizer(br.readLine());
		//최소 개수
		int A = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int count = 0; //비밀번호 종류의 수
		
		int dnaCount[] = new int[4]; //각 부분 문자 개수 (A, C, G, T)
		//초기 세팅
		for(int i=0; i<P; i++) {
			if(dna.charAt(i)=='A') { //가르키는 곳이 A이면
				dnaCount[0]++; //개수 증가
			}
			if(dna.charAt(i)=='C') { //가르키는 곳이 C이면
				dnaCount[1]++; //개수 증가
			}
			if(dna.charAt(i)=='G') { //가르키는 곳이 G이면
				dnaCount[2]++; //개수 증가
			}
			if(dna.charAt(i)=='T') { //가르키는 곳이 T이면
				dnaCount[3]++; //개수 증가
			}
		}
		
		//비밀번호 조건 만족했을 경우
		if(A<=dnaCount[0] && C<=dnaCount[1] && G<=dnaCount[2] && T<=dnaCount[3]) {
			count++;
		}
		
		//슬라이딩 윈도우
		for(int i=P; i<S; i++) { //뒤로 더 추가될 수 있는 경우
			if(dna.charAt(i)=='A') { //가르키는 곳이 A이면
				dnaCount[0]++; //개수 증가
			}
			if(dna.charAt(i)=='C') { //가르키는 곳이 C이면
				dnaCount[1]++; //개수 증가
			}
			if(dna.charAt(i)=='G') { //가르키는 곳이 G이면
				dnaCount[2]++; //개수 증가
			}
			if(dna.charAt(i)=='T') { //가르키는 곳이 T이면
				dnaCount[3]++; //개수 증가
			}
			//앞이 빠지는 경우
			int j = i-P;
			if(dna.charAt(j)=='A') { //가르키는 곳이 A이면
				dnaCount[0]--; //개수 감소
			}
			if(dna.charAt(j)=='C') { //가르키는 곳이 C이면
				dnaCount[1]--; //개수 감소
			}
			if(dna.charAt(j)=='G') { //가르키는 곳이 G이면
				dnaCount[2]--; //개수 감소
			}
			if(dna.charAt(j)=='T') { //가르키는 곳이 T이면
				dnaCount[3]--; //개수 감소
			}
			
			//비밀번호 조건 만족했을 경우
			if(A<=dnaCount[0] && C<=dnaCount[1] && G<=dnaCount[2] && T<=dnaCount[3]) {
				count++;
			}
		}
		System.out.println(count);
	}

}
