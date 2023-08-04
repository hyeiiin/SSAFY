package practice;

import java.io.*;
import java.util.*;

public class Solution_1218_이세은 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트케이스 10개

			int len = Integer.parseInt(br.readLine());
			char[] charArr = br.readLine().toCharArray(); // 괄호 저장 배열

			int rslt = isCorrect(charArr); // 괄호 짝 맞는지 검사하는 메서드
			bw.write("#" + test_case + " " + rslt + "\n"); // 결과 출력
			bw.flush();

		}
		br.close();
		bw.close();
	}

	public static int isCorrect(char[] charArr) {
		Stack<Character> stack = new Stack<>();
		int j = 0;
		while (!(charArr[j] == ')') && !(charArr[j] == ']') && !(charArr[j] == '}') && !(charArr[j] == '>')) { // 닫는괄호 나오기 전까지 push
			stack.push(charArr[j++]);
		}
		while (!stack.isEmpty()) {// 닫는 괄호 나왔을 경우 짝 맞는지 확인
			if ((charArr[j] == ')') || (charArr[j] == ']') || (charArr[j] == '}') || (charArr[j] == '>')) { // 닫는괄호
				boolean fail = false;
				switch (stack.peek()) {
				case '(':
					if (charArr[j] != ')')
						fail = true;
					break;
				case '[':
					if (charArr[j] != ']')
						fail = true;
					break;
				case '{':
					if (charArr[j] != '}')
						fail = true;
					break;
				case '<':
					if (charArr[j] != '>')
						fail = true;
					break;
				}
				if (fail) {
					return 0;
				} else {
					// 짝 맞는 경우 pop
					stack.pop();
					j++;
				}
			} else {
				stack.push(charArr[j++]);
			}
		}
		return 1; //무사 통과
	}
}
