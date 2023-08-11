import java.io.*; 

import java.util.*; 

//2차원 배열을 이용하지 않음 
////이유
////1. 거리식 자체가 좌표 정보로만 표현할 수 있음
////2. 인접 격자의 정보를 쓰지 않음 

//좌표 정보 저장 클래스 
class Point{
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n; //~50 한변의 길이 
	static int m; //~13 고르는 치킨집 개수 
	
	//집의 개수 : ~2n 
	//전체 치킨집 개수 : m~13;  
	
	static final int CHICKEN =2;
	static final int BLANK = 0;
	static final int HOUSE = 1; 
	
	static ArrayList<Point> chickens;
	static ArrayList<Point> houses; 
	static Point[] selecteds; 
	
	//치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리 
	
	//도시의 치킨 거리 : 모든 집의 치킨 거리 합 
	
	
	//두 좌표가 주어졌을 때 거리 정보 반환 
	static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
	}
	
	static int totalDistance = Integer.MAX_VALUE; 
	static boolean[] isUsed; 
	
	//전체 치킨집 중 m개를 뽑는다. (중복 x, 조합)  
			// 집 리스트 순회 	
				//선택된 치킨집 순회 
					//각각의 치킨 거리 구하고 최소값 구함 
				//최소값을 총 거리에 더해준다.
			//총거리 최소값 확인 
		
		//시간 복잡도 13C7 (m개 뽑는 로직) * m * 2n 
		// 1716 * 13 * 100 = 2230800 ~= 200만 
	static void bt(int cur, int last) {
		if(cur==m) {
			int tmpTotalDistance = 0; 
			
			//각 집마다 모든 치킨집과의 거리를 비교하여 최소 거리를 구함 (완전 탐색) 
			for(Point house : houses) {
				int tmp = Integer.MAX_VALUE;
				
				for(Point chicken : selecteds) {
					tmp = Math.min(tmp, getDistance(house, chicken));
				}
				//구한 최소 거리를 현재 경우의 수의 전체 거리에 더해줌 
				tmpTotalDistance += tmp; 
			}
			
			//현재 경우의 수 전체거리가 전체 경우의 수 중에서 최소 거리인지 판단하기 
			totalDistance = Math.min(tmpTotalDistance, totalDistance); 
			return;
		}
		
		//전체 치킨집 중 m개를 중복없이 조합으로 뽑는 로직 
		for(int next=last+1; next<chickens.size(); next++) {
			if(!isUsed[next]) {
				isUsed[next] = true;
				selecteds[cur] = chickens.get(next); 
				bt(cur+1,next); 
				isUsed[next] = false; 
			}
		}
	}
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine());
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		selecteds = new Point[m]; 
		
		chickens = new ArrayList<>();
		houses = new ArrayList<>(); 
		//지도 정보 받기 
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=0; y<n; y++) {
				int data = Integer.valueOf(tokens.nextToken());
				//해당 데이터가 치킨집일 경우 치킨집 전체 리스트에 넣어준다. 
				if(data==CHICKEN) {
					chickens.add(new Point(x,y));
				}else if(data == HOUSE) {
					
				//해당 데이터가 집일 경우 집 전체리스트에 넣어준다. 
					houses.add(new Point(x,y));
				}
			}
		}
		
		//전체 치킨집 개수를 바탕으로 중복 체크 배열을 생성한다. 
		isUsed = new boolean[chickens.size()];
		
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		//전체 경우의 수를 탐색한다. 
		bt(0,-1); 
		//경우의 수 완전 탐색후 최소 거리를 반환 
		System.out.println(totalDistance); 
	}
	
}
	




