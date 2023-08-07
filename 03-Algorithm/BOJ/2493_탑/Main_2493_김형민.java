package boj;
import java.io.*;
import java.util.*;
/**
 * 배열에서 가장 가까운 자신보다 큰 값을 찾는 문제?
 *
 * 스택을 활용함
 *
 * 스택을 생성한 뒤 세가지 조건을 세웠다.
 *
 * 1. 스택이 비어 있다면 스택에 arr[idx]을 넣고 스트링 빌더에 0을 추가한다.
 * 2. arr[idx]가 스택.peek() 보다 크다면 arr[idx]보다 큰 값 or 스택이 비어질때까지 스택을 뽑는다.
 * 3. arr[idx]가 스택.peek() 보다 작다면 arr[idx]를 넣고 스트링 빌더에 idx+1을 추가한다.
 */
public class Main_2493_김형민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> numToIndexMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int idx=0; idx<n; idx++){
            int num = Integer.parseInt(st.nextToken());
            numToIndexMap.put(num,idx+1);

            while(!stack.empty()&&num>stack.peek()){
                stack.pop();
            }

            if (stack.empty()){
                stack.push(num);
                sb.append(0).append(" ");
                continue;
            }

            if (num<=stack.peek()){
                sb.append(numToIndexMap.get(stack.peek())).append(" ");
                stack.push(num);
            }
        }
        System.out.println(sb.toString());

    }
}
