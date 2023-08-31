import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_다리놓기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//서쪽 사이트 개수
			long N = Long.parseLong(st.nextToken());
			//동쪽 사이트 개수
			long M = Long.parseLong(st.nextToken());
			//다리를 지을 수 있는 경우의 수
			long result = 1;
			
			/*nCr= mCn : 동쪽 사이트 M개 중 N개가 연결 될 다리 뽑기
			* nCr = n!/(n-r)!r!
			* = n부터 1씩 줄어들면서 r개의 곱/ 1부터 1씩 늘어나면서 r개의 곱
			* = m부터 1씩 줄어들면서 n개의 곱/ 1부터 1씩 늘어나면서 n개의 곱
			*/
			for(int j = 0; j < N; j++) {//N번 돌려주기
				result *= (M - j);// m부터 1씩 줄어들면서 곱해지기
				result /= (j + 1); //1부터 1씩 늘어나면서 N까지 나누기
			}
			//==> 결국 조합 공식을 코드화 한 것
			System.out.println(result);
		}
	}

}