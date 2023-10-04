import java.io.*;
import java.util.*;

public class Main_1786_김현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = 0;

		char[] text = br.readLine().toCharArray();
		int n = text.length; // 텍스트의 길이
		char[] pattern =  br.readLine().toCharArray();
		int m = pattern.length; // 패턴의 길이

		// 부분일치 테이블 만들기
		int[] pi = new int[m];
		int j = 0;	//접두사 포인터
		//패턴의 길이만큼 부분문자열 확인
		for (int i = 1; i < m; i++) {
			//불일치하다면 부분일치 테이블을 이용해 접두사 다시 설정
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];				
			}
			//접미사와 접두사가 일치하면 pi[i] 증가
			if (pattern[i] == pattern[j])
				pi[i] = ++j;
		}

 
		// 매칭 
		j = 0;	//패턴의 포인터		
		for (int i = 0; i < n; i++) {
			//불일치하다면 패턴의 포인터 재설정
			while(j>0 && text[i] != pattern[j]) {
				j = pi[j-1];				
			}
			//패턴과 텍스트기 일치하다면 패턴전체가 일치하는지 확인
			if (pattern[j] == text[i]) {
				//패턴 전체가 일치할 경우 일치횟수 증가. 일치 위치 저장. 패턴포인터 재설정
				if(j==m-1) {
					count++;
					sb.append(i - j+1).append(" ");
					j = pi[j];
				}
				//현재까지 패턴 일부만 일치할 경우 다음 패턴문자 확인
				else {
					j++;
				}
			}
		}
		 

		// 출력
		System.out.println(count);
		System.out.println(sb.toString());

	}

}
