package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12869_탁하윤 {
	static int HP[][][], scv[], min;
	static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};	// 공격 가능한 순서
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// SCV의 수
		scv = new int[3];	// SCV의 수는 최대 3개이므로 3으로 초기화
		HP = new int[61][61][61];	// hp마다 공격 횟수 저장
		min = Integer.MAX_VALUE;	// 최솟값 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {	// scv의 각 hp 대입
			scv[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(scv[0], scv[1], scv[2], 0);	// 현재 각 scv의 hp와 공격수 호출
		System.out.println(min);
	}
	
	private static void dfs(int s1, int s2, int s3, int cnt) {
		if(s1<=0) s1=0;	// scv[0]
		if(s2<=0) s2=0;	// scv[1]
		if(s3<=0) s3=0; // scv[2]
		
		if(min <= cnt) return;	// 현재 저장된 공격 최솟값보다 많이 호출됐다면 return
		if(HP[s1][s2][s3] != 0 && HP[s1][s2][s3] <= cnt) return;	// 방문한 곳이 지금 공격 횟수 보다 더 작은 경우 return
		
		HP[s1][s2][s3] = cnt;	// 현재 공격 횟수 저장
		
		if (s1 == 0 && s2 == 0 && s3 == 0) {	// 모두 0이라면 최솟값 저장
			min = Math.min(min, cnt);
			return;
		}		
		
		for (int i = 0; i < 6; i++) {	// 공격 가능한 순서로 공격
			dfs(s1- attack[i][0], s2- attack[i][1], s3- attack[i][2], cnt+1);
		}
	}

}
