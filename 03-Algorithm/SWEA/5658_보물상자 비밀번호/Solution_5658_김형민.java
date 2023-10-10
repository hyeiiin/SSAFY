import java.util.*;
import java.io.*;

class Solution {
    static int N, K, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            ArrayDeque<String> q = new ArrayDeque<>();
            HashSet<Integer> nums = new HashSet<>();
            HashSet<String> numString = new HashSet<>();

            String input = br.readLine();
            String temp = "";
            for (int i = 0; i < N; i++) {
                String inp = String.valueOf(input.charAt(i));
                q.add(inp);
                temp+=inp;
                if ((i+1)%(N/4)==0){
                    nums.add(Integer.parseInt(temp,16));
                    numString.add(temp);
                    temp="";
                }
            }
            for (int i = 0; i < (N/4); i++) {
                String s = q.pollLast();
                q.addFirst(s);
                int j = 0;
                for (String inp : q) {
                    temp+=inp;
                    j++;
                    if (j%(N/4)==0){
                        nums.add(Integer.parseInt(temp,16));
                        numString.add(temp);
                        temp="";
                    }
                }
            }
            Integer[] array = nums.toArray(new Integer[nums.size()]);
            Arrays.sort(array, Comparator.reverseOrder());
            ans = array[K-1];
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }

}
