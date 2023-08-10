package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _5215 {
	//제한 개수, 제한 칼로리로 사용할 변수
	static int n, l;
	//선호도, 칼로리를 넣을 배열
	static int[] t;
	static int [] k;
	// 최적 점수를 임시저장하기위한 변수
	static int mx;
	
	//조건을 충족하는 조합을 찾는 함수 부분집합에 사용할 재료의 배열번호와 점수, 기저조건에 총 칼로리를 변수로 받는다.
	static void die(int cnt, int point, int totalkal) {
		//제한을 넘길 경우 곧바로 종료
		if(totalkal > l)return;
		//조합을 완성한 경우 멈추고 조건을 실행함
		if(cnt == n) {
			mx = Math.max(mx, point);
			return;
		}
		
		//재료를 넣는 함수 부분집합이기에 재료를 넣거나 넣지 않거나로 표현
		//재료를 넣었을때, 다음 재료를 결정하기위해 what+1을 기본으로 수행하고 재료점수, 조건에 맞는 칼로리를 더해준다.
		die(cnt+1, point+t[cnt], totalkal+k[cnt]);
		//재료를 넣지 않았을때,  점수와 열량을 더하지 않고 넘긴다
		die(cnt+1, point, totalkal);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//테스트 케이스 횟수를 받음
		int tt = Integer.parseInt(bf.readLine());
		for(int test = 1; test <= tt; test++) {			
			//변수들에 값 입력
			StringTokenizer st = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			t = new int[n];
			k = new int[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				t[i] = Integer.parseInt(st.nextToken());
				k[i] = Integer.parseInt(st.nextToken());
			}
			//최대값을 찾기위해 초기화
			mx = 0;
			//함수 실행
			die(0, 0, 0);
			sb.append("#").append(test).append(" ").append(mx).append("\n");
		}
		System.out.println(sb.toString());
	}

}
