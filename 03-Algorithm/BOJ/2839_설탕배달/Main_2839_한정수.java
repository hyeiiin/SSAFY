import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2839_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int num_5 = (int)N/(int)5;
		int N_mod = (int)N % (int)5;
		int num_3 = 0;
		boolean result = false;
		//3  5+3  10+3  15+3
		//1  1+5   1+10 1+15
		//3나머지
		//1   0     2    1
		//즉, 일단 5로 나누고, 나머지가 3으로 안나눠 떨어지면
		//5의 개수를 2개까지만 빼서 3으로 나눠떨어지나 체크해보고 그래도 안되면 절대 N을 못맞춤.

		for (int i=0; i<=2; i++) {
			if(((int)N_mod + (int)5*i) % (int)3 == 0) {
				if((N_mod+5*i) > N) {
					//그렇다고 원래 수보다 크면 안됨.
					continue;
				}
				num_5 -= i;
				num_3 = (N_mod + 5*i) / 3;
				System.out.println(num_5+num_3);
				result = true;
				break;
			}
		}
		if (!result) {
			System.out.println(-1);
		}
		
		
	}

}
