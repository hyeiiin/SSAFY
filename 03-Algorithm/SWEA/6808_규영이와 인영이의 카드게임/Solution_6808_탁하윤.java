package com.ssafy.hw.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_탁하윤 {
    static int gCard[], iCard[], WIN, LOSE;
    static boolean check[], visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            check = new boolean[19];    // 인영이 카드 넣기 위한 boolean타입 배열
            visited = new boolean[9];   // 라운드 visit 처리
            gCard = new int[9]; // 규영이 카드
            iCard = new int[9]; // 인영이 카드
            WIN = 0;    // 규영이 이긴 수
            LOSE = 0;   // 규영이 진 수

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<9; i++) {    // 규영이 카드 넣기
                gCard[i] = Integer.parseInt(st.nextToken());
                check[gCard[i]] = true; // 규영이가 넣은 카드 true 처리
            }

            int idx = 0;    // 인영이 카드 index
            for(int i=1; i<=18; i++) {  // 인영이 카드 넣기
                if(!check[i]){  // 규영이가 넣은 카드가 아니라면
                    iCard[idx++] = i;   // 인영이 카드 넣기
                }
            }
            perm(0, 0, 0);  // 재귀 호출 부분
            System.out.printf("#%d %d %d\n", tc, WIN, LOSE);
        }
    }

    static void perm(int cnt, int win, int los){    // cnt: 현재 라운드 win: 규영이 점수 los: 인영이 점수
        if(cnt==9){ // 9 라운드라면
            if(win>los) WIN++;  // 이긴 경우
            else if(win<los) LOSE++;    // 진 경우
            return;
        }
        for(int i=0; i<9; i++){ // 9라운드 돌기
            if(visited[i]) continue;    // 이미 뽑은 카드라면 다시
            visited[i] = true;  // 카드 선택하기
            if(gCard[cnt] > iCard[i]){  // 규영이가 이긴 경우
                perm(cnt+1, win+gCard[cnt]+iCard[i], los);
            } else {    // 인영이가 이긴 경우
                perm(cnt+1, win, los+gCard[cnt]+iCard[i]);
            }
            visited[i] = false;
        }
    }

}