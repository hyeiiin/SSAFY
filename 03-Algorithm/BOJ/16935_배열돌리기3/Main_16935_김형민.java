package boj;

import java.io.*;
import java.util.*;

public class Main_16935_김형민 {
    static StringBuilder sb;
    static int N;
    static int M;
    static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int code = Integer.parseInt(st.nextToken());
            N = arr.length;
            M = arr[0].length;
            if (code==1){
                arr = one(arr);
            }
            if (code==2){
                arr = two(arr);
            }
            if (code==3){
                arr = thr(arr);
            }
            if (code==4){
                arr = four(arr);
            }
            if (code==5){
                arr = five(arr);
            }
            if (code==6){
                arr = six(arr);
            }
        }

        for (int[] ints : arr) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[][] one(int[][] arr) {
        int[][] result = new int[N][M];
        //상하
        for (int i = N - 1; i > -1; i--) {
            for (int j = 0; j < M; j++) {
                result[(N - 1) - i][j] = arr[i][j];
            }
        }
        return result;
    }

    static int[][] two(int[][] arr) {
        int[][] result = new int[N][M];
        //좌우
        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j > -1; j--) {
                result[i][(M-1)-j] = arr[i][j];
            }
        }
        return result;
    }


    static int[][] thr(int[][] arr) {
        int[][] result = new int[M][N];
        //오른쪽 90
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = arr[(N - 1) - j][i];
                // 0 0 -> 5 0
                // 0 1 -> 4 0
            }
        }
        return result;
    }

    static int[][] four(int[][] arr) {
        int[][] result = new int[M][N];
        //왼쪽 90
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = arr[j][(M - 1)- i];
                // 0 0 -> 0 5
                // 0 1 -> 1 5
            }
        }
        return result;
    }

    static int[][] five(int[][] arr) {
        ArrayDeque<int[][]> splitArr = getSplitArr(arr);
        //분할 시계방향
        //1 2 3 4 -> 4 1 2 3
        //맨 뒤를 빼서 앞에 넣는다.
        splitArr.addFirst(splitArr.pollLast());
        int[][] result = setSplitArr(splitArr);
        return result;
    }

    static int[][] six(int[][] arr) {
        ArrayDeque<int[][]> splitArr = getSplitArr(arr);
        //분할 역시계방향
        //1 2 3 4 -> 2 3 4 1
        //앞을 빼서 뒤에 넣는다.
        splitArr.add(splitArr.pollFirst());
        int[][] result = setSplitArr(splitArr);
        return result;
    }

    static ArrayDeque<int[][]> getSplitArr(int[][] arr){
        ArrayDeque<int[][]> queue = new ArrayDeque<>();
        int[][] temp;
        //좌상 넣기
        temp = new int[N/2][M/2];
        for (int i = 0; i < N/2; i++) {
            for (int j = 0; j < M/2; j++) {
                temp[i][j]=arr[i][j];
            }
        }
        queue.addLast(temp);
        //우상 넣기
        temp = new int[N/2][M/2];
        for (int i = 0; i < N/2; i++) {
            for (int j = M/2; j < M; j++) {
                temp[i][j-(M/2)]=arr[i][j];
            }
        }
        queue.addLast(temp);
        //우하 넣기
        temp = new int[N/2][M/2];
        for (int i = N/2; i < N; i++) {
            for (int j = M/2; j < M; j++) {
                temp[i-(N/2)][j-(M/2)]=arr[i][j];
            }
        }
        queue.addLast(temp);
        //좌하 넣기
        temp = new int[N/2][M/2];
        for (int i = N/2; i < N; i++) {
            for (int j = 0; j < M/2; j++) {
                temp[i-(N/2)][j]=arr[i][j];
            }
        }
        queue.addLast(temp);

        return queue;
    }
    static int[][] setSplitArr(ArrayDeque<int[][]> queue){
        int[][] result = new int[N][M];
        int[][] temp;
        //좌상 넣기
        temp = queue.pollFirst();
        for (int i = 0; i < N/2; i++) {
            for (int j = 0; j < M/2; j++) {
                result[i][j]=temp[i][j];
            }
        }
        //우상 넣기
        temp = queue.pollFirst();
        for (int i = 0; i < N/2; i++) {
            for (int j = M/2; j < M; j++) {
                result[i][j]=temp[i][j-(M/2)];
            }
        }
        //우하 넣기
        temp = queue.pollFirst();
        for (int i = N/2; i < N; i++) {
            for (int j = M/2; j < M; j++) {
                result[i][j]=temp[i-(N/2)][j-(M/2)];
            }
        }
        //좌하 넣기
        temp = queue.pollFirst();
        for (int i = N/2; i < N; i++) {
            for (int j = 0; j < M/2; j++) {
                result[i][j]=temp[i-(N/2)][j];
            }
        }
        return result;
    }
}
