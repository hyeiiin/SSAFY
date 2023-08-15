package ssafyStudy.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16435_이세은 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //과일 개수
        int l = Integer.parseInt(st.nextToken()); //초기 길이

        List<Integer> fruits = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            fruits.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(fruits); //차례대로 먹기 위한 정렬
        int i=0;

        while(i<fruits.size()){
            if(l<fruits.get(i))
                break;
            l++;i++;
        }
        System.out.println(l);
    }
}
