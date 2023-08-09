package algorithm.baekjoon;

import java.util.*;
import java.io.*;

class Node {
	int x; //원본 정수
	int absoluteX; //절댓값 정수
	Node(int x, int absoluteX){
		this.x = x;
		this.absoluteX = absoluteX;
	}
}
public class Main_11286_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); //연산의 개수
		
		//
		PriorityQueue<Node> heap = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				int res = o1.absoluteX - o2.absoluteX; //절댓값 오름차순 (절댓값 작은 값 먼저 출력)
				if(res==0) { //두 값의 절댓값이 같을 경우
					res = o1.x - o2.x; //원본 값 오름차순 (원본 값이 작은 수 출력)
				}
				return res;
			}
		});
		
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) { //x가 0이 아니라면 힙에 x 값 넣기
				Node node = new Node(x, Math.abs(x));
				heap.add(node);
			}
			else { //x가 0이라면 절댓값이 가장 작은 값 출력, 그 값 제거
				if(heap.isEmpty()) { //힙 비어있는 경우인데 출력하라고 한 경우
					sb.append(0+"\n");
				}
				else {
					Node node = heap.remove();
					sb.append(node.x+"\n");
				}
			}
		}
		System.out.println(sb);
	}

}
