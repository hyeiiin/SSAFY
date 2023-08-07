package swea;
import java.io.*;
import java.util.*;

public class Solution_1228_김형민 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\CodingTest\\src\\swea\\input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer inputTokens;
        StringTokenizer comdTokens;
        int T = 10;
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            LinkedList<Integer> list = new LinkedList<>();


            int inputLen = Integer.parseInt(br.readLine());
            inputTokens = new StringTokenizer(br.readLine());
            int comdLen = Integer.parseInt(br.readLine());
            comdTokens = new StringTokenizer(br.readLine());

            for (int input = 0; input < inputLen; input++) {
                list.add(Integer.parseInt(inputTokens.nextToken()));
            }
            int num = 0;
            while (num<comdLen) {
                String l = comdTokens.nextToken();
                if (l.equals("I")) num++;
                int X = Integer.parseInt(comdTokens.nextToken());
                int Y = Integer.parseInt(comdTokens.nextToken());

                for (int y = 0; y < Y; y++) {
                    list.add(X++,Integer.parseInt(comdTokens.nextToken()));
                }
            }

            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);


    }
}
