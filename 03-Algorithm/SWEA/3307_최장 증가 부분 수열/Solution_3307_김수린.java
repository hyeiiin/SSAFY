package SWEA;

import java.util.*;
import java.io.*;

public class Solution_3307_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	static int T, N, arr[], LIS[], ans;	// 케이스수, 배열크기, 기존 받아줄 배열 arr, dp로 받아줄 lis, 답
	
	/**
	 * 기본 값 초기화
	 * @throws IOException
	 */
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		LIS = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		ans = 1;
	}
	
	/**
	 * LIS(최장 증가 부분 수열) 구하는 메소드
	 */
	public static void LIS() {
		for(int i = 0; i < N; i++) {
			if(LIS[i] == 0) LIS[i] = 1;		// 자기 자신이 있으니 가장 처음 최장 증가 부분 수열은 1
			for(int k = 0; k < i; k++) {	// 이전 값들 찾아가기
				if(arr[i] > arr[k]) {	// 이전 값보다 내가 크다면 이전 값의 dp에서 + 1한 값이 후보.
					// 후보들 끼리 비교하여 lis(dp)배열 바꿔나가기
					LIS[i] = Integer.max(LIS[i], LIS[k] + 1);
				}
			}
			// 답안을 교체할지 확인
			ans = Integer.max(ans, LIS[i]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			init();
			LIS();
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}
}
