package swea;
import java.util.*;
import java.io.*;
public class Solution_1289_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int[] arr = new int[t];
		
		for(int i=0; i<t; i++) {
			String n = br.readLine();
			String memory = n;
			int count = 0;
			char[] str = new char[n.length()];
			//str에다가 한개씩 저장
			for(int j=0; j<n.length(); j++) {
				str[j] = n.charAt(j);
			}
			
			for(int j=0; j<n.length(); j++) {
				if(str[j]=='1' && j==0) {
					count++;
				}
				else if(j!=0 && str[j]!=str[j-1]) {
					count++;
				}
				arr[i] = count;
			}
		}
		for(int i=0; i<t; i++) {
			System.out.printf("#%d %d\n", i+1, arr[i]);
		}
	}

}
