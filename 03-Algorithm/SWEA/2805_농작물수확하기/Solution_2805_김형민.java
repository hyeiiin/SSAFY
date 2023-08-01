package swea;

import java.io.*;
import java.util.*;


public class Solution_2805_김형민 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\CodingTest\\src\\swea\\input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n][n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < str.length(); j++) {
                    arr[j][i] = Integer.parseInt(String.valueOf(str.charAt(j)));
                    sum += arr[j][i];
                }
            }


            int pivotI = (n / 2);
            int pivotJ = (n / 2);
            int minus = 0;
            int temp = 0;
            //왼상
            for (int i = 0; i < pivotI; i++) {
                for (int j = 0; j < pivotJ; j++) {
                    temp += arr[i][j];
                }
                pivotJ--;
            }
            minus += temp;
            //오른상
            temp = 0;
            pivotI = (n / 2);
            pivotJ = (n / 2);
            for (int i = 0; i < pivotI; i++) {
                for (int j = pivotJ + 1; j < n; j++) {
                    temp += arr[i][j];
                }
                pivotJ++;
            }
            minus += temp;
            //왼하
            temp = 0;
            pivotI = (n / 2);
            pivotJ = (n / 2);
            for (int i = n-1; i > pivotI; i--) {
                for (int j = pivotJ-1; j > -1; j--) {
                    temp += arr[i][j];
                }
                pivotJ--;
            }
            minus += temp;
            //오른하
            temp = 0;
            pivotI = (n / 2);
            pivotJ = (n / 2);
            for (int i = n-1; i > pivotI; i--) {
                for (int j = pivotJ + 1; j < n; j++) {
                    temp += arr[i][j];
                }
                pivotJ++;
            }
            minus += temp;

            sb.append("#").append(testCase).append(" ").append(sum-minus).append("\n");
        }
        System.out.println(sb);

    }
}
