import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_12891_김현영 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int result = 0; // 출력할 비밀번호 종류의 수

		// 입력
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		String pw = br.readLine();

		//주어진 문자별 개수 -> key-문자 value-문자별개수
		st = new StringTokenizer(br.readLine());
		HashMap<Character, Integer> cnt = new HashMap<>();
		cnt.put('A', Integer.parseInt(st.nextToken()));
		cnt.put('C', Integer.parseInt(st.nextToken()));
		cnt.put('G', Integer.parseInt(st.nextToken()));
		cnt.put('T', Integer.parseInt(st.nextToken())); 

		// 슬라이딩 윈도우 첫번째
		// String을 사용하면 문자가 최대 길이인 1,000,000만큼 길어질 수 있음
		// tempPw의 마지막에 새로운 문자를 추가할 때 +연산자 사용시 시간복잡도는 O(n+k) : 기존문자열길이+새로운문자열의 길이
		StringBuilder tempPW = new StringBuilder();
		tempPW.append(pw.substring(0, p));
		
		// 슬라이딩윈도우의 문자 별 개수 구하기 -> key-문자 value-문자별개수
		HashMap<Character, Integer> tempCnt = new HashMap<>();
		tempCnt.put('A', 0);
		tempCnt.put('C', 0);
		tempCnt.put('G', 0);
		tempCnt.put('T', 0);
		for (int j = 0; j < tempPW.length(); j++) {
			tempCnt.put(tempPW.charAt(j), tempCnt.getOrDefault(tempPW.charAt(j), 0) + 1);
		}

		//주어진 문자열 선회
		for (int i = 0; i <= s - p; i++) {
			result += 1;
			// 문자별 개수 비교 : 주어진문자별 개수가 더 클 경우 다음 문자열 확인으로 넘어간다
			for (Character c : cnt.keySet()) {
				if (cnt.get(c) > tempCnt.get(c)) {
					result -= 1;
					break;
				}
			}

			//슬라이딩윈도우에서 가장 앞의 문자 제거 후 마지막에 새로운 문자 추가
			if (i != s - p) {
				// 슬라이딩윈도우에서 제일 앞의 문자 개수 -1
				char first = tempPW.charAt(i);
				tempCnt.put(first, tempCnt.getOrDefault(first, 0) - 1);

				// 슬라이딩 윈도우 새로만들기 : 임시문자열+원래pw에서 1글자
				// 슬라이딩 윈도우(tempPW)를 인덱스만으로 접근하기 때문에 가장 앞글자 제거 기능을 넣지 않았음
				char last = pw.charAt(p + i);
				tempPW.append(last);
				// 슬라이딩윈도우의 새로 추가된 문자 개수 새로 구하기
				tempCnt.put(last, tempCnt.getOrDefault(last, 0) + 1);
			}

		}

		System.out.println(result);

	}

}