
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {
	// 집/ 치킨집 정보 저장, getDis() 거리 계산 메소드 포함
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r =  r;
			this.c =  c;
		}
		public int getDis(Node n) {
			return Math.abs(this.r-n.r)+Math.abs(this.c-n.c);
		}
	}
	static int N,M;	//N : 도시의 행열 크기, M: 치킨집 최대 개수
	static int[][] map;	 //지도 정보
	static ArrayList<Node> house = new ArrayList<>(); //집 정보
	static ArrayList<Node> chicken = new ArrayList<>();	//치킨집 정보
	static ArrayList<Node> selectedChicken = new ArrayList<>();	 //선택된 치킨집 정보
	
	static int ans;	//최소 도시 치킨거리를 저장할 변수
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		
		map = new int[N][N]; //맵생성
		ans=Integer.MAX_VALUE;	//최소 도시 치킨거리 초기화
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	
				
				if(map[i][j]==1) house.add(new Node(i,j)); //집정보 받아두기
				else if(map[i][j]==2) chicken.add(new Node(i,j)); //치킨 정보 받아두기
			}
		}
		combination(0, 0);
		
		System.out.println(ans);
	}
	
	/**
	 * 
	 * @param i : 조합 시작 idx
	 * @param cnt : 조합 개수
	 */
	private static void combination(int i, int cnt) {
		if(M == cnt) { //선택된 치킨집의 개수가 M이 되었을때 --> M개를 선택하는 조합 와성!
			int dis = calcCityChick(); //도시의 치킨거리 계산
			ans = Math.min(ans, dis); //최소값을 갱신
			return;
		}
		
		for(int j=i;j<chicken.size();j++) {
			selectedChicken.add(chicken.get(j)); //조합한 리스트에 추가
			combination(j+1,cnt+1); //다음 조합 만들러 gogo
			selectedChicken.remove(selectedChicken.size()-1); //조합 리스트에 빼주기
		}
	}
	
	//현재 선택된 조합의 치킨집들의 도시 치킨거리 구하기
	//도시에 포함된 집들의 최소 치킨거리 모두 더하기
	private static int calcCityChick() {
		
		int total = 0; 
		for(Node h : house) { //집 가져오기
			int minDis=Integer.MAX_VALUE;	
			
			for(Node c : selectedChicken) {//선택된 집과 조합된 M개의 치킨집 사이의 최소 치킨거리 찾기
				minDis = Math.min(minDis, c.getDis(h));
			}
			total += minDis;
		}
		return total;
	}
}
