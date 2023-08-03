package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_12891_문예은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); // 문자열 총 길이
		int P = Integer.parseInt(st.nextToken()); // 비번 사용 길이
		String str = br.readLine(); // 문자열 배열 받기
		
		HashMap<Character, Integer> count = new HashMap<>(); // ACGT 개수 셀 맵 생성
		count.put('A', 0);
		count.put('C', 0);
		count.put('G', 0);
		count.put('T', 0);
		
		char[] dna = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			dna[i]= str.charAt(i); // 문자열 한글자씩 배열로 옮기기
		}
		
		st = new StringTokenizer(br.readLine()); // 각 문자 당 최소 개수조건
		int a = Integer.parseInt(st.nextToken()); 
		int c = Integer.parseInt(st.nextToken()); 
		int g = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < P; i++) { // 첫 슬라이딩 윈도우 만들기
			int findNum = count.get(dna[i]); // 문자의 현재까지의 개수 가져오기
			count.replace(dna[i], findNum+1); // 들어온 문자 키의 개수 증가시키기
		}
		// 슬라이딩 윈도우가 비번 조건 만족하는지
		int result = 0;
		int win_left = 0; // 슬라이딩 윈도우의 왼쪽 인덱스값
		for(int i = P-1; i < S; i++) { // 슬라이딩 윈도우의 끝이 전체 문자열 맨 끝까지 이동
			if (count.get('A') >= a && count.get('C') >= c 
					&& count.get('G') >= g && count.get('T') >= t) {
				result++;
			}
			if (i+1 < S) { // 다음 슬라이딩으로 넘어갈 때 전체 문자열 범위를 넘어서는지 확인
				int findNum = count.get(dna[i+1]); // 추가되는 문자 카운트 증가
				count.replace(dna[i+1], findNum+1);
				findNum = count.get(dna[win_left]); // 빠지는 문자 카운트 감소
				count.replace(dna[win_left++], findNum-1);
			} else break;
		}	
		System.out.println(result);
	}
}
