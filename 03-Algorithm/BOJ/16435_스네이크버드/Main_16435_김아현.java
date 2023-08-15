package ssafy.Boj;

import java.io.*;
import java.util.*;
// 스네이크버드
// 작은 순으로 탐색했을 때 
// 스네이크버드의 길이를 넘어가면 더 이상 먹을 수 있는 과일이 없다.
public class Main_16435_김아현 {
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt(); // 과일의 개수
			int len = sc.nextInt(); // 스네이크버드의 초기 길이
			
			int[] fruits = new int[n];
			for (int i = 0; i < n; i++) {
				fruits[i] = sc.nextInt();
			}
			
			// 과일 오름차순 정렬
			Arrays.sort(fruits);
			
			for (int i = 0; i < n; i++) {
				if(len >= fruits[i]) {
					len++;
				}else {
					break;
				}
			}
			
			System.out.println(len);
	}
}