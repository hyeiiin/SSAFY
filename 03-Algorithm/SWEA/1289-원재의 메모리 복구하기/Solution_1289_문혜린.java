package algorithm;

import java.io.*;
import java.util.*;

public class Solution_1289_문혜린 {
	static String oriStr; //원래 상태
	
	public static int algorithm(String str, int index, int result) {
		if(oriStr.charAt(index) != str.charAt(index)) { //자리수 비트 다르면 
			if(oriStr.charAt(index) == '1') { //원래 인덱스가 1을 가르키는 경우
				//현재 가르키는 위치 전까지 그대로 두고 나머지 인덱스는 끝까지 1로 변경
				str = str.substring(0, index) + str.substring(index).replace('0', '1');
			}
			else { //원래 인덱스가 0을 가르키는 경우
				//현재 가르키는 위치 전까지 그대로 두고 나머지 인덱스는 끝까지 0으로 변경
				str = str.substring(0, index) + str.substring(index).replace('1', '0');
			}
			result++; //수정 횟수 증가
		}
		if(oriStr.equals(str)) { //문자 같아지거나 인덱스 끝까지 다 본 경우 재귀 탈출
			return result;
		}
		return algorithm(str, ++index, result); //문자 같지 않으면 다음 인덱스로 넘어가기
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			oriStr = br.readLine();
			String str = oriStr.replace('1', '0'); //0000으로 바꾸기
			System.out.println("#"+(t+1)+" "+algorithm(str, 0, 0));
		}
		
		
	}

}
