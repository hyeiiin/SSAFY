import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main_1600_말이되고픈원숭이{
    
    static int K, W, H;
    static int[][] board;
    static int min = Integer.MAX_VALUE;
    static int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2}; //말이 이동할 수 있는 8방향
    static int[] hdy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] dx = {0, 1, 0 ,-1}; // 원숭이가 이동할 수 있는 4방향
    static int[] dy = {1, 0, -1, 0};
    static boolean[][][] visited;
    
    public static class Monkey {
        int x; //원숭이 위치
        int y;
        int count; //이동 횟수
        int k; //말처럼 이동할 수 있는 횟수
        
        public Monkey(int x, int y, int count, int k) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.k = k;
        }
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner scan = new Scanner(System.in);
    
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); //격자판의 크기 
        H = Integer.parseInt(st.nextToken());
        
        board = new int[H][W];
        for(int i = 0; i < H; i++) {
        	st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        visited = new boolean[H][W][K + 1];//H*W크기의 방문체크 배열을 말로 이동할 수 있는 횟수만큼 만들기
        min = bfs(0, 0); //원숭이 출발 위치는 무조건(0,0)
        
        if(min == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(min);
    }
    
    /**
     * 원숭이가 (0,0)~(H-1,W-1)로 이동시키기
     * - 도착 못하는 경우도 있음
     * @param x 원숭이 위치
     * @param y
     * @return
     */
    public static int bfs(int x, int y) {
        Queue<Monkey> q = new LinkedList<>();
        //시작 위치의 원숭이 넣고 시작
        q.offer(new Monkey(x, y, 0, K)); 
        visited[x][y][K] = true; //방문처리
        
        while(!q.isEmpty()) { //도착 못해서 비거나 도착할 때 까지 반복
        	Monkey current = q.poll();
        	//도착지에 도착했다면? 이때가 최소 횟수임.
            if(current.x == H - 1 && current.y == W - 1) return current.count; 
            
            //원숭이 이동시키기
           //1. 원숭이 움직임으로 이동 - 상,하,좌,우
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < H && ny < W //경계를 벗어나지 않았는지 확인
                		&& !visited[nx][ny][current.k] //이미 더 빠르게 방문한적이 있는지 확인(말 이동 횟수 고려 필수!!)
                				&& board[nx][ny] == 0) { //장애물이 없으면 갈 수 있음
                    visited[nx][ny][current.k] = true;
                    //이동횟수 추가해서 다음으로 넘어가기
                    q.offer(new Monkey(nx, ny, current.count + 1, current.k));
                }
            }
            //2. 말의 움직임으로 이동 - 8방향(나이트 움직임)
            //말처럼 이동할 수 있는 횟수가 남아있으면
            if(current.k > 0) {
                for(int i = 0; i < 8; i++) { //움직이기
                    int nx = current.x + hdx[i];
                    int ny = current.y + hdy[i];
                    if(nx >= 0 && ny >= 0 && nx < H && ny < W //경계를 넘지 않았는지
                    		&& !visited[nx][ny][current.k - 1] //말 이동횟수 고려하여 방문한적 있는지 확인
                    		&& board[nx][ny] == 0) { //장애물이 없으면 
                    	//말 이동 횟수 한번 썼기 때문에 방문처리
                        visited[nx][ny][current.k - 1] = true;
                        //이동 횟수 추가, 말 이동 횟수 차감
                        q.offer(new Monkey(nx, ny, current.count + 1, current.k - 1));
                    }
                }
            }
        }
        //다 돌고 최솟값 리턴
        return min;
    }
    

}