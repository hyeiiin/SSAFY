import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 입력받은 관계에서 A-B-C-D-E관계가 있는지 확인하는 문제
 */
public class Main_13023_ABCDE {

	static boolean[] relathionship;
	static List<Integer>[] list; //각 List타입을 받을 배열
	static boolean check;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N]; //ArrayList타입의 배열 생성
		//각 배열index에 리스트 생성해줘야 쓸 수 있기 때문에 초기화 작업(안하면 null)
		for(int i=0; i<N;i++) { //각 참여자별 친구 목록을 받을 ArrayList 생성
			list[i]= new ArrayList<>();
		}
	
		//친구 관계 정보 저장하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int me = Integer.parseInt(st.nextToken());
			int you = Integer.parseInt(st.nextToken());
			//친구 관계는 양방향이므로 2명 모두 넣어주기
			list[me].add(you);
			list[you].add(me);
		}
		
		check = false; //ABCDE 관계가 있었는지 확인하기 위한 flag
		//각 참여자별로 친구 관계 연결 확인하기
		for (int i = 0; i < N; i++) {
			relathionship = new boolean[N];
			dfs(i,1); //확인을 시작하는 사람부터 5명 이어져 있는지 확인하러가기
			if(check) { //flag 확인해서 관계 확인 되면 종료
				System.out.println(1);
				return;
			}
		}
		//여기까지 확인 안되고 내려오면 없는것.
		System.out.println(0);
	}
	
	/**
	 * 시작 위치부터 타고가면서 이어진 친구가 있는지 확인하기
	 * depth가 몇명까지 이어져 있는지 확인하기
	 * @param me
	 * @param depth
	 */
	static void dfs(int me, int depth) {
		if(depth==5) { //5명까지 타고 왔으면 끝! 더 이어져 있는지 여부 관심 없음.
			check = true;
			return;
		}
		relathionship[me] = true; //사이클 만들지 않도록 방문처리
		for(int you: list[me]){ //내 친구인 목록에서 타고 갈 수 있는 사람 한명씩 꺼내오기
			if(!relathionship[you]) { //방문여부 확인
				dfs(you,depth+1); //처음보는 친구면 다음으로가기
			}
		}
		relathionship[me] = false;//되돌아오면 방문해제
	}

}