package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_탁하윤 {
    static int N, M, arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 배열 크기 N
        M = Integer.parseInt(st.nextToken());   // 배열 크기 M
        int R = Integer.parseInt(st.nextToken());   // 연산 개수

        arr = new int[N][M];    // 배열 생성

        for(int i=0; i<N; i++){ // 배열 초기화
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int r=0; r<R; r++){ // 연산 개수만큼 연산하기
            int next = Integer.parseInt(st.nextToken());
            if(next == 1) rotate1();
            else if(next == 2) rotate2();
            else if(next == 3) rotate3();
            else if(next == 4) rotate4();
            else if(next == 5) rotate5();
            else if(next == 6) rotate6();
        }

        N = arr.length; // 연산 3, 4, 5, 6 진행하면서 배열의 가로 세로 크기가 달라질 수 있으므로 값 받아서 변경하기
        M = arr[0].length;
        for(int i=0; i<N; i++){ // 배열 출력
            for(int j=0; j<M; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

    }

    static void rotate1(){  // 상하 반전
        N = arr.length;
        M = arr[0].length;
        for(int i=0; i<N/2; i++){
            int[] temp = arr[i];    // 주소값 반전
            arr[i] = arr[N-1-i];
            arr[N-1-i] = temp;
        }
    }
    static void rotate2(){  // 좌우 반전
        N = arr.length;
        M = arr[0].length;
        for(int i=0; i<N; i++){
            for(int j=0; j<M/2; j++){
                int temp = arr[i][j];   // temp에 첫번째 값 넣어주고 값 변경
                arr[i][j] = arr[i][M-1-j];
                arr[i][M-1-j] = temp;
            }
        }

    }
    static void rotate3(){  // 오른쪽으로 90도 회전
        N = arr.length;
        M = arr[0].length;
        int[][] temp = new int[M][N];   // 오른쪽으로 90도 회전이기 때문에 가로 세로 값 변경

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                temp[j][N-1-i] = arr[i][j]; // temp로 새로운 배열 만들고 arr 변경하기
            }
        }
        arr = temp;
    }
    static void rotate4(){
        N = arr.length;
        M = arr[0].length;
        int[][] temp = new int[M][N];   // 왼쪽으로 90도 회전하기 때문에 가로 세로 값 변경

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                temp[M-1-j][i] = arr[i][j]; // temp로 새로운 배열 만들고 arr 변경하기
            }
        }

        arr = temp;
    }
    static void rotate5(){  // 시계 방향 돌리기
        N = arr.length;
        M = arr[0].length;

        for(int i=0; i<N/2; i++){
            for(int j=0; j<M/2; j++){
                int temp = arr[i][j];   // 처음 값 temp에 저장해두기
                arr[i][j] = arr[i+N/2][j];  // 1번째 영역은 4번째 영역 값으로 바꾸기
                arr[i+N/2][j] = arr[i+N/2][j+M/2];  // 4번째 영역은 3번째 영역 값으로 바꾸기
                arr[i+N/2][j+M/2] = arr[i][j+M/2];  // 3번째 영역은 2번째 영역 값으로 바꾸기
                arr[i][j+M/2] = temp;   // 2번째 영역은 1번째 영역 값으로 바꾸기
            }
        }
    }
    static void rotate6(){  // 반시계 방향 돌리기
        N = arr.length;
        M = arr[0].length;

        for(int i=0; i<N/2; i++){
            for(int j=0; j<M/2; j++){
                int temp = arr[i][j];   // 처음 값 temp에 저장해두기
                arr[i][j] = arr[i][j+M/2];  // 1번째 영역은 2번째 영역 값으로 바꾸기
                arr[i][j+M/2] = arr[i+N/2][j+M/2];  // 2번째 영역은 3번째 영역 값으로 바꾸기
                arr[i+N/2][j+M/2] = arr[i+N/2][j];  // 3번째 영역은 4번째 영역 값으로 바꾸기
                arr[i+N/2][j] = temp;   // 4번째 영역은 1번째 영역 값으로 바꾸기
            }
        }
    }


}
