import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 0; T < 10; T++) {
            int length = Integer.parseInt(br.readLine());
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            int cntA = 0;
            int cntB = 0;
            int cntC = 0;
            int cntD = 0;
            boolean isPossible = true;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '(') {
                    cntA++;
                }
                if (c == '[') {
                    cntB++;
                }
                if (c == '{') {
                    cntC++;
                }
                if (c == '<') {
                    cntD++;
                }
                if (c == ')') {
                    if (cntA < 0) {
                        isPossible = false;
                        break;
                    }
                    else {
                        cntA--;
                    }
                }
                if (c == ']') {
                    if (cntB < 0) {
                        isPossible = false;
                        break;
                    }
                    else {
                        cntB--;
                    }
                }
                if (c == '}') {
                    if (cntC < 0) {
                        isPossible = false;
                        break;
                    }
                    else {
                        cntC--;
                    }
                }
                if (c == '>') {
                    if (cntD < 0) {
                        isPossible = false;
                        break;
                    }
                    else {
                        cntD--;
                    }
                }
            }
            if (cntA != 0 || cntB != 0 || cntC != 0 || cntD != 0) {
                isPossible = false;
            }
            if (isPossible) {
                System.out.println("#" + (T + 1) + " " + 1);
            }
            else
                System.out.println("#" + (T + 1) + " " + 0);
        }

    }
}
