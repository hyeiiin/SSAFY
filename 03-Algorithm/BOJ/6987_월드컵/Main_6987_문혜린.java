package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//월드컵
public class Main_6987_문혜린 {
	static int[][] arr;
	//경기를 진행하는 경우의 수 15가지
	static int[] my = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4}; //본인팀
	static int[] other = {1, 2 ,3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5}; //상대팀
	static boolean end = false; //가능 or 불가능 결과
	
	public static void back(int game) {
		if(end) { //결과 나왔을 경우 재귀 더 안돌게 하는 조건문
			return;
		}
		if(game == 15) {
			end = true;
			return;
		}
		
		//본인팀이 이기는 경우 (본인팀 이겼을 때 점수 남아있고 상대팀 졌을 때 점수 남아있으면)
		if(arr[my[game]][0] > 0 && arr[other[game]][2] > 0) {
			//1점씩 빼줘서 경우의 수 하나 걷어내기
			arr[my[game]][0]--;
			arr[other[game]][2]--;
			back(game+1); //다음 경기
			//해당 경우가 아닐 수도 있으므로 원상복구 시켜주기
			arr[my[game]][0]++;
			arr[other[game]][2]++;
		}
		//무승부인 경우
		if(arr[my[game]][1] > 0 && arr[other[game]][1] > 0) {
			//1점씩 빼줘서 경우의 수 하나 걷어내기
			arr[my[game]][1]--;
			arr[other[game]][1]--;
			back(game+1); //다음 경기
			//해당 경우가 아닐 수도 있으므로 원상복구 시켜주기
			arr[my[game]][1]++;
			arr[other[game]][1]++;
		}
		//상대팀이 이기는 경우
		if(arr[my[game]][2] > 0 && arr[other[game]][0] > 0) {
			//1점씩 빼줘서 경우의 수 하나 걷어내기
			arr[my[game]][2]--;
			arr[other[game]][0]--;
			back(game+1); //다음 경기
			//해당 경우가 아닐 수도 있으므로 원상복구 시켜주기
			arr[my[game]][2]++;
			arr[other[game]][0]++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 4; t++) {
			st = new StringTokenizer(br.readLine());
			arr = new int[6][3]; //6개국 승무패
			int total = 0; //총 점수
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					total += arr[i][j];
				}
			}
			if(total != 30) { //총 점수가 30점이 아니면 불가능한 결과
				sb.append(0 + " ");
				continue;
			}
			end = false; //결과 초기화
			back(0);
			if(end) { //가능한 결과일 경우
				sb.append(1 + " ");
			}
			else {
				sb.append(0 + " ");
			}
		}
		System.out.println(sb);
	}

}
