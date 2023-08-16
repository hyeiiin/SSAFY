package boj;
import java.io.*;
import java.util.*;
public class Main_3109_김형민 {
    static int R, C;
    static boolean[][] canMove;
    static int[][] move;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        canMove = new boolean[R][C]; //이동할 수 있는지 확인
        move = new int[][]{{-1,1},{0,1},{1,1}};
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = input.charAt(j);
                if (c=='.'){
                    canMove[i][j] = true;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            if (getGas(i,0)){
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean getGas(int x, int y){
        canMove[x][y] = false;
        if (y == C-1){ //마지막에 도착했다면
            return true;
        }

        for (int[] mv : move) {
            int dx = x + mv[0];
            int dy = y + mv[1];

            if (dx>=0&&dx<R&&dy>=0&&dy<C&&canMove[dx][dy]){
                if (getGas(dx,dy)){//만약 끝까지 도착했다면 true 리턴됨

                    return true; //그러면 이 좌표도 true
                }
            }
        }
        return false;

    }

}

