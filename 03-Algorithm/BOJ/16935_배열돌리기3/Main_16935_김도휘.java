import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[] dx = {0, 1, 0, -1}; //오른쪽 -> 아래 -> 왼쪽 -> 위
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //가로
        M = Integer.parseInt(st.nextToken()); //세로
        R = Integer.parseInt(st.nextToken()); //회전 횟수
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] command = new int[R];
        int[][] ans = new int[N][M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            command[i] = Integer.parseInt(st.nextToken()); //연산 번호

            switch (command[i]) {
                case 1: //상하 반전
                    map = upDown(map);
                    break;
                case 2: //좌우 반전
                    map = leftRight(map);
                    break;
                case 3: //오른쪽 90도 회전
                    map = rightRotate(map);
                    break;
                case 4: //왼쪽 90도 회전
                    map = leftRotate(map);
                    break;
                case 5: //
                    map = moveRight(map);
                    break;
                case 6:
                    map = moveLeft(map);
                    break;

            }
        }
        print(map);


    }

    public static int[][] upDown(int[][] map) {
        int half = N / 2; //실제 행은 half-1
        int[][] tempMap = new int[N][M];

        //0,1,2
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[N - 1 - i][j];
            }
        }
        return tempMap;
    }

    public static int[][] leftRight(int[][] map) {
        int half = N / 2; //실제 행은 half-1
        int[][] tempMap = new int[N][M];

        //0,1,2
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][M - 1 - j];
            }
        }
        return tempMap;
    }

    public static int[][] leftRotate(int[][] map) {
        int[][] tempMap = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[M - 1 - j][i] = map[i][j];
            }
        }
        int temp = M;
        M = N;
        N = temp;
        return tempMap;
    }

    public static int[][] rightRotate(int[][] map) {
        int[][] tempMap = new int[M][N];

        int F = N - 1; // 5
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[j][F] = map[i][j];
            }
            F--;
        }
        int temp = M;
        M = N;
        N = temp;
        return tempMap;
    }

    public static int[][] moveRight(int[][] map) {
        //1->2, 2->3, 3->4, 4->1
        int halfN = map.length / 2; //3
        int halfM = map[0].length / 2; //4
        int[][] tempArr = new int[map.length][map[0].length];

        //1->2
        for (int i = 0; i < halfN; i++) { //3
            for (int j = 0; j < M / 2; j++) {
                tempArr[i][j + halfM] = map[i][j];
            }
        }
        //2->3
        for (int i = 0; i < N / 2; i++) {
            for (int j = halfM; j < M; j++) {
                tempArr[i + halfN][j] = map[i][j];
            }

        }
        //3->4
        for (int i = halfN; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                tempArr[i][j] = map[i][j + halfM];
            }
        }
        //4->1
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < halfM; j++) {
                tempArr[i][j] = map[i + halfN][j];
            }
        }
        return tempArr;

    }

    public static int[][] moveLeft(int[][] map) {
        //1->4, 4->3, 3->2, 2->1

        int halfN = map.length / 2; //3
        int halfM = map[0].length / 2; //4
        int[][] tempArr = new int[map.length][map[0].length];

        //1->4
        for (int i = 0; i < N/2; i++) { //3
            for (int j = 0; j < halfM; j++) {
                tempArr[i + halfN][j] = map[i][j];
            }
        }
        //2->1
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M/2; j++) {
                tempArr[i][j] = map[i][j + halfM];
            }

        }
        //3->2
        for (int i = 0; i < N/2; i++) {
            for (int j = halfM; j < M; j++) {
                tempArr[i][j] = map[i + halfN][j];
            }
        }
        //4->3
        for (int i = halfN; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                tempArr[i][j + halfM] = map[i][j];
            }
        }
        return tempArr;
    }

    public static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
