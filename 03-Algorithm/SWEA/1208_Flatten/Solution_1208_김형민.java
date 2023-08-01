package swea;

import java.io.*;
import java.util.*;

public class Solution_1208_김형민 {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\CodingTest\\src\\swea\\input.txt"));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testCase = 1; testCase <= 10; testCase++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());


            int[] arr = new int[100];
            for (int j = 0; j < 100; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for (int n = 0; n < N; n++) {
                Arrays.sort(arr);
                arr[0]++;
                arr[99]--;
            }
            Arrays.sort(arr);

            sb.append("#").append(testCase).append(" ").append(arr[99]-arr[0]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
