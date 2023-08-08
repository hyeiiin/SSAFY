package swea;
import java.util.*;
import java.io.*;
public class Solution_1228_김태훈 {
	static int t = 10;
	static int[][] result = new int[t][10];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n, m, index, num;
		LinkedList<String> list = new LinkedList<>();
		
		for(int i=0; i<t; i++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			//==========암호문 원문============
			for (int j = 0; j < n; j++) {
				list.add(st.nextToken());
			}
			m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				st.nextToken();				//저장할 필요가 없으면 이렇게 저장
				//암호문 삽입 시작 위치
				index = Integer.parseInt(st.nextToken());
				//암호문 갯수
				num = Integer.parseInt(st.nextToken());
				//
				int end = index+num;
				while(index<end) {
					list.add(index++, st.nextToken());
				}
			}
			System.out.printf("#%d ",i+1);
			for (int j = 0; j < 10; j++) {
				System.out.printf("%s ",list.get(j));
			}
			System.out.println();
			list.clear();
		}
	}
}
		
		
/*		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			ArrayDeque<Integer> que = new ArrayDeque<>();
			
			//=========원본 암호문 입력받음===========
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				 int num = Integer.parseInt(st.nextToken());
				 if(j<10) {
					 que.offer(num);
				 }
			}
			//===========명령어 입력받음==========
			int command = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<command; j++) {
				String chr = br.readLine();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(x<=10) {
					for (int k = 0; k < que.size()-x; k++) {
						arr[k] = que.poll();
					}
				}
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < y; k++) {
					int num = Integer.parseInt(st.nextToken());
					if(10>que.size()+k) {
						que.offer(num);
					}
				}
				for (int k = 0; que.size()<10; k++) {
					que.offer(arr[k]);
				}
			}
			for (int j = 0; j < 10; j++) {
				result[i][j] = arr[j];
			}
		}
		for (int i = 0; i < t; i++) {
			System.out.printf("#%d ",i+1);
			for (int j = 0; j < 10; j++) {
				System.out.printf("%d",result[i][j]);
			}
		}
*/

