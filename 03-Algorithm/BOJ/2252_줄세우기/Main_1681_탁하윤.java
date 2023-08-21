package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1681_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// N 명의 학생들
		String L = st.nextToken();	// 포함되지 않을 라벨 숫자
		int cnt = 0;	// 줄 선 사람 수
		String max = "1";	// 1부터 시작
		
		while(true) {
			int temp = Integer.parseInt(max);	// temp에 현재 줄 선 번호(string)을 int형으로 담아 두기
			if(max.contains(L)) {	// max에 L이 포함되어 있다면
				max = Integer.toString(temp+1);	// max를 +1해주기
				continue;	// 다음 번호로 다시 돌기
			}else {	// max에 L이 포함되어 있지 않다면
				cnt++;	// 현재 줄 선 사람 수 +1 해주기
				if(cnt == N) {	// 만약 현재 줄 선 사람 수가 N명의 학생수와 같다면 while문 탈출
					break;
				}
			}
			max = Integer.toString(temp+1);	// 다음 max값으로 대입
		}
		
		System.out.println(max);
	}

}
