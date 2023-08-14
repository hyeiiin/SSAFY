package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686_전상혁 {
	// 집과 치킨집 정보를 저장하고 , getDis() 메서드로 거리 계산
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public int getDis(Node n) {
			//거리계산 -> 행-행의 절댓값 + 열-열의 절댓값
			return Math.abs(this.r-n.r) + Math.abs(this.c- n.c);
		}
	}
	
	static int map[][];
	static int N,M,res;
	static ArrayList<Node> house = new ArrayList<>();
	static ArrayList<Node> chicken = new ArrayList<>();
	static ArrayList<Node> selectedChicken = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//인지해야 할 것
		//거리계산 -> 행-행의 절댓값 + 열-열의 절댓값
		//0=빈칸, 1=집, 2=치킨집
		//치킨거리=집-치킨집 사이 거리
		//도시의 치킨거리 = 각 집의 치킨 거리의 합
		//도시의 치킨 거리의 최솟값 출력
		
		//배열 크기
		N = Integer.parseInt(st.nextToken());
		//가장 수익을 많이 낼 수 있는 치킨집의 개수는 최대 M개
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		res = Integer.MAX_VALUE; //도시의 치킨거리 최솟값을 초기화
		
		//map 생성
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//1=집, 2=치킨집
				if (map[i][j]==1) house.add(new Node(i,j)); //집정보
				else if (map[i][j]==2) chicken.add(new Node(i, j)); //치킨 정보 
			}
		}
		//조합해보기
		combination(0, 0);
		
		System.out.println(res);
		
	}
	//i: 조합시작 index, cnt: 조합 개수
	private static void combination(int i, int cnt) {
		if (M==cnt) {
			//도시의 치킨거리 계산
			int dis = calcCityChick();
			//도시의 치킨거리는 최솟값을 갱신해야 함
			res = Math.min(res, dis); 
			return;
		}
		
		for (int j = i; j < chicken.size(); j++) {
			selectedChicken.add(chicken.get(j)); //조합한 리스트에 추가
			combination(j+1, cnt+1); //다음 조합 만들러 출발
			selectedChicken.remove(selectedChicken.size()-1); //조합 리스트에 빼주기
		}

	}
	
	private static int calcCityChick() {
		//현재 선택된 조합의 치킨집들의 도시 치킨거리 계산
		//도시에 포함된 집들의 최소 치킨거리 모두 더하기
		int total = 0;
		for (Node h : house) {
			int minDis = Integer.MAX_VALUE;
			
			//선택된 집과 조합된 M개의 치킨집 사이의 최소 치킨거리 찾기
			for (Node c : selectedChicken) {
				minDis = Math.min(minDis, c.getDis(h));
			}
			//집들의 최소 치킨거리를 모두 더해 total에 저장!
			total += minDis;
		}
		return total;
	}
	
	
}
