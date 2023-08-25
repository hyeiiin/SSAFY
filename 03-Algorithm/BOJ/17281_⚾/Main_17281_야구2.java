// ~

import java.io.*;
import java.util.*;

public class Main_17281_야구2 {

    static int N, bestGameScore;
    static final int BASEBALL = 9;
    static int[] orderOfHitter;
    static int[][] scoreOfHitter;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        scoreOfHitter = new int[N][BASEBALL]; //각 이닝의 타자들의 점수
        orderOfHitter = new int[BASEBALL]; //타순에 설 타자 순서
        selected = new boolean[BASEBALL]; //타순에 타자 배치 여부 확인용

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < BASEBALL; j++) {
                scoreOfHitter[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        orderOfHitter[3] = (1 - 1); //4번타자 셋팅
        selected[0] = true;
        setOrderOfHitter(0);
        System.out.println(bestGameScore);
    }

    /**
     * 순열로 타자 배치
     * @param depth
     */
    static void setOrderOfHitter(int depth) {
        if (depth == BASEBALL) { //9명 순서 다 배치 했으면, 게임하기
            play();
            return;
        }
        if (depth == 3) {
            setOrderOfHitter(depth + 1); //4번타자는 이미 셋팅되어 있으니까 다음으로
        }else {
        for (int i = 0; i < BASEBALL; i++) {
        	//순열로 타자배치
            if (!selected[i]) {
                orderOfHitter[depth] = i;
                selected[i] = true;
                setOrderOfHitter(depth + 1);
                selected[i] = false;
            }
        }
        }
    }

    /**
     * 게임 진행하기
     */
    static void play() {
        int outCount = 0, gameScore = 0, hitterIdx = 0;
        boolean[] runner; 
        for (int i = 0; i < N; i++) {//각 이닝별 진행
            runner = new boolean[3]; //진루 확인을 위한 배열
            while (outCount < 3) { //아웃이 3번 발생할 때 까지 계속 이닝은 계속 됨
                hitterIdx %= 9; //0~9까지 순서대로 돌기
                int hit = scoreOfHitter[i][orderOfHitter[hitterIdx++]];
                if (hit == 0) {//아웃인 경우
                    outCount++; 
                } else if (hit == 4) { //홈런인 경우
                    for (int j = runner.length - 1; j >= 0; j--) {
                        if (runner[j] == true) { //모든 나가있는 타자 들어오기
                            gameScore++;
                            runner[j] = false;
                        }
                    }
                    gameScore++; //타자도 홈으로 바로 들어옴
                } else {
                    for (int j = runner.length - 1; j >= 0; j--) {//3~0까지
                        if (runner[j] && (j + hit) >= runner.length) {//진루 되어 있고, 현재 진루 번호+안타 진루수>= 3 홈으로 들어오는 경우
                            gameScore++;//점수 추가
                            runner[j] = false;//진루 된 곳 비워주기
                        } else if (runner[j]) { //아니면 진루 시키기
                            runner[j + hit] = true;
                            runner[j] = false;
                        }
                    }
                    runner[hit - 1] = true;//타자 진루
                }
            }
            outCount = 0;
        }
        //점수 갱신
        bestGameScore = Math.max(bestGameScore, gameScore);
    }
}