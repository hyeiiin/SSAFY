import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

class Solution_1218_이도훈 {

    static HashMap<Character,Character> map;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        map.put('>','<');

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());

            String s = br.readLine();

            ArrayDeque<Character> stack = new ArrayDeque<>();
            boolean isAble = true;

            for (int i = 0; i < N; i++) {

                if (map.containsValue(s.charAt(i))) {
                    stack.add(s.charAt(i));
                }

                if (map.containsKey(s.charAt(i))) {
                    if (stack.pollLast() != map.get(s.charAt(i))) {
                        isAble = false;
                        break;
                    }
                }
            }

            sb.append("#").append(test_case).append(" ");
            if (isAble) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
