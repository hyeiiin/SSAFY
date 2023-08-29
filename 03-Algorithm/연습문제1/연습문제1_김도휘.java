import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int sum;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(paint(N));

    }
    public static int paint(int n) {
        if (n == 1) { //1층
            return 2;
        }
        if (n == 2) { //2층
            return 3;
        }
        //전층과 전전층의 합
        return sum + paint(n - 2) + paint(n - 1);
    }
}
