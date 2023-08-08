import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1233 {
    
    static int N, NUM, START, END, ANSWER;
    static String[][] INPUT;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int TC = 1; TC < 11; TC++) {
            
            N = Integer.parseInt(br.readLine());
            
            NUM = 1;
            while (NUM <= N/2) NUM *= 2;
            
            START = NUM/2;
            END = NUM-1;
            
            INPUT = new String[N][4];
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = 0;
                while (st.hasMoreTokens()) {
                    INPUT[i][idx++] = st.nextToken();
                }
            }
            
            ANSWER = 1;
            for (int i = START - 1; i < END; i++) {
                if (INPUT.length == 4) {
                    if ((INPUT[i*2][1].equals("+") || INPUT[i*2][1].equals("-") || INPUT[i*2][1].equals("*") || INPUT[i*2][1].equals("/")) && (INPUT[i*2+1][1].equals("+") || INPUT[i*2+1][1].equals("-") || INPUT[i*2+1][1].equals("*") || INPUT[i*2+1][1].equals("/"))) {
                        ANSWER = 0;
                        break;
                    }
                }
                else {
                    if ((INPUT[i][1].equals("+") || INPUT[i][1].equals("-") || INPUT[i][1].equals("*") || INPUT[i][1].equals("/"))) {
                        ANSWER = 0;
                        break;
                    }
                }
            }
            
            System.out.printf("#%d %d\n", TC, ANSWER);
            
//            for (int i = 0; i < INPUT.length; i++) {
//                for (int j = 0; j < INPUT[i].length; j++) {
//                    System.out.print(INPUT[i][j] + " ");
//                }
//                System.out.println();
//            }
            
        }
        
    }
}
