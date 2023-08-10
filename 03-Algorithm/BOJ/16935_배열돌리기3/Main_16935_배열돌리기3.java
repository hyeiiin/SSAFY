
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_배열돌리기3 {
	   
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, R; 
    static int[][] map;

    static void menu1() {
        N = map.length; 
        M = map[0].length; 
        for (int i=0, h=N/2; i<h; i++) {
            int[] tmp = map[i];
            map[i] = map[N-i-1];
            map[N-i-1] = tmp;
        }
    }

    static void menu2() {
        N = map.length;
        M = map[0].length;
        for (int i=0; i<N; i++)
        for (int j=0, h=M/2; j<h; j++) {
            int tmp = map[i][j];
            map[i][j] = map[i][M-j-1];
            map[i][M-j-1] = tmp;
        }
    }

    static void menu3() {
        N = map.length;
        M = map[0].length;
        int[][] tmp = new int[M][N];

        for (int i=0; i<N; i++)
        for (int j=0; j<M; j++)
        tmp[j][N-i-1] = map[i][j];

        map = tmp;
    }

    static void menu4() {
        N = map.length;
        M = map[0].length;
        int[][] tmp = new int[M][N];

        for (int i=0; i<N; i++)
        for (int j=0; j<M; j++)
        tmp[M-j-1][i] = map[i][j];

        map = tmp;
    }

	//전체 배열을 4등분하여 N/2, M/2 돌려주기.(시계 방향)
	//값은 각 범위에서 고정
    static void menu5(int count) { 
        N = map.length;
        M = map[0].length;
        for (int i=0, h=N/2; i<h; i++)
        for (int j=0, l=M/2; j<l; j++)
        for (int c=0; c<count; c++) {
            int tmp = map[i][j];
            map[i][j] = map[i+h][j];
            map[i+h][j] = map[i+h][j+l];
            map[i+h][j+l] = map[i][j+l];
            map[i][j+l] = tmp;
        }
    }

	//전체 배열을 4등분하여 N/2, M/2 돌려주기.(반시계 방향)
	//값은 각 범위에서 고정
    static void menu6(int count) {
        N = map.length;
        M = map[0].length;
        for (int i=0, h=N/2; i<h; i++)
        for (int j=0, l=M/2; j<l; j++)
        for (int c=0; c<count; c++) {
            int tmp = map[i][j];
            map[i][j] = map[i][j+l];
            map[i][j+l] = map[i+h][j+l];
            map[i+h][j+l] = map[i+h][j];
            map[i+h][j] = tmp;
        }
    }

    // 180도 뒤집기
    static void turn() {
        N = map.length;
        M = map[0].length;
        for (int i=0, h=N/2; i<h; i++) {
            for (int j=0; j<M; j++) {
                int tmp = map[i][j];
                map[i][j] = map[N-i-1][M-j-1];
                map[N-i-1][M-j-1] = tmp;
            }
        }
    }

    static void menu(int option, int count) {
        count %=4; //같은 연산 4번이면 초기값과 같으므로 나머지 연산 이용
        switch (option) {
            case 1: if (count%2!=0) menu1(); break; //짝수면 뒤집기 의미 없으므로 홀수일때만 수행
            case 2: if (count%2!=0) menu2(); break; //짝수면 뒤집기 의미 없으므로 홀수일때만 수행
            case 3: //90도 돌리는 연산의 경우 짝수면 180도 돌아감->뒤집기 연산으로 수행
                if (count>=2) turn();
                if (count%2==1) menu3(); //홀수면 90도 돌려주기
                break;
            case 4: //위와 동일
                if (count>=2) turn();
                if (count%2==1) menu4();
                break;
            case 5: menu5(count); break; //4등분 후 돌리는 연산의 경우 count 횟수만큼 돌려줘야함.
            case 6: menu6(count); break;
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()); //명령의 수 받기
        map = new int[N][M]; //배열 생성

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++)
            map[i][j] = Integer.parseInt(st.nextToken()); //배열 값 입력 받기
        }

        st = new StringTokenizer(br.readLine());
        int preOption = Integer.parseInt(st.nextToken());
        int count = 1; //같은 연산이 여러번 들어오는 경우 사용하는 변수
        for (int r=1; r<R; r++) { //옵션이 여러가지 인 경우
            int option = Integer.parseInt(st.nextToken());
            if (preOption == option) count++; //앞 연산과 같은 연산이면 count 수 증가
            else { //앞 연산과 다른 연산인 경우
                menu(preOption, count); //앞 연산 실행
                preOption = option; //입력 받은 연산을 앞연산에 셋팅해주기
                count = 1; //count 초기화(연산 했으니까!)
            }
        }
        menu(preOption, count); //연산이 단 하나인경우 혹은 여러 연산의 마지막 연산 수행

		//출력 부분
        StringBuilder sb = new StringBuilder();
        N = map.length; M = map[0].length;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++)
            sb.append(map[i][j]+" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
