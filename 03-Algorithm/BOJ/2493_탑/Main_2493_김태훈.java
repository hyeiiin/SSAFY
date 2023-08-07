package baekjoon;
import java.util.*;
import java.io.*;
class Top{
	int num;
	int height;
	Top(int num, int height){
		this.num = num;
		this.height = height;
	}
}
public class Main_2493_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		Stack<Top> stack = new Stack<>();
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			int height = Integer.parseInt(st.nextToken());
			//============처음엔 그냥 무조건 0=========
			if(stack.isEmpty()) {
				sb.append("0 ");
				stack.push(new Top(i, height));
			}
			else {
				while(true) {
					//=========종료 조건==========
					if(stack.isEmpty()) {
						sb.append("0 ");
						stack.push(new Top(i, height));
						break;
					}
					
					Top top = stack.peek();
					if(top.height > height) {	//픽한 탑 높이가 받은 숫자보다 높으면
					sb.append(top.num + " ");
					stack.push(new Top(i, height));
					break;
					}
					else {		//스택에 있는 높이가 현재 높이보다 낮으면
						stack.pop();
					}
				}
			}
		}
		
//		arr[0] = 0;
//		for(int i=0; i<n; i++) {
//			top[i] = Integer.parseInt(st.nextToken());
//		}
//		for(int i=1; i<n; i++) {
//			for (int j = i-1; j > 0; j--) {
//				if(top[i]<=top[j]) {
//					arr[i] = j+1;
//					break;
//				}
//			}
//		}
//		for (int i = 0; i < n; i++) {
//			sb.append(arr[i]).append(" ");
//		}
		System.out.println(sb);
	}

}
