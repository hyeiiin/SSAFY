package boj;

import java.io.*;
import java.util.*;

public class Main_11286_김형민 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int compare = Integer.compare(Math.abs(o1), Math.abs(o2));
            if (compare==0){
                return Integer.compare(o1,o2);
            }
            return compare;
        });
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num==0){
                if (queue.isEmpty()){
                    sb.append(0).append("\n");
                    continue;
                }
                Integer poll = queue.poll();
                sb.append(poll).append("\n");
                continue;
            }
            queue.add(num);
        }
        System.out.println(sb);
    }

}
