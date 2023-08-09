package com.ssafy.hw.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286_탁하윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pque = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // o1 에 우선순위를 주고 싶다면, 음수값 return
                // o1과 o2의 절대 값이 같다면 음수에 더 높은 우선순위
                if(Math.abs(o1) == Math.abs(o2)) {
                    return o1-o2;
                } else if(Math.abs(o1) > Math.abs(o2)) {
                    return Math.abs(o1) - Math.abs(o2);
                }
                return -1;
            }
        });    // [힙] 낮은 숫자가 우선순위

        for(int i=0; i<N; i++) {
            int in = Integer.parseInt(br.readLine());    // 연산 정보
            if(in == 0) {   // 입력 값이 0이라면 queue가 비어있다면 0, 아니라면 poll 해야함
                if(pque.isEmpty()) {
                    System.out.println(0);
                }else {
                    System.out.println(pque.poll());
                }
            }else { // 0이 아니라면 입력 값이니까 offer
                pque.offer(in);
            }
        }

    }

}

