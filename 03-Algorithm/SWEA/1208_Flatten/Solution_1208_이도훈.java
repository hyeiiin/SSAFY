
import java.util.Scanner;
import java.util.StringTokenizer;

class Solution_1208_이도훈 {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(sc.nextLine());
            st = new StringTokenizer(sc.nextLine());

            // 그냥 높이 카운팅해서 낮은 애들로 뿌려주면 될 듯
            int[] arr = new int[101];
            for (int i = 0; i < 100; i++) {
                arr[Integer.parseInt(st.nextToken())]++;
            }

            int low = 1;
            int top = 100;

            for (int i = low; i < arr.length; i++) {
                if (arr[i] != 0) {
                    low = i;
                    break;
                }
            }
            for (int i = top; i >= 0; i--) {
                if (arr[i] != 0) {
                    top = i;
                    break;
                }
            }

            while (N > 0) {
                if (arr[top] == 0) {
                    top--;
                    continue;
                }

                if (arr[low] == 0) {
                    low++;
                    continue;
                }

                if (low + 1 >= top)
                    break;

                arr[top]--;
                arr[top - 1]++;
                arr[low]--;
                arr[low + 1]++;

                N--;
            }
            if(arr[low] == 0) low++;
            if(arr[top] == 0) top--;

            sb.append("#").append(test_case)
                    .append(" ")
                    .append(top - low)
                    .append("\n");
        }
        System.out.println(sb);
    }
}
