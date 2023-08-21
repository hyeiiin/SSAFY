package algorithm;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2522_������ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] inDegree = new int[N+1]; // ���� ���� �迭
		// ���� ����Ʈ
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to); // ���� ����Ʈ �� ����
			inDegree[to]++; // ���� ���� �� ����
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		// ���� ������ 0 �� �� ť�� �ֱ�
		for(int i=1; i<inDegree.length; i++) {
			if(inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int next = queue.poll();
			
			sb.append(next + " ");
			
			for(int i=0; i < graph[next].size(); i++) {
				int temp = graph[next].get(i); // �ش� �л� �ڿ� �����ϴ� �л� ����
				inDegree[temp]--; // ���� ���� ����
				if(inDegree[temp] == 0) {
					queue.offer(temp);
				}		
			}
		}
		
		System.out.println(sb);


	}

}
