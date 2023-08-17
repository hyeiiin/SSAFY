import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//총 경기 수는 15번이며, 나올 수 있는 점수 합은 30
public class Main {
    static int[][] worldCup;
    static final int TEAM_COUNT = 6;
    static int[][] matches;
    static boolean isEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int totalGame = 0;
        int tc = 4; //4번의 게임
        for (int i = 1; i < TEAM_COUNT; i++) {
            totalGame += i; //1+2+3+4+5 = 15
        }
        matches = new int[totalGame][2]; //시합을 할 팀
        int teamNum = 0;
        for (int i = 0; i < TEAM_COUNT; i++) {
            for (int j = i + 1; j < TEAM_COUNT; j++) {
                matches[teamNum][0] = i; //A
                matches[teamNum][1] = j; //B,C,D,E,F
                teamNum++;
            }
        }
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            worldCup = new int[TEAM_COUNT][3]; //팀당 승, 무, 패
            boolean isPossible = true; //승+무+패=5인지 확인용
            isEnd = false; //백트래킹을 끝내는 조건

            for (int i = 0; i < TEAM_COUNT; i++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                worldCup[i][0] = win;
                worldCup[i][1] = draw;
                worldCup[i][2] = lose;

                //한 나라당 5게임을 하지 않는다면 게임 진행 X
                if (win + draw + lose != 5) {
                    isPossible = false;
                    break;
                }
            }
            //승무패의 합이 5인 조건 충족
            if (isPossible) {
                solve(worldCup, 0, totalGame);

                if (isEnd) {
                    sb.append(1);
                }
                else {
                    sb.append(0);
                }
            }
            else
            {
                sb.append(0);
            }
            sb.append(" ");
        }

        System.out.println(sb);

    }

    public static void solve(int[][] worldCup, int matchCnt, int totalGame) {
        if (isEnd) {
            return;
        }
        if (matchCnt == totalGame) { //모든 경기 끝날 수 있다면 이 월드컵 가능
            isEnd = true;
            return;
        }
        int myself = matches[matchCnt][0]; //나
        int enemy = matches[matchCnt][1]; //적

        //나 : 승 / 적 : 패
        if (worldCup[myself][0] > 0 && worldCup[enemy][2] > 0) {
            worldCup[myself][0]--;
            worldCup[enemy][2]--;
            solve(worldCup, matchCnt + 1, totalGame);
            worldCup[myself][0]++;
            worldCup[enemy][2]++;
        }
        //나 : 무 / 적 : 무
        if (worldCup[myself][1] > 0 && worldCup[enemy][1] > 0) {
            worldCup[myself][1]--;
            worldCup[enemy][1]--;
            solve(worldCup, matchCnt + 1, totalGame);
            worldCup[myself][1]++;
            worldCup[enemy][1]++;
        }
        //나 : 패 / 적 : 승
        if (worldCup[myself][2] > 0 && worldCup[enemy][0] > 0) {
            worldCup[myself][2]--;
            worldCup[enemy][0]--;
            solve(worldCup, matchCnt + 1, totalGame);
            worldCup[myself][2]++;
            worldCup[enemy][0]++;
        }

    }
}
