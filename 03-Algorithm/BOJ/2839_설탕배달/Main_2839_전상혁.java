package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_전상혁 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		//설탕봉지는 3, 5킬로그램
		int cnt = 0;

		//상근이는 귀찮기 때문에 최대한 적은 봉지를 들고 감. 즉 봉지의 개수를 최소화 하기 위해선 5킬로그램으로 많이 담아야 함
		//N이 5로 나눠지면 무조건 5킬로그램 봉지로 담아야 함
		while(N%5!=0) { //5로 안나눠질 경우
			//3을 빼고 5로 나눠지는지 확인
			N -= 3;
			cnt += 1;
			
			//N이 0과 3사이 값, 즉 3과 5로도 나눌 수 없는 값이면--> 3과 5의 봉지로 다 담을 수 없다. -1 출력 
			if (N>0 && N<3) {
				cnt = -1;
				break;
			}
			
		}
		//5로 떨어지는 경우
		cnt += N/5;
		
		
			
		System.out.println(cnt);

	}

}
