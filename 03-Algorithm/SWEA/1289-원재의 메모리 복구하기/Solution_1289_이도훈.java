
import java.util.Scanner;

class Solution_1289_이도훈 {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        sc.nextLine();


        for (int test_case = 1; test_case <= T; test_case++) {

            String s = sc.nextLine();

            boolean[] input = new boolean[s.length()];
            boolean[] comp = new boolean[s.length()];

            for (int i = 0; i < input.length; i++) {
                if (s.charAt(i) != '0') {
                    input[i] = true;
                }
            }

            int changeCnt = 0;
            for (int i = 0; i < input.length; i++) {
                if (changeCnt % 2 != 0) comp[i] = !comp[i];

                if (input[i] != comp[i]) {
                    changeCnt++;
                }


            }
            System.out.println("#" + test_case + " " + changeCnt);
        }
    }
}
