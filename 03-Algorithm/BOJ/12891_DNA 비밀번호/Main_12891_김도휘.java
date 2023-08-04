import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] checkArray;
    static int[] myArray;
    static int checkEachDnaChar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String str = br.readLine(); //임시 문자열
        checkArray = new int[4]; //조건의 비밀번호 개수를 설정하는 배열
        myArray = new int[4]; //탐색하는 윈도우가 가진 비밀번호 개수 카운팅 배열
        char[] inputArr = str.toCharArray();
        checkEachDnaChar = 0;
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArray[i] = Integer.parseInt(st.nextToken());
            //최소 개수가 0개라면 이미 조건 충족한 인덱스이므로 카운트
            if (checkArray[i] == 0) {
                checkEachDnaChar++;
            }
        }
        for (int i = 0; i < P; i++) {
            add(inputArr[i]);
        }
        if (checkEachDnaChar == 4) {
            result++;
        }
        for (int i = P; i < S; i++) { //2,3
            int j = i - P; //0,1
            add(inputArr[i]); //2,3
            remove(inputArr[j]); //0,1

            if (checkEachDnaChar == 4) {
                result++;
            }
        }
        System.out.println(result);
    }

    public static void add(char c) {
        switch (c) {
            case 'A':
                myArray[0]++;
                if (checkArray[0] == myArray[0]) {
                    checkEachDnaChar++;
                }
                break;
            case 'C':
                myArray[1]++;
                if (checkArray[1] == myArray[1]) {
                    checkEachDnaChar++;
                }
                break;
            case 'G':
                myArray[2]++;
                if (checkArray[2] == myArray[2]) {
                    checkEachDnaChar++;
                }
                break;
            case 'T':
                myArray[3]++;
                if (checkArray[3] == myArray[3]) {
                    checkEachDnaChar++;
                }
                break;
        }
    }
    public static void remove(char c) {
        switch (c) {
            case 'A':
                if (checkArray[0] == myArray[0]) {
                    checkEachDnaChar--;
                }
                myArray[0]--;
                break;
            case 'C':
                if (checkArray[1] == myArray[1]) {
                    checkEachDnaChar--;
                }
                myArray[1]--;
                break;
            case 'G':
                if (checkArray[2] == myArray[2]) {
                    checkEachDnaChar--;
                }
                myArray[2]--;
                break;
            case 'T':
                if (checkArray[3] == myArray[3]) {
                    checkEachDnaChar--;
                }
                myArray[3]--;
                break;
        }
    }
}
