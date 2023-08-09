
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 인풋값으로 푸는 경우

public class Solution_1233_사칙연산유효성검사_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, result, i;
        String[] inputs;
        for (int TC = 1; TC <= 10; TC++) {
            N = Integer.parseInt(br.readLine()); //정점의개수
            result = 1;  // 유효성 검사의 결과 :  기본값 1 설정
            for (i = 1; i <= N; i++) { 
                inputs = br.readLine().split(" "); 
                //정점의 값 정보만 들고옴
                	//연산자가 들어온 경우
                if (inputs[1].equals("+") || inputs[1].equals("-") || inputs[1].equals("*") || inputs[1].equals("/")) {
                	//자식 노드가 꽉 차야하는 성질을 가지고 있음
                	// length 무조건 4가 되어야함.
                	if (inputs.length != 4) {  // 자식노드가 없건, 하나만 있거나 하는 등
                		result = 0; // 유효하지 않음 
                        for (int j = i + 1; j <= N; j++)
                            br.readLine(); 
                        break;
                    }
                } else {  //정점에 숫자가 들어온 경우
                    if (inputs.length != 2) { // 숫자인 경우 자식노드 있으면 X
                    	//length는 무조건 2가 되어야함
                    	result = 0; //유효하지 않음
                        for (int j = i + 1; j <= N; j++)
                            br.readLine();
                        break;
                    }
                }
            }
            System.out.println("#" + TC + " " + result);
        }
    }
}