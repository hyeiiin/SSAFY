import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution_1225_김도현 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			// arraydeque 하나 만듬
			ArrayDeque<Integer> list = new ArrayDeque<>(8);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) { // 값 넣음
				int num = Integer.parseInt(st.nextToken());
				list.add(num);
			}
			while(list.getLast()!=0) {
				list.addLast(list.pollFirst()-1);
				if(!check(list.getLast())) {
					list.removeLast();
					list.addLast(0);
					break;
				}
				list.addLast(list.pollFirst()-2);
				if(!check(list.getLast())) {
					list.removeLast();
					list.addLast(0);
					break;
				}
				list.addLast(list.pollFirst()-3);
				if(!check(list.getLast())) {
					list.removeLast();
					list.addLast(0);
					break;
				}
				list.addLast(list.pollFirst()-4);
				if(!check(list.getLast())) {
					list.removeLast();
					list.addLast(0);
					break;
				}
				list.addLast(list.pollFirst()-5);
				if(!check(list.getLast())) {
					list.removeLast();
					list.addLast(0);
					break;
				}
			}
			System.out.print("#"+T+" ");
			for (int i = 0; i < 8; i++) {
				if(i==7) {
					System.out.println(list.pollFirst());
				}else {
					System.out.print(list.pollFirst()+" ");
				}
			}
			
		}
	}
	public static boolean check(int num) {
		if(num<=0) {
			return false;
		}else {
			return true;
		}
	}

}
