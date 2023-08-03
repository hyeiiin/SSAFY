import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2961_김현영 {
	static int n;
	static int[] sour, bitter;
	static List<Integer> result = new ArrayList<>();  //음식 재료의 조합을 저장할 리스트
	static List<Integer> dif= new ArrayList<>();	//맛 차이를 저장할 리스트

	public static void func(int start, int cnt, int size) {
		//신맛끼리 곱과 쓴맛끼리 합의 차이를 저장 
		if (cnt == size) {
			int s = 1;
			int b = 0;
			for (int i : result) {
				s *= sour[i-1];
				b += bitter[i-1];
			}
			dif.add(Math.abs(s-b));
			return;
		}

		for (int i = start; i <= n - size + cnt + 1; i++) {
			result.add(i);
			func(i + 1, cnt + 1, size);
			result.remove(result.size() - 1);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		sour = new int[n];
		bitter = new int[n];

		//재료의 수만큼 맛의 값 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}

		//공집합을 제외한 조합을 구하고 조합들의 맛 차이 구하기
		for (int i = 1; i <= n; i++) {
			result = new ArrayList<Integer>();
			func(1, 0, i);
		}
		
		//오름차순으로 맛의 차이 정렬 후 가장 작은 값 출력
		Collections.sort(dif);
		System.out.println(dif.get(0));

	}

}
