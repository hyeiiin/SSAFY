import java.io.*;
import java.util.*;

public class Main_2252_�̼��� {
	
	private static int[] inDegree; //�� �л��� ���ϴ� �������� ���� �迭
	private static Student[] adjList; //�� �л� �� ������ ������ �迭
	private static int n, m;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken()); //�л� ��
		m = Integer.parseInt(st.nextToken()); //�� Ƚ��
		
		adjList = new Student[n+1];
		inDegree = new int[n+1]; //�л� �� ũ���� �������� �迭 ����
		for(int i=0; i<m; ++i) {
			st = new StringTokenizer(br.readLine());
			int smaller = Integer.parseInt(st.nextToken());
			int taller = Integer.parseInt(st.nextToken());
			adjList[smaller] = new Student(taller, adjList[smaller]);
			++inDegree[taller];
		}
		
		List<Integer> list = topology();
		if(list.size()==n) { //��� �л� ���� �Ϸ�
			for (Integer integer : list) {
				sb.append(integer).append(" ");
			}
			System.out.println(sb);
		}
	}
	private static List<Integer> topology(){
		List<Integer> list = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=n; ++i) { //��� �л� ����ŭ
			if(inDegree[i] == 0) //���������� 0�̶��, ��� �л��� ���ؼ� �ڽ��� ���� ����
			q.add(i); //���� �л��� ���� ��´�
		}
		while(!q.isEmpty()) {
			int current = q.poll(); //Ű�� ū �л����� poll�ȴ�
			list.add(current);
			
			for(Student stu = adjList[current]; stu != null; stu = stu.comp) {
				if(--inDegree[stu.no]==0) //�������� 0�� �Ǿ��ٸ� ť�� �־��ִ�
					q.add(stu.no);
			}
		}
		return list;
	}
	
	static class Student{
		int no;//�л� ��ȣ
		Student comp; //�� ���
		public Student(int no, Student comp) {
			this.no = no;
			this.comp = comp;
		}
		
	}

}
