package swea;

import java.io.*;
import java.util.*;
public class Solution_1233_김형민 {
    static StringBuilder sb;

    public static void main(String[] args) throws  IOException{
        System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\CodingTest\\src\\swea\\input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= 10; t++) {
            sb.append("#").append(t).append(" ");
            int n = Integer.parseInt(br.readLine());
            int ans = 1;

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                st = new StringTokenizer(line);
                st.nextToken();
                String code = st.nextToken();
                if (!st.hasMoreTokens()) {
                    if (code.equals("-")||code.equals("+")||code.equals("*")||code.equals("/")){
                        ans=0;
                    }
                    continue;
                }
                if (code.equals("-")||code.equals("+")||code.equals("*")||code.equals("/"))continue;
                ans = 0;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }
}