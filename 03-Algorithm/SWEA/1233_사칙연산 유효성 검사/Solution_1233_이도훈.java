import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution_1233_이도훈 {

    static int N;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {

            N = Integer.parseInt(br.readLine());

            boolean isPossible = true;

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();

                // 끝노드인 경우
                if (st.countTokens() == 1) {
                    String input = st.nextToken();
                    if (input.equals("*") || input.equals("/") || input.equals("+") || input.equals("-")) {
                        isPossible = false;

                    }
                } else if (st.countTokens() == 2) {
                    isPossible = false;

                } else {
                    String input = st.nextToken();
                    if (input.equals("*") || input.equals("/") || input.equals("+") || input.equals("-")) {
                        continue;
                    }
                    isPossible = false;

                }
            }

            sb.append("#").append(test_case).append(" ");
            if (isPossible) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append("\n");

        }
        System.out.println(sb);
    }



}
