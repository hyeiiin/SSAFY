import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16935_이도훈 {

    static int[][] arr;
    static int N;
    static int M;
    static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int operator = Integer.parseInt(st.nextToken());

            switch (operator) {
                case 1:
                    upDown();
                    break;
                case 2:
                    leftRight();
                    break;
                case 3:
                    right();
                    break;
                case 4:
                    left();
                    break;
                case 5:
                    five();
                    break;
                case 6:
                    six();
                    break;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void upDown() {
        int[][] newArr = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[arr.length - 1 - i].clone();
        }
        arr = newArr;
    }

    public static void leftRight() {
        int[][] newArr = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                newArr[i][arr[0].length - j - 1] = arr[i][j];
            }
        }
        arr = newArr;
    }


    public static void right() {
        int[][] newArr = new int[arr[0].length][arr.length];

        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                newArr[i][j] = arr[arr.length-j-1][i];
            }
        }
        arr = newArr;
    }

    public static void left() {
        int[][] newArr = new int[arr[0].length][arr.length];

        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                newArr[i][j] = arr[j][arr[0].length-i-1];
            }
        }
        arr = newArr;
    }

    public static void five() {
        int[][] temp = new int[arr.length / 2][arr[0].length / 2];

        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = 0; j < arr[0].length / 2; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        for (int i = arr.length / 2; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length / 2; j++) {
                arr[i - arr.length / 2][j] = arr[i][j];
            }
        }

        for (int i = arr.length / 2; i < arr.length; i++) {
            for (int j = arr[0].length / 2; j < arr[0].length; j++) {
                arr[i][j - arr[0].length / 2] = arr[i][j];
            }
        }

        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = arr[0].length / 2; j < arr[0].length; j++) {
                arr[i + arr.length / 2][j] = arr[i][j];
            }
        }

        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = 0; j < arr[0].length / 2; j++) {
                arr[i][j + arr[0].length / 2] = temp[i][j];
            }
        }
    }

    public static void six() {
        int[][] temp = new int[arr.length / 2][arr[0].length / 2];

        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = 0; j < arr[0].length / 2; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = arr[0].length / 2; j < arr[0].length; j++) {
                arr[i][j - arr[0].length / 2] = arr[i][j];
            }
        }

        for (int i = arr.length / 2; i < arr.length; i++) {
            for (int j = arr[0].length / 2; j < arr[0].length; j++) {
                arr[i - arr.length / 2][j] = arr[i][j];
            }
        }

        for (int i = arr.length / 2; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length / 2; j++) {
                arr[i][j + arr[0].length / 2] = arr[i][j];
            }
        }

        for (int i = 0; i < arr.length / 2; i++) {
            for (int j = 0; j < arr[0].length / 2; j++) {
                arr[i+arr.length/2][j] = temp[i][j];
            }
        }
    }

}
