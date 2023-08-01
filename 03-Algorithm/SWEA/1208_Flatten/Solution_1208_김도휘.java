import java.io.*;
import java.util.*;

public class Main {
    static int[] heights;
    static int max, min;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            int dump = Integer.parseInt(br.readLine());
            heights = new int[100];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 100; j++) {
                heights[j] = Integer.parseInt(st.nextToken());
            }

            while (dump-- > 0) {
                Arrays.sort(heights);
                heights[0]++;
                heights[99]--;

            }
            Arrays.sort(heights);

            System.out.println("#" + (i + 1) + " " + (heights[99] - heights[0]));

        }
    }
}