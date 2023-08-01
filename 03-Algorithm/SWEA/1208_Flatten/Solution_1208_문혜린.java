package algorithm.swea;

import java.util.*;
import java.io.*;

//Flatten
public class Solution_1208_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int dump = Integer.parseInt(br.readLine()); //덤프 횟수
			StringTokenizer st = new StringTokenizer(br.readLine());
			int box[] = new int[100]; //상자 배열
			
			for(int i=0; i<100; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<dump; i++) {
				Arrays.sort(box); //매번 정렬해서 최고점, 최저점 찾아주기
				box[99] -= 1; //가장 높은 상자 하나 옮기기
				box[0] += 1; //가장 낮은 상자로
			}
			Arrays.sort(box); //마지막으로 정렬해주기
			System.out.println("#"+t+" "+(box[99]-box[0])); //최고점과 최저점 높이 차
		}
		
	}

}
