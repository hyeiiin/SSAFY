import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution_2805_이도훈 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = Integer.parseInt(br.readLine());


            int cnt = 1;
            int add = 2;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                String input = br.readLine();

                if (i == N / 2) {
                    add = -2;
                }

                int start = N / 2 - cnt / 2;

                for (int j = 0; j < cnt; j++) {
                    sum += input.charAt(start + j) - '0';
                }

                cnt += add;

            }

            sb.append("#").append(test_case)
                    .append(" ")
                    .append(sum).append("\n");
        }

        System.out.println(sb);

    }
}
