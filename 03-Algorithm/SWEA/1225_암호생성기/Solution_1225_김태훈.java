package swea;
import java.util.*;
import java.io.*;
public class Solution_1225_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = 10;
		int[][] arr = new int[t][8];
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			int[] numbers = new int[8];
			ArrayDeque<Integer> que = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<8; j++) {
				numbers[j] = Integer.parseInt(st.nextToken());
				que.offerLast(numbers[j]);
			}
			int count = 1;
			while(que.peekLast()>0) {
				if(count>5)
					count = 1;
				int num = que.pollFirst();
				num -= count++;
				que.offerLast(num);
			}
			for(int j=0; j<8; j++) {
				arr[i][j] = que.pollFirst();
			}
			arr[i][7] = 0;
		}
		for (int i = 0; i < t; i++) {
			System.out.printf("#%d ", i+1);
			for (int j = 0; j < 8; j++) {
				System.out.printf("%d ",arr[i][j]);				
			}
			System.out.println();
		}
	}

}
