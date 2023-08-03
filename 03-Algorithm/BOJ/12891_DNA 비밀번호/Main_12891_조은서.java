import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891_조은서 {

	static int[] check; // 필요한 A, C, G, T의 최소 갯수
	static int[] charCnt; // 입력 받은 문자열에서 A, C, G, T의 개수 counting
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); // 문자열 길이
		int p = Integer.parseInt(st.nextToken()); // 부분 문자열 길이
		
		char[] str = br.readLine().toCharArray(); // DNA 문자열
		
		check = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			check[i] = Integer.parseInt(st.nextToken()); // A, C, G, T 순서대로 필요한 최소 갯수 저장
		}
		
		charCnt = new int[4];
		for(int i=0; i<p; i++) {
			if(str[i]=='A') charCnt[0]++;
			else if(str[i]=='C') charCnt[1]++;
			else if(str[i]=='G') charCnt[2]++;
			else if(str[i]=='T') charCnt[3]++;
		}
		
		int answer =0;
		if(verifyPassword()) {
			answer++;
		}
		
		
		//  부분 문자열 만들기
		int i = -1;
		
		for (int j=p; j<s; j++) {
			i = j - p; // 이전 부분 문자열의 시작 위치
			
			// 이전 부분 문자열 시작 문자 counting 제외
			if(str[i]=='A') charCnt[0]--;
			else if(str[i]=='C') charCnt[1]--;
			else if(str[i]=='G') charCnt[2]--;
			else if(str[i]=='T') charCnt[3]--;
			
			// 현재 부분 문자열 마지막 문자 counting
			if(str[j]=='A') charCnt[0]++;
			else if(str[j]=='C') charCnt[1]++;
			else if(str[j]=='G') charCnt[2]++;
			else if(str[j]=='T') charCnt[3]++;
			
			if(verifyPassword()) {
				answer++;
			}
			
		}
		System.out.println(answer);
		
		
	}

	private static boolean verifyPassword() {
		for(int i=0; i<4; i++) {
			if(charCnt[i] < check[i]) { // 비밀번호 조건을 만족했는지 확인
				return false;
			}
		}
		return true;
	}
	
}
