import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //자리수

        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    public static boolean isPrime(int num)
    {
        for (int i = 2; i <= num / 2; i++)
        {
            if (num % i == 0)
            {
                return false;
            }
        }
        return true;

    }

    public static void DFS(int num, int jarisu)
    {
        if (jarisu == N)
        {
            if (isPrime(num))
            {
                System.out.println(num);
            }
            return;
        }

        for (int i = 1; i < 10; i += 2)
        {
            int value = num * 10 + i;
            if (isPrime(value))
            {
                DFS(value, jarisu + 1);

            }
        }


    }
}