import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_이도훈 {

    static int[][] map;
    static int startCnt;
    static int r;
    static int c;
    static int N;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N);

        map = new int[2][2];
        divide(N, 0, 0, 0);

    }

    public static void divide(int length, int x, int y, int startCnt) {
        if (c < x || r < y || x + length <= c || y + length <= r) return;

        if (length == 2) {

            map[0][0] = startCnt;
            map[0][1] = startCnt + 1;
            map[1][0] = startCnt + 2;
            map[1][1] = startCnt + 3;

            System.out.println(map[r-y][c-x]);
            return;
        }

        int newLength = length / 2;
        int cn = newLength * newLength;// 사등분 쪼개기

        divide(newLength, x, y, startCnt);
        divide(newLength, x + newLength, y, startCnt + cn);
        divide(newLength, x, y + newLength, startCnt + cn * 2);
        divide(newLength, x + newLength, y + newLength, startCnt + cn * 3);
    }

}

