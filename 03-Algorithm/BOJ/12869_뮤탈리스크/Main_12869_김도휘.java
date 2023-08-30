import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}}; //6x3

    static int[][][] dp;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[61][61][61]; //공격 횟수의 최솟값이 들어가있는 배열
        int[] input = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        dfs(input, 0);
        System.out.println(min);

    }

    /**
     *
     * @param input : SCV
     * @param depth : 공격 횟수
     */
    public static void dfs(int[] input, int depth) {
        boolean isAllZero = true;
        for (int i = 0; i < N; i++) {
            if (input[i] != 0) {
                isAllZero = false;
            }
        }
        if (isAllZero) {
            min = Math.min(min, depth);
            return;
        }
        //한번도 공격을 안한 값이 아니고(0), 지금 공격 횟수보다 작다면 최소 공격 횟수 변경 X
        if (dp[input[0]][input[1]][input[2]] <= depth && dp[input[0]][input[1]][input[2]] != 0) {
            return;
        }
        else {
            dp[input[0]][input[1]][input[2]] = depth;
        }

        int[] tempArr = input.clone(); //배열 복사
        for (int i = 0; i < 6; i++) { //공격 유형
            for (int j = 0; j < N; j++) {
                if (input[j] - attack[i][j] < 0) { //음수라면 0 설정
                    tempArr[j] = 0;
                }
                else {
                    tempArr[j] = input[j] - attack[i][j];
                }
            }
            dfs(tempArr, depth + 1); //재귀 호출
        }
    }
}
