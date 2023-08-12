package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15686_탁하윤 {
    static int M, city[][], chicken[][], house[][], result;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시 크기 N
        M = Integer.parseInt(st.nextToken());   // 폐업시키지 않을 치킨집 최대 N
        city = new int[N][N];   // city 배열
        int chic = 0;   // 치킨집 개수
        int home = 0;   // 집 개수
        result = Integer.MAX_VALUE; // 도시의 치킨 거리 최솟값
        for(int i=0; i<N; i++) {    // 도시 배열 초기화
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j]==2) { // 치킨집 개수
                    chic++;
                } else if(city[i][j] == 1){ // 집 개수
                    home++;
                }
            }
        }

        chicken = new int[chic][2]; // 치킨집들 개수 크기의 좌표 배열
        house = new int[home][2];   // 집들 개수 크기의 좌표 배열
        visited = new boolean[chic];    // 방문 처리(치킨집들 개수만큼)
        int idx = 0;    // 치킨집들 index
        int hidx = 0;   // 집들 index
        for(int i=0; i<N; i++){ // 치킨집, 집 개수 만큼
            for(int j=0; j<N; j++){
                if(city[i][j]==2) {
                    // 치킨집 좌표 넣기
                    chicken[idx][0] = i;
                    chicken[idx][1] = j;
                    idx++;
                } else if(city[i][j] == 1){
                    // 집 좌표 넣기
                    house[hidx][0] = i;
                    house[hidx][1] = j;
                    hidx++;
                }
            }
        }

        perm(0, 0); // 재귀 호출 부분
        System.out.println(result);
    }

    public static void perm(int cnt, int start){    // cnt: 현재 뽑은 수 start: 시작할 수
        if(cnt == M){   // M개 뽑은 수 distance() 호출하여 최소 거리값 구하기
            distance();
            return;
        }
        for(int i=start; i<chicken.length; i++){    // 치킨집 수 만큼 돌면서 뽑기
            if(visited[i]) continue;
            visited[i] = true;
            perm(cnt+1, i+1);   // 시간초과 -> i+1로 가지치기
            visited[i] = false;
        }
    }

    public static void distance(){
        int disMin = 0;    // 거리 합
        for(int i=0; i<house.length; i++){  // 집들마다 각 치킨집 돌면서 최솟값 구하기
            int min = Integer.MAX_VALUE; // 집과 치킨집들 거리 중 최솟값
            for(int j=0; j<chicken.length; j++){
                if(!visited[j]) continue;
                if(min>Math.abs(house[i][0] - chicken[j][0]) + Math.abs(house[i][1] - chicken[j][1])){
                    min = Math.abs(house[i][0] - chicken[j][0]) + Math.abs(house[i][1] - chicken[j][1]);
                }
            }
            disMin += min;  // 한 집에서 치킨집들 중 거리 최솟값 구하기
        }
        result = Math.min(result, disMin);  // 치킨 거리 최솟값
    }

}