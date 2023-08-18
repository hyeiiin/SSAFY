import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//고객 정보를 저장한 클래스
class customer {
    int row;
    int col;
 
    public customer(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }
 
}
 
public class Solution_1247_최적경로 {
 
    static int N, min;
    static customer[] customers; //고객들 담을 배열
    static customer start; //시작위치 저장
    static customer end; //도착위치 저장
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int tc = Integer.parseInt(br.readLine());
 
        for (int test = 1; test <= tc; test++) {
            N = Integer.parseInt(br.readLine()); // 고객의 수
            customers = new customer[N]; // 고객의 위치 정보
            int[] set = new int[N]; // index 정보
            min = Integer.MAX_VALUE; // 최소 거리
 
            st = new StringTokenizer(br.readLine());
 
            // 시작 & 도착 위치 정보
            start = new customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            end = new customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
 
            // 고객 위치 정보 저장
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                customers[i] = new customer(x, y);
            }
 
            for (int i = 0; i < N; i++) {//인덱스 세팅
                set[i] = i;
            }

            np(set, 0, N);
 
            System.out.println("#" + test + " " + min);
 
        }
 
    }
    // 순열 만들기
    /*
     * @param set : 고객 인덱스 정보
     * @param depth : 현재 위치
     * @param k : 고객 수
     */
    static void np(int[] set, int depth, int k) {
        if (depth == k) { // 기저조건
            int s = solve(set); // 거리 계산
 
            min = min > s ? s : min; // 최저값 계산
            return;
        }
 
        for (int i = depth; i < k; i++) { // depth를 기준으로
            swap(set, i, depth); // 순열 위치 swap
            np(set, depth + 1, k); // depth+1 재귀 호출
            swap(set, i, depth); // 원상 복구
        }
    }
 
    // swap - 순열 위치 바꾸기
    /*
     * @param set :고객 인덱스 정보
     * @param i : i
     * @param index : 현재 위치(depth)들어옴
     */
    static void swap(int[] set, int i, int index) {
        int temp = set[i];
        set[i] = set[index];
        set[index] = temp;
    }
    
    //회사부터 집까지 전체 거리 계산
    static int solve(int[] set) {
        int sum = 0;
        sum += getDistance(start, customers[set[0]]); // 회사-첫 고객 거리
        
        for (int i = 0; i < set.length - 1; i++) {
           //순열 idx와 그 다음 idx 대입
            sum += getDistance(customers[set[i]], customers[set[i + 1]]); // 고객간의 거리
        }
        
        sum += getDistance(customers[set[set.length - 1]], end); // 마지막 고객-집의 거리
 
        return sum;
    }
 
    // 거리 계산
    static int getDistance(customer c1, customer c2) {
        return Math.abs(c1.row - c2.row) + Math.abs(c1.col - c2.col);
    }
 
}