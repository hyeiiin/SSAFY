import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] materials;
    static int[] numbers;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        materials = new int[N + 1][2];

        //N개중 몇개의 재료를 뽑아야 하는지 -> 조합

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            materials[i][0] = Integer.parseInt(st.nextToken());
            materials[i][1] = Integer.parseInt(st.nextToken());
        }
        //넣을 재료 개수 1, 2, 3, 4 ,, N개
        for (int i = 1; i <= N; i++) {
            numbers = new int[i];
            cook(1, 0, i);

        }
        System.out.println(min);

    }

    public static void cook(int start, int depth, int size) {
        if (depth == size) {
            int sourSum = 1;
            int bitterSum = 0;
            for (int i = 0; i < size; i++) {

                sourSum *= materials[numbers[i]][0];
                bitterSum += materials[numbers[i]][1];
                min = Math.min(min, Math.abs(bitterSum - sourSum));
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            numbers[depth] = i;
            cook(i + 1, depth + 1, size);

        }

    }
}