package todo._0807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1228_최지웅 {
	
	static StringTokenizer st;
	static StringBuilder sb;
	
	static ArrayList<String> nums;

	public static void main(String[] args) throws Exception {

		class LinkedList {

			class Node {
				String data;
				Node link;
				
				public Node() {
					
				}
				
				public Node(String data) {
					this.data = data;
				}
			}

			Node newNode = new Node();
			Node head = newNode;
			Node tail = newNode;

			void add(String data) {

				Node newNode = new Node(data);
				
				if (head == tail) {
					head.link = newNode;
					tail = newNode;
				} else {
					tail.link = newNode;
					tail = newNode;
				}
			}

			void insert(int x, int y) {

				Node curNode;
				
				curNode = head;
				for (int i = 0; i < x; i++) {
					curNode = curNode.link;
				}
				
				Node preNode = curNode;
				Node postNode = curNode.link;
				
				Node nextNode;
				for (int i = 0; i < y; i++) {
					nextNode = new Node(nums.get(i));
					curNode.link = nextNode;
					curNode = nextNode;
				}
				
				curNode.link = postNode;
			}
		}

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N;
		
		sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());

			LinkedList linkedList = new LinkedList();

			StringTokenizer st;
			
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				linkedList.add(st.nextToken());
			}
			
			N = Integer.parseInt(br.readLine());
			
			String cmd;
			int x;
			int y;
			
			st = new StringTokenizer(br.readLine());
			
			for (int n = 1; n <= N; n++) {

				cmd = st.nextToken();
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				nums = new ArrayList<>();
				for (int i = 0; i < y; i++) {
					nums.add(st.nextToken());
				}
				
				linkedList.insert(x, y);
			}
			
			sb.append("#").append(t).append(" ");
			
			int cnt = 0;
			for (LinkedList.Node it = linkedList.head.link; it != null; it = it.link) {
				cnt++;
				sb.append(it.data).append(" ");
				if (cnt >= 10) break;
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

}
