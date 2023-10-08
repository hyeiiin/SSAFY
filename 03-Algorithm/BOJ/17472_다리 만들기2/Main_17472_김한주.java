
import java.io.*;
import java.util.*;

//다른 패키지에서 Node 클래스가 중복되어서 Node2로 선언함 
class Node2 implements Comparable<Node2>{
	int vertex, dist;
	
	public Node2(int vertex, int dist) {
		this.vertex = vertex; 
		this.dist = dist; 
	}
	
	public String toString() {
		return this.vertex+":"+this.dist; 
	}
	//프림 알고리즘 사용을 위해 정렬 기준 정의 
	@Override 
	public int compareTo(Node2 o) {
		return this.dist - o.dist; 
	}
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int n; 
    static int m; 
    static int[][] map;
    static int[][] nodeNum; 
    
    static int N; 
    static ArrayList<Node2>[] graph; 
    static boolean[] isVisited;
    static int[] dist; 
    
    
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    
    static void mapToGraph() {
        //인접 리스트 사용 
    	graph = new ArrayList[N+1];
    	
    	for(int i=0; i<=N; i++) {
    		graph[i] = new ArrayList<>(); 
    	}
    	
    	
    	//전체 맵 순회하면서 값이 1인(=육지인 부분)을 노드로 삼아 
        //4방향을 탐색하며 최초의 대륙이 나올 때까지 탐색 
        //최초의 대륙이 나온다면 해당 노드끼리 간선을 생성하여 인접 리스트에 넣어준다.
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<m; y++) {
    			if(map[x][y]==1) {
    				
                    //4방 탐색 
    				for(int dir=0; dir<4; dir++) {
    					int length = 1; 
    					
    					while(true) {
    						int nX = x + dx[dir]*length;
    						int nY = y + dy[dir]*length; 
    						
    						if(!inRange(nX, nY)) break; 
    						
    						if(map[nX][nY]==1) {
                                //거리가 2로 연결될 경우 무시한다 (문제에서 다리를 만들 수 없는 경우라고 명시함)
    							if(length==2)break;
    							graph[nodeNum[x][y]].add(new Node2(nodeNum[nX][nY], length-1));
    							break;
    						}
    						
    						length++; 
    					}	
    				}
    			}
    		}
    	}
    }
    
    static boolean inRange(int x, int y) {
    	return x>=0&&y>=0&&x<n&&y<m; 
    }
    

    //지도 정보 입력받기 
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine());
    	n = Integer.valueOf(tokens.nextToken());
    	m = Integer.valueOf(tokens.nextToken()); 
    	
    	map = new int[n][m]; 
    	nodeNum = new int[n][m];
    	N = 1;
    	for(int x=0; x<n; x++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		for(int y=0; y<m; y++) {
    			map[x][y] = Integer.valueOf(tokens.nextToken()); 
    			if(map[x][y]==1) {
    				nodeNum[x][y] = N;
    				N++;
    			}
    		}
    	}
    }
    
    
    public static void main(String[] args)throws IOException{
    	init(); 
    	
    	
    	
    	mapToGraph(); 
    	//프림 알고리즘 사용을 위한 자료구조 세팅 
    	dist = new int[N+1];
    	isVisited = new boolean[N+1]; 
    	PriorityQueue<Node2> pq = new PriorityQueue<>(); 
    	Arrays.fill(dist, (int)1e9); 
    	

        //시작점을 노드 1로 잡음 
    	dist[1] = 0;
    	pq.add(new Node2(1, 0)); 
    	
    	int result = 0; 
    	

        //프림 알고리즘으로 MST 찾기 
    	while(!pq.isEmpty()) {
    		Node2 cur = pq.poll();
    		
    		if(isVisited[cur.vertex])continue; 
    		isVisited[cur.vertex] = true; 
    		result += cur.dist; 
    		
    		for(Node2 target : graph[cur.vertex]) {
    			if(dist[target.vertex]>target.dist) {
    				dist[target.vertex] = target.dist; 
    				pq.add(new Node2(target.vertex, target.dist));
    			}
    		}
    		
    	}
    	//모든 노드를 방문했을 경우에만 정상 출력 
    	if(isAllVisited()) {
    		System.out.println(result);
    	}else {
    		System.out.println(-1);
    	}
    	
    	
    }
    //모든 노드를 방문했는지 확인 
    static boolean isAllVisited() {
    	for(int node=1; node<=N-1; node++) {
    		if(!isVisited[node])return false;
    	}
    	
    	return true;
    }
    
    
    
    //디버깅용 출력메서드 
    static void print(int[][] arr) {
    	for(int[]ar: arr) {
    		for(int a: ar) {
    			System.out.print(a+" ");
    		}System.out.println();
    	}
    }
    
    
    
    
}
