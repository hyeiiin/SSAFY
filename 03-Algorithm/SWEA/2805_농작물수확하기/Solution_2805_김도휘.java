import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0};
    static int[] dy = {1, -1};
    static int answerX;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine()); //홀수
            int[][] map = new int[N][N];

            for (int j = 0; j < N; j++) {
                String str = br.readLine();
                for (int k = 0; k < N; k++) {
                    map[j][k] = str.charAt(k) - '0';
                }
            }
            System.out.println("#" + (i + 1) + " " + farm(N, map));

        }
    }

    public static int farm(int N, int[][] map) {
        int middle = N / 2;
        int totalSum = 0;
        int upSum = 0;
        for (int i = 0; i <= middle; i++) {
            for (int j = middle - i; j <= middle + i; j++) {
                upSum += map[i][j];
            }
        }
        int downSum = 0;
        int s = 1;
        for (int i = middle + 1; i < N; i++) {
            for (int j = s; j <= N - 1 - s; j++) {

                downSum += map[i][j];

            }
            s++;
        }
        totalSum = upSum + downSum;
        return totalSum;
    }
}