package baekjun;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_김도현 {
    
    static int N, M, lab[][],temp[][],maxResult;
    static boolean check[][];
    static int[] dx = {-1,0,1,0}; // 좌 상 우 하
    static int[] dy = {0,1,0,-1};
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        lab = new int[N][M];
        check = new boolean[N][M];
        
        
        // 처음 주어진 lab의 결과를 담는다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                
            }
        }
        
        maxResult = Integer.MIN_VALUE;
        
        permutation(0);
        
        System.out.println(maxResult);

    }
    
    public static void permutation(int depth) {
        if(depth==3) {
            virus();            // 벽이 다 세워졌다면 바이러스를 퍼뜨린다
            return;
        }
        
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(lab[i][j]==0) { // 벽을 놓을 수 있다면 
                    lab[i][j] = 1; // 벽을 놓고
                    permutation(depth+1); //카운터를 올린다
                    lab[i][j] = 0;
                }
            }
        }
    }
    
    
    


    private static void virus() {
    	Queue<Node> q = new LinkedList<>();
    	
    	temp = new int[N][M];
    	
    	for (int i = 0; i < N; i++) {
			temp[i] = lab[i].clone();
		}
    	
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(temp[i][j]==2) {
					q.add(new Node(i, j));
				}
			}
		}
    	
    	while(!q.isEmpty()) {
    		Node curr = q.poll();
    		int x = curr.x;
    		int y = curr.y;
    		
    		for (int dir = 0; dir < 4; dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				if(nx>=0 && ny>=0 && nx<N && ny < M && temp[nx][ny]==0) {
					temp[nx][ny] = 2;
					q.add(new Node(nx, ny));
				}
			}
    		
   
    	}
    	
		cal(temp);
	}

	private static void cal(int[][] map) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0) {
                    sum+=1;
                }
            }
        }
        maxResult = Math.max(maxResult, sum);
    }
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
    
}
