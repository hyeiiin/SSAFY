package boj;


import java.io.*;
import java.util.*;
public class Main_16926_김형민 {
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }
        }
        rotate(arr,R);
        for (int[] ints : arr) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
    static void rotate(int[][] arr, int r){
        ArrayDeque<Integer> deque = new ArrayDeque<>();;
        int cnt = 0;
        int maxCnt = N*M;
        int n = N-1;
        int m = M-1;
        int start = 0;
        while(cnt<maxCnt){
            int i = start;
            int j = start;
            int di = start;
            int dj = start;
            //오른쪽
            for (int k = 0; k < m && cnt<maxCnt; k++) {
                deque.addLast(arr[i][j++]);
                cnt++;
            }

            //아래
            for (int k = 0; k < n && cnt<maxCnt; k++) {
                deque.addLast(arr[i++][j]);
                cnt++;
            }
            //왼쪽
            for (int k = 0; k < m && cnt<maxCnt; k++) {
                deque.addLast(arr[i][j--]);
                cnt++;
            }
            //위
            for (int k = 0; k < n && cnt<maxCnt; k++) {
                deque.addLast(arr[i--][j]);
                cnt++;
            }
            //배열 r번 회전
            for (int k = 0; k < r; k++) {
                deque.addLast(deque.pollFirst());
            }

            //배열 바꿔 넣어주기

            //오른쪽
            for (int k = 0; k < m; k++) {
                arr[di][dj++]=deque.pollFirst();
            }
            //아래
            for (int k = 0; k < n; k++) {
                arr[di++][dj]=deque.pollFirst();
            }
            //왼쪽
            for (int k = 0; k < m; k++) {
                arr[di][dj--]=deque.pollFirst();
            }
            //위
            for (int k = 0; k < n; k++) {
                arr[di--][dj]=deque.pollFirst();
            }

            start++;
            m=m-2;
            n=n-2;
        }

    }


}
