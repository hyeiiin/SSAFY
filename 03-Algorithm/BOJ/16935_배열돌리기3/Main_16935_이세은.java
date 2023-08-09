package ssafyStudy.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_이세은 {

    private static int[][] arr;
    private static int n, m;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine()); // 명령 입력받기
        for (int i = 0; i < r; i++) {
            switch (st.nextToken()) {
                case "1":
                    changeUD();
                    break;
                case "2":
                    changeRL();
                    break;
                case "3":
                    arr = turnR();
                    break;
                case "4":
                    arr = turnL();
                    break;
                case "5":
                    arr = turnSectionR();
                    break;
                case "6":
                    arr = turnSectionL();
                    break;
            }
        }
        print(arr);

    }

    private static void print(int[][] arr) {
        // 배열 결과 출력
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //4개 섹션 왼쪽으로 회전 함수
    private static int[][] turnSectionL() {
        int[][] newRslt = new int[n][m];
        //1그룹->4그룹
        for (int i = n/2; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                newRslt[i][j] = arr[i-n/2][j];
            }
        }
        //4그룹->3그룹
        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                newRslt[i][j] = arr[i][j-m/2];
            }
        }
        //3그룹->2그룹
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {
                newRslt[i][j] = arr[i+n/2][j];
            }
        }
        //2그룹->1그룹
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                newRslt[i][j] = arr[i][j+m/2];
            }
        }

        return newRslt;

    }

    //4개 섹션 오른쪽으로 회전 함수
    static int[][] turnSectionR() {
        int[][] newRslt = new int[n][m];
        //1그룹->2그룹
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {
                newRslt[i][j] = arr[i][j-m/2];
            }
        }
        //2그룹->3그룹
        for (int i = n / 2; i < n; i++) {
            for (int j = m / 2; j < m; j++) {
                newRslt[i][j] = arr[i-n/2][j];
            }
        }
        //3그룹->4그룹
        for (int i = n / 2; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                newRslt[i][j] = arr[i][j+m/2];
            }
        }
        //4그룹->1그룹
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                newRslt[i][j] = arr[i+n/2][j];
            }
        }
        return newRslt;
    }

    //왼쪽 90도 회전 함수
    static int[][] turnL() {
        int[][] newRslt = new int[m][n];//행렬 반전
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newRslt[i][j] = arr[j][m - i - 1]; //행 마다 차례대로 끝 요소부터 넣어주기
            }
        }

        //회전한 후 n과 m 바꿔주기
        int tmp = n;
        n = m;
        m = tmp;

        return newRslt;

    }

    // 오른쪽 90도 회전 함수
    static int[][] turnR() {
        int[][] newRslt = new int[m][n]; //행렬 반전
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newRslt[i][j] = arr[n - j - 1][i]; //열 마다 차례대로 끝 요소부터 넣어주기
            }
        }

        //회전한 후 n과 m 바꿔주기
        int tmp = n;
        n = m;
        m = tmp;

        return newRslt;

    }

    // 배열 좌우반전 함수
    private static void changeRL() {
        int[] tmp = new int[n]; // 한 열씩 덤프
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < tmp.length; j++) {
                tmp[j] = arr[j][i];
            }
            int col = m - 1 - i;
            for (int j = 0; j < n; j++) {
                arr[j][i] = arr[j][col];
            }
            for (int j = 0; j < tmp.length; j++) {
                arr[j][col] = tmp[j];
            }
        }

    }

    // 배열 상하반전 함수
    private static void changeUD() {
        int[] tmp = new int[m]; // 한 행씩 덤프
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < tmp.length; j++) {
                tmp[j] = arr[i][j];
            }
            int row = n - 1 - i;
            for (int j = 0; j < m; j++) {
                arr[i][j] = arr[row][j];
            }
            for (int j = 0; j < tmp.length; j++) {
                arr[row][j] = tmp[j];
            }
        }
    }

}
