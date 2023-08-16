package boj;

import java.io.*;
import java.util.*;

public class Main_6987_김형민 {
    static int MAX_NATION_GAME = 5;
    static int[][] data, matches;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        // 경기 결과 케이스
        for (int t = 0; t < 4; t++) {
            boolean isPossible = true;

            //경기 결과를 저장할 데이터
            data = new int[6][3];
            matches = new int[15][2];//경기 별 팀 저장
            int match = 0;
            //모든 경우의 수(매치 라인업)을 저장한다.
            for (int teamOne = 0; teamOne < 5; teamOne++) {
                for (int teamTwo = teamOne+1; teamTwo < 6; teamTwo++) {
                    matches[match][0] = teamOne; // teamOne
                    matches[match][1] = teamTwo; // teamTwo
                    match++; // 다음경기
                }
            }
            st = new StringTokenizer(br.readLine());
            for (int nation = 0; nation < 6; nation++) {
                int sum = 0;
                for (int result = 0; result < 3; result++) {
                    data[nation][result] = Integer.parseInt(st.nextToken());
                    sum += data[nation][result];
                }
                if (sum!=MAX_NATION_GAME){
                    isPossible = false;
                    break;
                }
            }
            if (isPossible&&isPossibleGameResult(0)){
                sb.append(1);
            }else {
                sb.append(0);
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }

    static boolean isPossibleGameResult(int match){
        if (match==15){
            return true;
        }
        //이번경기에서 붙는 두 팀을 가져온다.
        int teamOne = matches[match][0];
        int teamTwo = matches[match][1];

        //teamOne이 이겼을때 처리
        if (data[teamOne][0] > 0 && data[teamTwo][2] > 0){
            data[teamOne][0]--;
            data[teamTwo][2]--;
            //다음 매치 경우의 수 탐색
            if (isPossibleGameResult(match+1)){
                return true;
            }
            //데이터 원복
            data[teamOne][0]++;
            data[teamTwo][2]++;
        }
        //무승부 처리
        if (data[teamOne][1] > 0 && data[teamTwo][1] > 0){
            data[teamOne][1]--;
            data[teamTwo][1]--;
            //다음 매치 경우의 수 탐색
            if (isPossibleGameResult(match+1)){
                return true;
            }
            //데이터 원복
            data[teamOne][1]++;
            data[teamTwo][1]++;
        }
        //teamOne이 졌을때
        if (data[teamOne][2] > 0 && data[teamTwo][0] > 0){
            data[teamOne][2]--;
            data[teamTwo][0]--;
            //다음 매치 경우의 수 탐색
            if (isPossibleGameResult(match+1)){
                return true;
            }
            //데이터 원복
            data[teamOne][2]++;
            data[teamTwo][0]++;
        }
        return false;
    }
}
