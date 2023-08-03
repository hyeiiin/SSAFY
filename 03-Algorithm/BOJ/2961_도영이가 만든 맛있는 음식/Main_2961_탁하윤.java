package com.ssafy.hw.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_탁하윤 {
    static int source, S[], B[];
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        source = Integer.parseInt(br.readLine());    // 재료 개수
        S = new int[source];    // 신맛 저장하기 위한 배열
        B = new int[source];    // 쓴맛 저장하기 위한 배열
        
        // 신맛 쓴맛 초기화
        for (int i = 0; i < source; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        getDif(0, 1, 0, 0); // 신맛 쓴맛의 최소값 구하기
        System.out.println(MIN);

    }

    private static void getDif(int cnt, int smat, int bmat, int selectedCount) {
        // cnt : 직전까지 고려된 원소의 개수, 현재 처리할 원소의 인덱스
        // smat: 직전까지 선택된 원소들의 신맛 곱하기
        // bmat: 직전까지 선택된 원소들의 쓴맛 더하기
        if (cnt == source) {
            if (selectedCount > 0) {
                // 재료를 모두 탐색하고 뽑힌 재료의 신맛 쓴맛 차이 구하기
                MIN = Math.min(MIN, Math.abs(smat-bmat));
            }
            return;
        }
        
        // cnt번째 재료 넣기
        getDif(cnt + 1, smat*S[cnt], bmat+B[cnt], selectedCount+1);
        // cnt번째 재료 안넣기
        getDif(cnt + 1, smat, bmat, selectedCount);

    }
}

