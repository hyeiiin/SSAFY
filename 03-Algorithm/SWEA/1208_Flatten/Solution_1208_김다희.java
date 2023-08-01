import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=10;
		for(int tc=1;tc<=T;tc++) {
			int dump=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			int[] arr=new int[100];
			for(int i=0;i<100;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int front=0,end=99,tmp;
			while(dump>0) {
				arr[front]++;
				arr[end]--;
				dump--;
				for(int i=0;i<end;i++) {
					if(arr[i]<arr[front]) {
						tmp=arr[i];
						arr[i]=arr[front];
						arr[front]=tmp;
						break;
					}
				}
				for(int i=end;i>=front;i--) {
					if(arr[i]>arr[end]) {
						tmp=arr[i];
						arr[i]=arr[end];
						arr[end]=tmp;
						break;
					}
				}
			}
			System.out.println("#"+tc+" "+(arr[end]-arr[front]));
		}
	}
}
