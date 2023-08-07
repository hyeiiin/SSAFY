package SWEA;

import java.util.*;
import java.io.*;

public class Solution_1228_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 10개 시작
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());	// 원본 암호문의 길이 N 입력
			List<String> list = new ArrayList<>();	// 암호문을 저장할 ArrayList 선언
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 암호문의 길이만큼 입력받은 암호들 list에 저장할 수 있게끔 반복문 돌리기
			for(int i=0; i<N; i++) {
				String password = st.nextToken();
				list.add(password);
			}
			
			int commandCount = Integer.parseInt(br.readLine());	// 명령어 개수 입력
			st = new StringTokenizer(br.readLine());
			// 명령어 개수만큼 반복문 돌리기
			for(int i=0; i<commandCount; i++) {
				char command = st.nextToken().charAt(0);	// 명령어 입력(삽입 연산 I만 되기때문에 사용할 필요 X)
				int x = Integer.parseInt(st.nextToken());	// x의 위치(index) 입력받음
				int y = Integer.parseInt(st.nextToken());	// y개 (암호들 개수) 입력받음
				
				// y개의 암호들 입력받을 수 있게끔 반복문 돌리기
				for(int j=0; j<y; j++) {
					String passwordInput = st.nextToken();	// 숫자로 이루어진 암호들 입력
					list.add(x, passwordInput);	// x의 위치(index)에 암호 추가해줌
					x++;	// x위치(index) 증가
				}
				
			}
			sb.append("#").append(tc).append(" ");
			// 암호문의 처음 10개 항 출력할 수 있게끔 StringBuilder에 저장
			for(int i=0; i<10; i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");	// 개행 추가
		}
		
		System.out.print(sb);
	}

}
