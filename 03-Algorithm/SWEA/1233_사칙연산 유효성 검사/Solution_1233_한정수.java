import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_한정수 {
	public class Node{
		private Object value;
		private Node left;
		private Node right;
		
		Node(){
			
		}
		Node(String value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		Node(String value, Node left, Node right){
			this.value = value;
			this.left = left;
			this.right = right;
		}
		
		public Object getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public Node getLeft() {
			return left;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		
		public void addLeft(Node node) {
			this.left = node;
		}
		public void addRight(Node node) {
			this.right = node;
		}
		
		
	}
	public class tree {
		Node left_node;
		Node right_node;
		Node root;
		
		tree(Node root, Node left_node, Node right_node){
			this.root = root;
			this.left_node = left_node;
			this.right_node = right_node;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//어찌됬든, 정점에 연산이 오냐, 숫자가 오냐인데, 숫자가 왔을때 자식이 있으면 안됨.
		// 굳이 트리를 만들어야되나?????
		for(int test_case=1 ; test_case<=10 ; test_case++) {
			boolean result = true;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int i=0; i<num ; i++) {
				if(result) {
					st = new StringTokenizer(br.readLine());
					int node_num = Integer.parseInt(st.nextToken());
					if (st.countTokens() == 3) {
						Object temp = st.nextToken();
						if (temp.equals("-") || temp.equals("+") || temp.equals("*") || temp.equals("/")) {
							continue;
						}
						else {
							result = false;
						}
					}
					else if(st.countTokens() == 2) {
						// 연산자가 1갠데 피연산자가 1개다? 그럼 잘못된거
						result = false;
					}
					else {
						Object temp = st.nextToken();
						if (temp.equals("-") || temp.equals("+") || temp.equals("*") || temp.equals("/")) {
							result = false;
						}
						
					}
					
				}
				else {
					br.readLine();
				}
				
				
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			if(result) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
			System.out.println(sb.toString());
			
			
		}
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int node_num = Integer.parseInt(st.nextToken());
//		System.out.println(st.countTokens());
//		System.out.println(st.nextToken().equals("-"));
//		System.out.println(st.nextToken());
//		System.out.println(st.nextToken());
		//5 - 10 11이면   3
		//58 8 이면       1
				
	}

}


