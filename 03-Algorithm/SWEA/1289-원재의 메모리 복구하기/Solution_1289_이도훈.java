
import java.util.Scanner;

class Solution_1289_이도훈 {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        sc.nextLine();
        
        for (int test_case = 1; test_case <= T; test_case++) {

            String s = sc.nextLine();

            // 비트를 boolean으로 처리하기 위한 배열
            boolean[] input = new boolean[s.length()];
            boolean[] comp = new boolean[s.length()];

            for (int i = 0; i < input.length; i++) {
                if (s.charAt(i) != '0') {
                    input[i] = true;
                }
            }

            // 몇번 딸깍했는지 체크하는 변수
            int changeCnt = 0;
            
            // 자리 돌면서
            for (int i = 0; i < input.length; i++) {
                // 만약 딸깍을 짝수만큼 안했으면 기존에서 변화가 존재하는 것이므로 변화를 줌
                if (changeCnt % 2 != 0) comp[i] = !comp[i];

                // 값 비교
                if (input[i] != comp[i]) {
                    changeCnt++;
                }


            }
            System.out.println("#" + test_case + " " + changeCnt);
        }
    }
}
