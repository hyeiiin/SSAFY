package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_4013_김도현 {
	
	static int K,result;
	static ArrayList<ArrayList<Integer>> magnetList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			magnetList = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				magnetList.add(new ArrayList<>());
			}
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnetList.get(i).add(Integer.parseInt(st.nextToken()));
				}
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int magnetNum = Integer.parseInt(st.nextToken())-1;
				int dir = Integer.parseInt(st.nextToken());
				checkRolling(magnetNum,dir);
			}
			result = 0;
			cal();
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void checkRolling(int magnetNum, int startDir) {
		// 특정 자석이 특정 방향으로 회전
		int check[] = new int[4];
		check[magnetNum] = startDir;
		// 특정 자석의 왼쪽 확인
		int dir = startDir;
		for (int i = magnetNum-1; i >= 0; i--) {
//				System.out.println(magnetList.get(i).get(2));
//				System.out.println(magnetList.get(i+1).get(6));
			if(magnetList.get(i).get(2)!=magnetList.get(i+1).get(6)) {
				dir = dir*(-1);
				check[i] = dir;
			}else {
				break;
			}
		}
		dir = startDir;
		// 특정 자석의 오른쪽확인
		for (int i = magnetNum+1; i < 4; i++) {
//				System.out.println(magnetList.get(i-1).get(2));
//				System.out.println(magnetList.get(i).get(6));
			if(magnetList.get(i-1).get(2)!= magnetList.get(i).get(6)) {
				dir = dir*(-1);
				check[i] = dir;
			}else {
				break;
			}
		}
		rolling(check);
		
	}
	
	private static void rolling(int[] check) {
		for (int i = 0; i < check.length; i++) {
			if(check[i]!=0) {
				if(check[i]==1) {
					magnetList.get(i).add(0,magnetList.get(i).remove(7));
				}else {
					magnetList.get(i).add(magnetList.get(i).remove(0));
				}
			}
		}
	}
	private static void cal() {
		for (int i = 0; i < magnetList.size(); i++) {
			if(magnetList.get(i).get(0)==1) {
				result += (int)Math.pow(2, i);
//				System.out.println("result : " + result);
			}
		}
	}
	

}
