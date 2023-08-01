package swea;
import java.util.*;
import java.io.*;
public class Solution_1208_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = 10;
		int[] arr = new int[102];
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] data = new int[102];
			int max = 0;
			int max_index = 0;
			int min = 101;
			int min_index = 0;
			for(int j=0; j<100; j++) {
				data[j] = Integer.parseInt(st.nextToken());
				if(data[j]>max) {
					max = data[j];
					max_index = j;
				}
				if(min>data[j]) {
					min = data[j];
					min_index = j;
				}
			}
			for (int j = 1; j <= n; j++) {
				data[max_index]--;
				data[min_index]++;
				if (data[max_index] == data[min_index]) {
					arr[i] = 0;
					break;
				}
				else {
					min_index = 101;
					max_index = 0;
					min = 101;
					max = 0;
					for (int z = 0; z < 100; z++) {
						if (data[z] > max) {
							max_index = z;
							max = data[z];
						}
						if (data[z] < min) {
							min_index = z;
							min = data[z];
						}
					}
				}
			}
			arr[i] = max - min;	
		}
		for (int i = 0; i < t; i++) {
			System.out.printf("#%d %d\n",i+1, arr[i]);
		}
	}

}
