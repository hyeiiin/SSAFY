package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;


class Solution_1289_김민석 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Solution_1289_김민석 S = new Solution_1289_김민석();
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= T; i++) {
			sb.append("#");
			sb.append(i);
			sb.append(" ");
			sb.append(S.solution(br.readLine()));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private int solution(String bit) {
		String tmp = "";
		int answer = 0;
		for (int i = 0; i < bit.length(); i++) {
			tmp += "0";
		}
		char[] bitArr = bit.toCharArray();
		while(!tmp.equals(bit)) {
			char[] tmpArr = tmp.toCharArray();
			for (int i = 0; i < bitArr.length; i++) {
				if(bitArr[i] != tmpArr[i]) {
					tmp = tmp.substring(0, i);
					for (int j = 0; j < bit.length()-i; j++) {
						tmp += bitArr[i];
					}
					answer++;
					break;
				}
			}
		}
		return answer;
	}
}