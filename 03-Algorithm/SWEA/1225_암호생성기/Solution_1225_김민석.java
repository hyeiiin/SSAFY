package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution_1225_김민석 {

	public static List<Integer> solution(List<Integer> arr) {
		//멈추는 조건 달성 시 break할 flag
		boolean flag = false;
		while(true) {
			//사이클
			for (int i = 1; i <= 5; i++) {				
				int tmp = arr.get(0);
				//0번째 제거
				arr.remove(0);
				//0보다 큰지 체크
				if(tmp-i<=0) {
					//0으로 추가, flag true로 변경
					arr.add(0);
					flag = true;
					break;
				} else {
					//추가
					arr.add(tmp-i);
				}			
			}
			if(flag) break;
		}
		return arr;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 10; i++) {
			sb.append("#").append(br.readLine()).append(" ");
			List<Integer> arr = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			for (int n : solution(arr)) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}