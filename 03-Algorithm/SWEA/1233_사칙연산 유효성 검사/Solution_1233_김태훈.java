package swea;
import java.util.*;
import java.io.*;
public class Solution_1233_김태훈 {
	static int t = 10;
	static int[] arr = new int[t];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		ArrayDeque<String> que = new ArrayDeque<>();
		Arrays.fill(arr, 1);		//배열 전부 1로 초기화
		int count = 0;
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());		//입력받을 갯수
			
			for (int j = 0; j < n; j++) {
				String input = br.readLine();		//한줄로 입력받음
				String[] Split = input.split(" ");	//0번에 순서, 1번에 기호 or 숫자 2번부터는 노상관
				int len = Split.length;
				if(len > 2) {
					if(!(Split[1].equals("-")) && !(Split[1].equals("+")) && !(Split[1].equals("*")) && !(Split[1].equals("/"))) {
						arr[i] = 0;
						count++;
					}
				}
				else {
					if((Split[1].equals("-")) || (Split[1].equals("+")) ||(Split[1].equals("*")) || (Split[1].equals("/"))) {
						arr[i] = 0;
					}
				}
			}
			if(n%2==0) {
				arr[i] = 0;				
			}
		}
		System.out.println(count);
		for (int i = 0; i < t; i++) {
			System.out.printf("#%d %d\n",i+1, arr[i]);
		}
	}

}
