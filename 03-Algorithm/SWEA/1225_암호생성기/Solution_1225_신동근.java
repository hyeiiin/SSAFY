package SWEA;

import java.util.*;
import java.io.*;

public class Solution_1225_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 10번 돌리기
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());	// 해당 테스트케이스 번호 입력받기
			Queue<Integer> queue = new LinkedList<>();	// LinkedList로 Queue 선언해주기 (단방향)
			StringTokenizer st = new StringTokenizer(br.readLine());	// 한줄 입력받는데 공백단위로 끊어서 토큰값들 저장
			// 8개의 숫자 입력받기
			for(int i=0; i<8; i++) {
				int number = Integer.parseInt(st.nextToken());
				queue.add(number);	// 입력받은 수 큐에 저장
			}
			
			// 무한 반복 이용
			while(true) {
				boolean isOk = false;	// 사이클 돌릴 때 해당 수가 0보다 작거나 같은 경우 찾았는지 판단해주는 변수
				// 1사이클 -> 5개의 숫자
				for(int i=1; i<=5; i++) {
					int num = queue.poll() - i;		// 1사이클 흐르는대로 큐에서 뽑은 숫자 감소시켜준 뒤
					// 해당 수가 0보다 작거나 같아지는 경우
					if(num <= 0) {
						isOk = true;	// 해당 수 0보다 작거나 같은 경우 찾았음 (while문 빠져나올 때 쓰려고)
						queue.add(0);	// 큐에 0 저장
						break;			// 반복문 빠져나옴(for문)
					}
					queue.add(num);	// 큐에 감소한 해당 수 저장
				}
				
				// 8자리 암호 생성 가능한 경우
				if(isOk) {
					break;	// 무한반복 빠져나옴
				}
			}
			
			sb.append("#").append(tc).append(" ");
			// 큐가 빌때까지 큐에 저장된 숫자들 뽑아서 StringBuilder에 저장
			while(!queue.isEmpty()) {
				sb.append(queue.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}

}
