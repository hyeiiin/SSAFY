package com.ssafy.hw.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891_탁하윤 {
    static int S, P, cnt;
    static char[] passwd;
    static int[] DNA = new int[4];
    static int[] temp = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());   // DNA 문자열 길이
        P = Integer.parseInt(st.nextToken());   // 부분 문자열의 길이

        passwd = br.readLine().toCharArray();   // 길이가 S인 DNA 문자열

        st = new StringTokenizer(br.readLine());
        DNA[0] = Integer.parseInt(st.nextToken());  // A
        DNA[1] = Integer.parseInt(st.nextToken());  // C
        DNA[2] = Integer.parseInt(st.nextToken());  // G
        DNA[3] = Integer.parseInt(st.nextToken());  // T

        findPasswd();

    }

    private static void findPasswd(){

        for(int i=0; i<P; i++){ // 초기화
            if(passwd[i] == 'A') temp[0]++;
            if(passwd[i] == 'C') temp[1]++;
            if(passwd[i] == 'G') temp[2]++;
            if(passwd[i] == 'T') temp[3]++;
        }

        if(isTrue()) cnt++; // 만약 초기화 한 값에 DNA가 있다면

        for(int i=P; i<S; i++){ // 바뀌는 값들만 작업
            int j = i-P;
            if(passwd[j] == 'A') temp[0]--;
            if(passwd[j] == 'C') temp[1]--;
            if(passwd[j] == 'G') temp[2]--;
            if(passwd[j] == 'T') temp[3]--;

            if(passwd[i] == 'A') temp[0]++;
            if(passwd[i] == 'C') temp[1]++;
            if(passwd[i] == 'G') temp[2]++;
            if(passwd[i] == 'T') temp[3]++;

            if(isTrue()) cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean isTrue(){
        for(int i=0; i<4; i++){ // 'A', 'C', 'G', 'T' 개수 세기
            if(temp[i]<DNA[i]){ // temp가 DNA보다 작으면 false
                return false;
            }
        }
        return true;
    }

}
