import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_이도훈 {

    static int N;
    static int[] fronts = {2,3, 5, 7};
    static int[] ends = {1, 3, 7, 9};

    static StringBuilder sb;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int front : fronts) {
            makePrime(1, front);
        }
        System.out.println(sb);

    }

    // dfs
    public static void makePrime(int depth, int i) {
        // 탈출 조건
        if (depth == N) {
            sb.append(i).append("\n");
        }

        // 끝자리 붙여주기
        for (int end : ends) {
            int num = i * 10 + end;
            if (isPrime(num)) {
                makePrime(depth + 1, num);
            }
        }

    }

    // 소수 체크
    public static boolean isPrime(int n) {
        if(n <= 1) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }


}
