import java.io.*;
import java.util.*;

public class Solution {
    static boolean[] cards; //규영이 카드 true, 인영이 카드 false 구분하는 배열
    static int[] gu; //규영이 카드번호 담은 배열
    static int[] in; //인영이 카드번호 담은 배열
    static boolean[] isSelected; //조합하기 위해 선택됐는지 확인하기 위한 배열
    static int[] remainNumbers; //규영이 카드번호를 제외한 번호 -> 인영이 카드 번호
    static int win; //규영이가 이긴 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            cards = new boolean[18];
            in = new int[9]; //인영
            gu = new int[9]; //규영
            isSelected = new boolean[9];
            remainNumbers = new int[9];
            win = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                gu[j] = Integer.parseInt(st.nextToken());
                cards[gu[j] - 1] = true; //규영이 카드 true, 인영이 카드 false
            }
            int index = 0;
            //인영이 카드 채우기
            for (int j = 0; j < 18; j++) {
                if (!cards[j]) {
                    remainNumbers[index++] = j + 1;
                }
            }
            cardGame(0);
            sb.append("#" + (i + 1)).append(" ").append(win).append(" ").append(362880 - win).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void cardGame(int cnt) {
        if (cnt == 9) { //게임 끝 (9 라운드)
            int gu_point = 0; //규영이 점수
            int in_point = 0; //인영이 점수
            for (int i = 0; i < 9; i++) {
                if (gu[i] > in[i]) { //규영 승
                    gu_point += gu[i] + in[i];
                } else if (gu[i] < in[i]) { //인영 승
                    in_point += gu[i] + in[i];
                }
            }
            if (gu_point > in_point) {
                win++;
            }
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (isSelected[i]) {
                continue;
            }
            isSelected[i] = true;
            in[cnt] = remainNumbers[i]; //인영이 카드번호 넣기
            cardGame(cnt + 1);
            isSelected[i] = false;
        }
    }
}
