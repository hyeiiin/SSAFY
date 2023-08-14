import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static String command;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 10; t++) {
            N = Integer.parseInt(br.readLine()); //원본 암호문의 길이
            List<String> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                list.add(st.nextToken()); //원본 암호문
            }
            int commandNum = Integer.parseInt(br.readLine()); //명령어 개수
            command = br.readLine();
            StringTokenizer str = new StringTokenizer(command, "I");
            for (int i = 0; i < commandNum; i++) {
                String commandLine = str.nextToken(); //'I'기준으로 자르기
                StringTokenizer str2 = new StringTokenizer(commandLine);
                int x = Integer.parseInt(str2.nextToken());
                int y = Integer.parseInt(str2.nextToken());

                while (str2.hasMoreTokens()) {
                    list.add(x++, str2.nextToken());
                }

            }
            sb.append("#" + (t + 1) + " ");
            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i) + " ");
            }
            sb.append("\n");

        }
        System.out.println(sb);

    }
}
