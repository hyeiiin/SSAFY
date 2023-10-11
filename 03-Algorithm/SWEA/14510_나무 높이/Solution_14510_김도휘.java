import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution
{
    static int mindate;
    static List<Integer> list;
 
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(br.readLine()); //나무의 개수
            int[] trees = new int[N];
            list = new ArrayList<>();
            mindate = Integer.MAX_VALUE;
 
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
            {
                trees[i] = Integer.parseInt(st.nextToken()); //나무 키
            }
            Arrays.sort(trees); //오름차순
            int maxHeight = trees[N - 1];
            int even = 0;
            int odd = 0;
            for (int i = 0; i < N - 1; i++)
            {
                int diff = maxHeight - trees[i];
                if (diff == 0)
                {
                    continue;
                }
                even += diff / 2;
                odd += diff % 2;
            }
            if (even > odd)
            {
                while (Math.abs(even - odd) > 1)
                {
                    even--;
                    odd += 2;
                }
            }
            int result = 0;
            if (odd > even)
            {
                result = odd * 2 - 1;
            } else if (odd < even)
            {
                result = even * 2;
            } else
            {
                result = even + odd;
            }
 
            sb.append("#" + (t + 1) + " ");
            sb.append(result);
            sb.append("\n");
        }
        System.out.println(sb);
    }
 
}
