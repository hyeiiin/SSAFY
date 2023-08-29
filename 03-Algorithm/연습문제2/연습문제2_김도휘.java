import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sum;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(stick(N));

    }
    public static int stick(int n) {
        if (n == 1) { //2층
            return 2;
        }
        if (n == 2) { //2층
            return 5;
        }
        return sum + (stick(n - 2) + 2 * stick(n - 1));
    }
}
