package ssafyStudy.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2178_이세은 {
    private static int[][] arr; //텀색하는 미로
    private static int n, m; //미로 행렬 값 및 도착 위치

    //상하좌우 이동 행렬 델타 함수
    private static int[] moveI = {-1, 1, 0, 0};
    private static int[] moveJ = {0, 0, -1, 1};
    
    private static boolean[][] visited; //방문처리 함수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //행
        m = Integer.parseInt(st.nextToken()); //열
        arr = new int[n][m]; //미로 2차원 배열 초기화
        visited = new boolean[n][m]; //방문처리 함수 초기화

        for(int i=0 ;i<n; i++){
            String[] str = br.readLine().split(""); //붙여서 출력된 한 행 받기
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(str[j]); //문자열 배열에 저장한 하나의 문자열 씩 int형으로 파싱해서 int형 2차원 배열에 저장
            }
        }

        bfs(0,0);
        System.out.println(arr[n-1][m-1]); //마지막으로 크기가 업데이트 된 정답 좌표의 값이 최소거리
    }
    
    //(0,0)부터 시작해서 (n,m)까지의 최소거리 찾는 메서드, 갈 수 있는 좌표의 값 1을 더하는 cnt를 매개변수로 가진다
    private static void bfs(int i, int j){
        
        Queue<int[]> q = new LinkedList<>(); //탐색하는 곳(좌표)을 순차적으로 넣어줄 큐 생성
        q.add(new int[]{i, j});
        //현재 좌표 방문 처리
        visited[i][j] = true;

        //큐가 빌 때까지 들어간 좌표 탐색
        while(!q.isEmpty()){
            int[] loc = q.poll(); //좌표 poll
            int r = loc[0]; //행 좌표
            int c = loc[1]; //열 좌표
            for(int k=0; k<4; k++){

                //행렬 업데이트
                int newR = r+moveI[k];
                int newC = c+moveJ[k];
                //범위 내이고 방문하지 않았으며 갈 수 있는 곳이라면 탐색 진행
                if(newR>=0 && newC>=0 && newR<n && newC<m && !visited[newR][newC] && arr[newR][newC] != 0){
                    visited[newR][newC] = true; //방문처리
                    arr[newR][newC] = arr[r][c] + 1; //갈수 있는 좌표는 1이므로 좌표 값 더해줘서 거리 표현
                    q.add(new int[]{newR, newC}); //다음 탐색
                    
                }
            }


        }
        
    }
}


