package ssafyPractice;

import java.io.*;
import java.util.*;

import java.io.IOException;

public class Main_6987_이세은 {

    private static StringBuilder sb;
    private static int[][] verse, teamArr;
    private static boolean end = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        verse = new int[15][2];
        sb = new StringBuilder();

        // 각 팀이 경기하는 경우의 수 구해놓기
        // 1-2 3 4 5 6, 2-3 4 5 6, ..
        int k = 0;
        for (int i = 1; i < 6; i++) {
            for (int j = i + 1; j < 7; j++) {
                verse[k][0] = i;
                verse[k][1] = j;
                k++;
            }
        }

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean flag = true;
            teamArr = new int[7][3]; // 각 팀의 승무패 저장 초기화
            for (int j = 1; j <= 6; j++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());
                teamArr[j][0] = win;
                teamArr[j][1] = draw;
                teamArr[j][2] = lose;

                // 한 팀 당 5번의 경기 필요
                if(win + draw + lose != 5) {
                    flag = false;
                    break;
                }
            }

            if (flag) { //처음부터 6팀의 총 경기 수 맞지 않음
                dfs(0); // 총 15번 경기 진행해야함
                if (end)
                    sb.append("1").append(" ");
                else
                    sb.append("0").append(" ");
            }
            else{
                sb.append("0").append(" ");
            }
            end = false; //테스트케이스들 처리위한 초기화
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int matchCnt) {

        if (end) // 결과값 저장되었다면 뒤에 이어지는 재귀 끝내기
            return;

        // 경기 15번 진행되었다면 모든 스코어가 0이 되었는지 확인, 되었다면 가능한 결과
        if (matchCnt == 15) {
            if (isAvailable())
                end = true;
            return;
        }

        // 모든 경기의 경우의 수만큼 조합
        // 경우의 수에 따라 우리팀과 상대팀 정하기
        int home = verse[matchCnt][0];
        int away = verse[matchCnt][1];
        // 우리팀 승-상대팀 패
        if (teamArr[home][0] > 0 && teamArr[away][2] > 0) {
            teamArr[home][0]--;
            teamArr[away][2]--;
            dfs(matchCnt + 1);
            teamArr[home][0]++;
            teamArr[away][2]++;
        }
        // 우리팀 무 - 상대팀 무
        if (teamArr[home][1] > 0 && teamArr[away][1] > 0) {
            teamArr[home][1]--;
            teamArr[away][1]--;
            dfs(matchCnt + 1);
            teamArr[home][1]++;
            teamArr[away][1]++;
        }
        // 우리팀 패 - 상대팀 승
        if (teamArr[home][2] > 0 && teamArr[away][0] > 0) {
            teamArr[home][2]--;
            teamArr[away][0]--;
            dfs(matchCnt + 1);
            teamArr[home][2]++;
            teamArr[away][0]++;
        }
    }

    public static boolean isAvailable() {
        // 각 승무패 횟수 0되었는지 확인
        for (int i = 1; i <= 6; i++) {
            if (teamArr[i][0] != 0 || teamArr[i][1] != 0 || teamArr[i][2] != 0) // 0으로 된 것 없으면 불가능한 경기
                return false;
        }
        return true;
    }
}