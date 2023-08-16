import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1074_Z {
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int N = Integer.parseInt(inputs[0]); //2^N*2^N 을 만들기 위한 입력
        r = Integer.parseInt(inputs[1]); //r,c 입력
        c = Integer.parseInt(inputs[2]);
        
        int pow_n = (int) Math.pow(2, N);


        recursive(0,0, pow_n, 0);

    }

    
   /**
    * 
    * @param si : 시작 i
    * @param sj : 시작 j
    * @param length : 시작점부터 해당 범위까지의 길이
    * @param cnt : (si,sj)순서
    */
    public static void recursive(int si, int sj, int length, int cnt){ 
    	// 2는 쪼갤 수 있는 단위의 최소(사각형 길이의 최소)
    	// 1은 완전히 다 쪼개진 상태
    	if(length==1){
            for (int i = si; i <= si+length; i++) {
                for (int j = sj; j <= sj+length; j++) {
                    if(i==r && j==c){
                        System.out.println(cnt);
                        return;
                    }
                    cnt++;
                }
            }
            return;
        }

        int half = length/2;

        //4개의 범위 중 r,c가 속한 범위로 들어감
        if(si<=r && r<si+half && sj<=c && c<sj+half){ 
            recursive(si, sj, half, cnt);
        }else if(si<=r && r<si+half && sj+half<=c && c<sj+length){
            recursive(si, sj+half, half, cnt+half*half);
        }else if(si+half<=r && r<si+length && sj<=c && c<sj+half){
            recursive(si+half, sj, half, cnt+half*half*2);
        }else{
            recursive(si+half, sj+half, half, cnt+half*half*3);
        }
    }
}