package ssafyPractice;

import java.io.*;

public class Main_2839_이세은 {

	private static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		int startN = n; //N킬로그램 불가한 경우 알기 위한 초기 n값 저장
		int cnt = n / 5;
		n %= 5; // 5로 나눌 수 있을 때까지 나눠놓기

		while (true) {
			if (n%3==0) { //5로 최대한 나눈 후 3으로 나누어지는 경우
				//3으로 나누어지는만큼 cnt++
				cnt += n/3;
				break;
			} else { //3으로 나누어 떨어지지 않으면 5의 몫에서 곱해진 5 한번 뺀다
				cnt--;
				n+=5;
				//계속 나누어지지 않아서 5를 곱하다가 기존의 n보다 커지면 N킬로그램 불가
				if(n > startN) {
					System.out.println(-1);
					return;
				}
				
			}
		}
		System.out.println(cnt);
	}

}
