import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_6987_이도훈 {


    static int[][] teamResult;
    static int[][] vs;

    static boolean isOk;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        vs = new int[15][2];
        int vsIdx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                vs[vsIdx][0] = i;
                vs[vsIdx][1] = j;
                vsIdx++;
            }
        }

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            boolean isPossible = true;

            teamResult = new int[6][3];

            for (int j = 0; j < 6; j++) {
                int w = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                if (w + t + l != 5) {
                    isPossible = false;
                    break;
                }

                teamResult[j][0] = w;
                teamResult[j][1] = t;
                teamResult[j][2] = l;
            }

            if (isPossible) {
                isOk = false;
                dfs(0);
                if (isOk) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
            } else {
                sb.append("0 ");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(int vsIdx) {
        if (vsIdx == 15) {
            isOk = true;
            return;
        }

        int aTeam = vs[vsIdx][0];
        int bTeam = vs[vsIdx][1];

        // 승
        if (teamResult[aTeam][0] > 0 && teamResult[bTeam][2] > 0) {
            teamResult[aTeam][0]--;
            teamResult[bTeam][2]--;
            dfs(vsIdx + 1);
            teamResult[aTeam][0]++;
            teamResult[bTeam][2]++;
        }

        // 무
        if (teamResult[aTeam][1] > 0 && teamResult[bTeam][1] > 0) {
            teamResult[aTeam][1]--;
            teamResult[bTeam][1]--;
            dfs(vsIdx + 1);
            teamResult[aTeam][1]++;
            teamResult[bTeam][1]++;
        }

        // 패
        if (teamResult[aTeam][2] > 0 && teamResult[bTeam][0] > 0) {
            teamResult[aTeam][2]--;
            teamResult[bTeam][0]--;
            dfs(vsIdx + 1);
            teamResult[aTeam][2]++;
            teamResult[bTeam][0]++;
        }

    }


}

