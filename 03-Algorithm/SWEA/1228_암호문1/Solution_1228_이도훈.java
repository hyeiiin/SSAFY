
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

class Solution_1228_이도훈 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        ArrayDeque<Integer> front = new ArrayDeque<>();
        ArrayDeque<Integer> back = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                back.add(Integer.parseInt(st.nextToken()));
            }

            int I = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < I; i++) {
                st.nextToken();

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                for (int j = 0; j < x; j++) {
                    front.add(back.pollFirst());
                }

                for (int j = 0; j < y; j++) {
                    front.addLast(Integer.parseInt(st.nextToken()));
                }

                while (!front.isEmpty()) {
                    back.addFirst(front.pollLast());
                }
            }
            sb.append("#").append(test_case).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(back.pollFirst()).append(" ");
            }
            while (!back.isEmpty()) {
                back.pollLast();
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
