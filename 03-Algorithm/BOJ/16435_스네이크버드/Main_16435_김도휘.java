import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //과일의 개수
        int L = Integer.parseInt(st.nextToken()); //뱀 초기 길이
        int[] fruits = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int nowLength = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        while (!pq.isEmpty()) {
            if (nowLength >= pq.peek()) {
                nowLength++;
                pq.poll();
            }
            else {
                break;
            }
        }
        System.out.println(nowLength);
    }
}
