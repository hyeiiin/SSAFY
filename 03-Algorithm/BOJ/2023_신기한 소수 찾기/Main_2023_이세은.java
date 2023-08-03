package ssafyStudy.BOJ;

import java.io.*;

public class Main_2023_이세은 {

    static int[] d = new int[]{1, 3, 5, 7, 9};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{

        int N = Integer.parseInt(br.readLine());

        //N자리수를 오름차순 출력, 모든 자리를 체크하기 때문에 한자리라도 합성수면 불가
        dfs(2, N);
        dfs(3, N);
        dfs(5, N);
        dfs(7, N);

        br.close();
        bw.close();
    }

    static private void dfs(int dn, int N) throws IOException{
        if(N==1){ //n이 1이되면 끝내기
            if(isPrime(dn)) { //마지막으로 n자리 수 소수 확인
                bw.write(dn + "\n");
                bw.flush();
            }
            return;
        }

        for(int i=0; i<5; i++){
            if(isPrime(dn*10+d[i])){
                dfs(dn*10+d[i], N-1);
            }
        }
    }

    //소수인지 판별하는 함수, 제곱근까지 나누어지는 수 있으면 소수아님
    static public boolean isPrime(int num) {
        int sqrtNum = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrtNum; i += 2) {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}
