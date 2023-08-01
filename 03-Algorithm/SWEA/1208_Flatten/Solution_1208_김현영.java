import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution_1208_김현영 {

	static int n; // 최대 이동 횟수

	public static int func(List<Integer> box, int cnt) {

		// 오름차순 정렬
		Collections.sort(box);

		//높이 차이 찾기
		int max = box.get(box.size() - 1);
		int min = box.get(0);
		int dif = max - min;
		
		// 최대 횟수 이내 평탄화 완료
		if (max - min == 1)
			return dif;
		// 최대 횟수 도달
		if (cnt == n)
			return dif;

		//평탄화 작업
		box.set(0, min + 1);
		box.set(box.size() - 1, max - 1);

		return func(box, cnt + 1);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());

			List<Integer> box = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				box.add(Integer.parseInt(st.nextToken()));
			}

			int cnt = 0; // 이동 횟수
			System.out.printf("#%d %d\n", t, func(box, cnt));

		}

	}

}
