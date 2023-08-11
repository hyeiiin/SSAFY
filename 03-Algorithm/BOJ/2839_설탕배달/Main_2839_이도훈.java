import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_이도훈 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        while (n % 5 != 0 && n > 0) {
            n -= 3;
            cnt++;
        }
        if (n < 0) {
            System.out.println(-1);
        } else {
            System.out.println(cnt + n / 5);
        }
    }
}
