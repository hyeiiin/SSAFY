package ssafyPractice;

import java.io.*;
import java.util.*;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_1233_이세은 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = Integer.parseInt(br.readLine()); // 정점 수
			String[] binaryTree = new String[n + 1];
			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()); // 정점 인덱스
				String op = st.nextToken();
				binaryTree[idx] = op; // 각 트리인덱스에 저장
			}
			int current = 1;
			boolean fail = false;
			while (true) {
				if (current * 2 > n) { // 인덱스가 최대 크기직전, 첫 리프 노드까지 왔을 때 탐색 시작
					while (current < n) { // 마지막 인덱스까지 탐색
						try {
							Integer.parseInt(binaryTree[current]); // 가장 끝의 노드가 숫자가 아니라면 유효하지 않는 연산
						} catch (NumberFormatException e) {
							System.out.println("#" + test_case + " 0");
							fail = true;
							break;
						}
						current++;
					}
					if (!fail) { // 리프노드가 마지막 인덱스까지 숫자인 경우 유효한 연산
						System.out.println("#" + test_case + " 1");
						break;
					}
				}
				if (fail)
					break;
				current++;
			}
		}

	}

}
