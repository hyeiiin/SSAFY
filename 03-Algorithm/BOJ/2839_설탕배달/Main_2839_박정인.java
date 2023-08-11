package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2839
 * @author SSAFY
 *
 */
public class Main_2839_박정인 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt5 = N / 5;	// 5kg 개수
		int cnt3 = (N - (cnt5 * 5)) / 3;	// 3kg 개수
		int calc = cnt5 * 5 + cnt3 * 3;
		
		// 봉지의 수를 최대한 적게하려면 5kg 봉지가 최대한 많아야 하고 
		// 5kg, 3kg 봉지로 N을 정확히 나눠야 한다. 
		// 따라서 5kg로 처음에 N을 나눈 값, 5kg를 제외하고 남은 값을 3kg로 나눈 값을 구하고 
		// 이 둘을 계산했을때 N이 만들어지지 않는다면 5kg 봉지수를 1씩 줄여가며 확인한다. 
		// 5kg 봉지수를 1씩 감소시키기에 0보다 작아질 가능성이 있는데 이럴경우는 성립하지 않고 5kg, 3kg 로 나눌수 없는 경우이다. 
		while ((cnt5 >= 0) && (calc != N)) {
			cnt5--;
			cnt3 = (N - (cnt5 * 5)) / 3;
			calc = cnt5 * 5 + cnt3 * 3; 
		}
		
		// 5kg이 0보다 작아진 경우는 5kg, 3kg 봉지로 나눌수 없는 경우
		int result = cnt5 < 0 ? -1 : cnt5 + cnt3; 
		
		System.out.println(result);
	}
}
