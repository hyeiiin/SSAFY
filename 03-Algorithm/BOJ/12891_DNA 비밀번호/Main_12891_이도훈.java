import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891_이도훈 {

    // 각 단어의 개수 체크 배열
    static int[] dna;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String input = br.readLine();

        st = new StringTokenizer(br.readLine());

        dna = new int[4];
        for (int i = 0; i < 4; i++) {
            dna[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터를 이용해서 범위 지정 
        int left = 0;
        int right = P-1;

        // 범위 지정한만큼 단어 개수 변경
        for (int i = 0; i < P; i++) {
            char cur = input.charAt(i);

            switch (cur) {
                case 'A':
                    dna[0]--;
                    break;
                case 'C':
                    dna[1]--;
                    break;
                case 'G':
                    dna[2]--;
                    break;
                case 'T':
                    dna[3]--;
                    break;
            }
        }

        int cnt = 0;
        while (right < S-1) {
            if (isPossible()) {
                cnt++;
            }

            // 왼쪽 증가
            switch (input.charAt(left++)) {
                case 'A':
                    dna[0]++;
                    break;
                case 'C':
                    dna[1]++;
                    break;
                case 'G':
                    dna[2]++;
                    break;
                case 'T':
                    dna[3]++;
                    break;
            }
            // 오른쪽 증가
            switch (input.charAt(++right)) {
                case 'A':
                    dna[0]--;
                    break;
                case 'C':
                    dna[1]--;
                    break;
                case 'G':
                    dna[2]--;
                    break;
                case 'T':
                    dna[3]--;
                    break;
            }
        }

        if(isPossible()) cnt++;

        System.out.println(cnt);


    }

    // dna배열 돌면서 최소 개수 이상인지 확인
    public static boolean isPossible() {
        for (int i = 0; i < 4; i++) {
            if (dna[i] > 0) {
                return false;
            }
        }
        return true;
    }

}
