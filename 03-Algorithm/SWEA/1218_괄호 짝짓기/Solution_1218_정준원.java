package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Solution_1218_정준원 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int[] dr = { 0, 1, 0, -1 }; // 좌 하 우 상
		int[] dc = { 1, 0, -1, 0 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n, m;
		int t = 10;

		out: for (int i = 0; i < t; i++) {

			int size = Integer.parseInt(br.readLine());
			String str = null;
			str = br.readLine();

			Stack<Character> st = new Stack<>();

			for (int j = 0; j < size; j++) {

				if (str.charAt(j) == '(' || str.charAt(j) == '[' || str.charAt(j) == '{' || str.charAt(j) == '<') {
					st.add(str.charAt(j));
				}

				else {
					if (st.peek() == '(' && str.charAt(j) == ')') {
						st.pop();
						continue;
					}
					if (st.peek() == '{' && str.charAt(j) == '}') {
						st.pop();
						continue;
					}
					if (st.peek() == '<' && str.charAt(j) == '>') {
						st.pop();
						continue;
					}
					if (st.peek() == '[' && str.charAt(j) == ']') {
						st.pop();
						continue;
					}

					System.out.println("#" + (i + 1) + " " + "0");
					continue out;
				}

			}

			System.out.println("#" + (i + 1) + " " + 1);

		}

	}

}
