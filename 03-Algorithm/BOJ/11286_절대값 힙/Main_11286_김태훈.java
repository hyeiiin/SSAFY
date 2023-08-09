package baekjoon;
import java.util.*;
import java.io.*;

public class Main_11286_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//입력 받을때 정렬해서 입력받게
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				//절댓값끼리 비교
				if(Math.abs(o1)>Math.abs(o2)) return 1;
				else if(Math.abs(o1)<Math.abs(o2)) return -1;
				//절댓값이 같으면 작은수가 앞에
				else {
					if(o1>o2) return 1;
					else return -1;
				}
			}
		});
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x!=0) {	//0아니면 넣고
				pq.add(x);
			}
			else if(x==0 && !(pq.isEmpty())){	//0이고 안에 숫자 잇으면 빼고
				sb.append(pq.remove()).append("\n");
			}
			else if(x==0 && pq.isEmpty()){		//0이고 안에 숫자도 없으면 그냥 0출력
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}

}
