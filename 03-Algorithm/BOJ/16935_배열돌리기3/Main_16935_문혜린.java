package algorithm;

import java.util.*;
import java.io.*;

public class Main_16935_문혜린 {
    static int arr[][]; //원본 배열
    static int arr2[][]; //연산 수행 배열
    static int N, M; //행, 열 개수
    static StringBuilder sb;

    public static void one() { //상하 반전
        for (int i = 0; i < N/2; i++) {
            for (int j = 0; j < M; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[N-1-i][j];
                arr[N-1-i][j] = temp;
            }
        }
    }
    public static void two() { //좌우 반전
        for (int i = 0; i < M/2; i++) {
            for (int j = 0; j < N; j++) {
                int temp = arr[j][i];
                arr[j][i] = arr[j][M-1-i];
                arr[j][M-1-i] = temp;
            }
        }
    }
    public static void three() { //오른쪽으로 90도 회전
    	arr2 = new int[M][N]; //90도 회전하면 행,열 크기 바뀜
        for (int j = 0; j < M; j++) {
            for (int i = N-1; i >= 0; i--) {
                arr2[j][N-1-i] = arr[i][j];
            }
        }
        
        arr = arr2.clone(); //원본 배열에 복사해주기
        //행,열 크기 변환
        int tmp = N;
        N = M;
        M = tmp;
    }

    public static void four() { //왼쪽으로 90도 회전
    	arr2 = new int[M][N]; //90도 회전하면 행,열 크기 바뀜
        for (int j = 0; j < M; j++) {
            for (int i = N-1; i >= 0; i--) {
                arr2[M-1-j][i] = arr[i][j];
            }
        }
        arr = arr2.clone(); //원본 배열에 복사해주기
        //행,열 크기 변환
        int tmp = N;
        N = M;
        M = tmp;
    }
    public static void five() { //1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동
        arr2 = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //1번 그룹(좌상) -> 2번 그룹(우상)
                if(i<N/2 && j<M/2) {
                    arr2[i][j+M/2] = arr[i][j];
                }
                //2번 그룹(우상) -> 3번 그룹(우하)
                else if(i<N/2 && j>=M/2) {
                    arr2[i+N/2][j]= arr[i][j];
                }
                //3번 그룹(우하) -> 4번 그룹(좌하)
                else if(i>=N/2 && j>=M/2) {
                    arr2[i][j-M/2] = arr[i][j];
                }
                //4번 그룹(좌하) -> 1번 그룹(좌상)
                else if(i>=N/2 && j<M/2) {
                    arr2[i-N/2][j] = arr[i][j];
                }
            }
        }
        arr = arr2.clone();
    }
    public static void six() { //1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동
        arr2 = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //1번 그룹(좌상) -> 4번 그룹(좌하)
                if(i<N/2 && j<M/2) {
                    arr2[i+N/2][j] = arr[i][j];
                }
                //4번 그룹(좌하) -> 3번 그룹(우하)
                else if(i>=N/2 && j<M/2) {
                    arr2[i][j+M/2]= arr[i][j];
                }
                //3번 그룹(우하) -> 2번 그룹(우상)
                else if(i>=N/2 && j>=M/2) {
                    arr2[i-N/2][j] = arr[i][j];
                }
                //2번 그룹(우상) -> 1번 그룹(좌상)
                else if(i<N/2 && j>=M/2) {
                    arr2[i][j-M/2] = arr[i][j];
                }
            }
        }
        arr = arr2.clone();
    }
    public static void print() { //출력 함수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append("\n");
    }


public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    sb = new StringBuilder();
    
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); //행 개수
    M = Integer.parseInt(st.nextToken()); //열 개수
    int R = Integer.parseInt(st.nextToken()); //연산 개수
    
    arr = new int[N][M]; //원본 배열
    for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
            arr[i][j] = Integer.parseInt(st.nextToken());
        }
    }
    
    int[] oper = new int[R]; //연산 종류 저장
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < R; i++) {
        oper[i] = Integer.parseInt(st.nextToken());
    }
    
    //배열 돌리기
    for (int i = 0; i < R; i++) {
        switch (oper[i]) {
        case 1:		   //1번 연산
            one();
            break;
        case 2:        //2번 연산
            two();
            break;
        case 3:        //3번 연산
            three();
            break;
        case 4:        //4번 연산
            four();
            break;
        case 5:        //5번 연산
            five();
            break;
        case 6:        //6번 연산
            six();
            break;
        }
    }
    print(); //결과 출력
    
    System.out.println(sb);
}
}