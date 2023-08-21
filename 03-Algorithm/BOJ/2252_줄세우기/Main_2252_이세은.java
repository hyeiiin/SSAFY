import java.io.*;
import java.util.*;

public class Main_2252_이세은 {
	
	private static int[] inDegree; //각 학생을 향하는 진입차수 저장 배열
	private static Student[] adjList; //각 학생 비교 정보를 가지는 배열
	private static int n, m;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken()); //학생 수
		m = Integer.parseInt(st.nextToken()); //비교 횟수
		
		adjList = new Student[n+1];
		inDegree = new int[n+1]; //학생 수 크기의 진입차수 배열 생성
		for(int i=0; i<m; ++i) {
			st = new StringTokenizer(br.readLine());
			int smaller = Integer.parseInt(st.nextToken());
			int taller = Integer.parseInt(st.nextToken());
			adjList[smaller] = new Student(taller, adjList[smaller]);
			++inDegree[taller];
		}
		
		List<Integer> list = topology();
		if(list.size()==n) { //모든 학생 정렬 완료
			for (Integer integer : list) {
				sb.append(integer).append(" ");
			}
			System.out.println(sb);
		}
	}
	private static List<Integer> topology(){
		List<Integer> list = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=n; ++i) { //모든 학생 수만큼
			if(inDegree[i] == 0) //진입차수가 0이라면, 모든 학생에 대해서 자신이 가장 작음
			q.add(i); //작은 학생을 먼저 담는다
		}
		while(!q.isEmpty()) {
			int current = q.poll(); //키가 큰 학생부터 poll된다
			list.add(current);
			
			for(Student stu = adjList[current]; stu != null; stu = stu.comp) {
				if(--inDegree[stu.no]==0) //집입차수 0이 되었다면 큐에 넣어주다
					q.add(stu.no);
			}
		}
		return list;
	}
	
	static class Student{
		int no;//학생 번호
		Student comp; //비교 대상
		public Student(int no, Student comp) {
			this.no = no;
			this.comp = comp;
		}
		
	}

}
