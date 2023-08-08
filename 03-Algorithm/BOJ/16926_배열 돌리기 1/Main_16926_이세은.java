package ssafyStudy.BOJ;

import java.io.*;
import java.util.*;

public class Main_16926_이세은 {


    private static int[][] arr;
    private static int[] moveR = {1, 0, -1, 0};
    private static int[] moveC = {0, 1, 0, -1};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 행
        int m = Integer.parseInt(st.nextToken()); // 열
        int r = Integer.parseInt(st.nextToken()); // 회전 수

        arr = new int[n][m]; // 배열 생성
        // 배열 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // n과 m중 작은 수는 2의 배수
        int min = Math.min(n, m);
        for (int t = 0; t < r; t++) { // r번 진행
            int i = 0, j = 0;
            visited = new boolean[n][m];
//            while (min >0) { // 행 또는 열 중 작은 것 따라서 내부 배열 돌리기 진행
//                System.out.println("min = " + min);
//                System.out.println("i = " + i);
//                System.out.println("j = " + j);
//                turn(i++, j++);
//                min /= 2;
//            }
            for (int a = 0; a < min / 2; a++) {
                for (int b = 0; b < min / 2; b++) {
                    turn(a, b);
                }
            }
        }

        // 배열 출력
        for (int k = 0; k < n; k++) {
            for (int z = 0; z < m; z++) {
                System.out.print(arr[k][z] + " ");
            }
            System.out.println();
        }
    }

    // 시작점 i행, j열을 기준으로 row행과 col열 반경 반시계 방향으로 한칸 회전하는 함수
    public static void turn(int i, int j) {
        int k = 0;
        int newX = i, newY = j;
        Queue<Integer> q = new LinkedList<>();// 현재 값 다음 값에 저장하기 위한 큐 이용 ->(1,0)->(0,0)->...
        q.add(arr[newX][newY]); //처음 좌표 넣어주기
        // 하우상좌 순, 먼저 아래로 갈 수 있을만큼 진행, 후에 오른쪽으로 갈 수 있을만큼 진행..

        while (!visited[i][j]) { //처음 좌표에 방문하지않은 동안 계속 진행
            newX += moveR[k];
            newY += moveC[k];
            if (newX >= 0 && newY >= 0 && newX < arr.length && newY < arr[0].length && !visited[newX][newY]) {
                visited[newX][newY] = true; //현재 좌표 방문처리
                q.add(arr[newX][newY]); // 현재 값 큐에 저장
                arr[newX][newY] = q.poll();// 큐 안에 담긴 값은 바로 예전 값

            } else { //더이상 진행 불가하면 방향 바꿈
                //한칸 더 간것 빼주기
                newX -= moveR[k];
                newY -= moveC[k];
                k++;
                if (k == 4) // 반시계로 한바퀴 다 돌았다
                    k = 0;
            }
        }
    }
}
