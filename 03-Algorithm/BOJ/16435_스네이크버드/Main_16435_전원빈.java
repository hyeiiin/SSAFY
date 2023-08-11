import java.io.*;
import java.util.*;

public class Main {
	static int fruit;
	static int snakebird;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		fruit = Integer.parseInt(st.nextToken());
		snakebird = Integer.parseInt(st.nextToken());
		int[] li = new int[fruit];
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < fruit ; i++) {
			li[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(li);
		int wait = 0;
		for(int i = 0; i < fruit; i++) {
			if(li[i] > snakebird) {
				wait = i;
				break;
			}
		}
		snakebird += wait;
		for(int i = wait; i < fruit; i++) {
			if(li[i] <= snakebird) {
				snakebird++;
			}else {
				break;
			}
		}
		sb.append(snakebird);
		System.out.println(sb.toString());
	
	}

}