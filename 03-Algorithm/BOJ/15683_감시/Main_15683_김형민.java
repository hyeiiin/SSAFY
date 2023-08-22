package boj;
import java.io.*;
import java.util.*;
public class Main_15683_김형민 {

    static int N, M;
    static boolean[][] map;
    static int minAns = Integer.MAX_VALUE;
    static ArrayList<CCTV> cctves = new ArrayList<>();
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num==0){
                    map[i][j] = true;
                }
                if (1<=num&&num<=5){
                    cctves.add(new CCTV(i,j,num));
                    map[i][j] = true;
                }
            }
        }
        perm(4,cctves.size(),0,new int[cctves.size()]);
        System.out.println(minAns);

    }
    
    // 맵에서 사각지대를 개수를 가져오는 메소드
    static int getBlindSpot(boolean[][] copyMap){
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j]){
                    ans++;
                }
            }
        }
        return ans;
    }

    //cctv가 보이는 곳은 false로 바꿔주는 메소드
    static void runningCCTV(boolean[][] copyMap, CCTV cctv, int[][] moveArr){
        for (int[] mv : moveArr) {
            int dx = mv[0] + cctv.x;
            int dy = mv[1] + cctv.y;
            while (dx>=0&&dx<N&&dy>=0&&dy<M&&map[dx][dy]){
                copyMap[dx][dy] = false;
                dx += mv[0];
                dy += mv[1];
            }
        }
    }
    
    //배열 깊은 복사해주는 메소드
    static boolean[][] getCopyMap(){
        boolean[][] result = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = map[i][j];
            }
        }
        //단 cctv 좌표도 false 처리해서 보낸다.
        for (CCTV cctv : cctves) {
            result[cctv.x][cctv.y] = false;
        }
        return result;
    }
    //cctv 인덱스 별 cctv회전 번호의 모든 경우의 수
    // 즉, n은 항상 4 고정이며 중복순열이다.
    static void perm(int n, int r, int cnt, int[] result){
        if (cnt==r){
            // 기존 맵 깊은 복사
            boolean[][] copyMap = getCopyMap();
            for (int i = 0; i < r; i++) {
                CCTV cctv = cctves.get(i);//cctv를 가져와서
                int[][] moveArr = getMoveArr(cctv.num, result[i]);//해당하는 회전 경우의 수의 이동 좌표를 가져온다.
                runningCCTV(copyMap, cctv, moveArr);//cctv가 보이는 곳 체크
            }
            minAns = Math.min(minAns,getBlindSpot(copyMap));// 개수를 센 뒤 최저값 이면 저장
            return;
        }
        for (int i = 0; i < n; i++) {
            result[cnt] = i;
            perm(n,r,cnt+1,result);
        }
    }
    //90도 방향 4번 할수있음 -> 0번 회전,1번 회전,2번 회전,3번 회전
    static int[][] getMoveArr(int num, int rotate){//cctv 번호와 회전 번호 를 입력하면 회전한 이동 배열 리턴
        int [][] result = null;
        int[][] arr = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
        

        if (num==1){
            result = new int[][] {arr[rotate%4]};//{1,0}, {0,-1}, {-1,0}
        }
        if (num==2){
            result = new int[][] {arr[rotate%4],arr[(rotate+2)%4]};//{{1,0},{-1,0}}
        }
        if (num==3){
            result = new int[][] {arr[rotate%4],arr[(rotate+3)%4]};
        }
        if (num==4){
            result = new int[][] {arr[rotate%4],arr[(rotate+2)%4],arr[(rotate+3)%4]};
        }
        if (num==5){
            result = new int[][] {arr[0],arr[1],arr[2],arr[3]};
        }

        return result;
    }
}

class CCTV{
    int x;
    int y;
    int num;

    public CCTV(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
}