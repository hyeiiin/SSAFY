package ssafyStudy.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1210_이세은 {
    private static int[] moveX = { 0, 0, -1 }; // 좌 우 상
    private static int[] moveY = { -1, 1, 0 }; // 좌 우 상
    private static int[][] ladder;
    private static int rslt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int no = Integer.parseInt(br.readLine());

            ladder = new int[100][100];
            int endX = 0;
            int endY = 0;

            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                    if (ladder[i][j] == 2) {
                        endX = i;
                        endY = j;
                    }
                }
            }
            findStart(endX, endY); //도착점을 endX, endY로 하는 출발점 찾기
            bw.write("#" + no + " " + rslt + "\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    public static void findStart(int x, int y) {
        if (x == 0) { // 가장 상단까지 올라옴
            rslt = y; //도착점의 열
            return;
        }

        for (int i = 0; i < 3; i++) {//3방탐색, 좌우상
            int newX = x + moveX[i];
            int newY = y + moveY[i];

            if (newX >= 0 && newY >= 0 && newX < ladder.length && newY < ladder.length) {
                if (ladder[newX][newY] == 1) {
                    ladder[newX][newY] = -1; //방문처리
                    if(i==0 || i==1){ //좌나 우로 이동 가능한 경우 위로 못감
                        ladder[x-1][y] = -1; //위쪽 방향 방문처리해버리기
                    }
                    findStart(newX, newY);
                }
            }
        }
    }
}
