
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_괄호짝짓기_2 {
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=10;
        int Answer;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            Answer = 1; 

            int N =Integer.parseInt(br.readLine().trim()); 
            String line = br.readLine(); // 괄호 정보 받기
            int size = line.length(); // String 사이즈 
            Stack<Character> stack = new Stack<Character>(); 
            if(size %2 == 1) { // 혹시나 문자열의 길이가 홀수 들어왔을때 >> 무조건 괄호 남음
                Answer = 0;
            }else {
                for (int i = 0 ; i <size; i++) {
                    char ch = line.charAt(i);  //String 문자열에서 괄호 하나씩 Char 가져오기
                    if(ch=='{' || ch=='(' || ch=='[' || ch=='<') { //열린괄호 이면 Stack에 넣기
                    	
                        stack.push(ch);
                    }else { //닫힌 괄호 일대, 스택이 버있으면 X/ 뺀 열리는 괄호가 닫히는 괄호와 다르면 X
                        if(stack.isEmpty() || stack.pop() != getPair(ch)) { 
                            Answer = 0; // 유효하지 않음으로 변경
                            break;
                        }
                    }
                }
                if(!stack.isEmpty()) { //스택에 남아있는 열리는 괄호가 있는지 확인
                    Answer=0;
                }
            }
            System.out.print("#"+test_case+" "+Answer);
            System.out.println();
        }
    }
    public static char getPair(char ch) { 
        switch (ch) { //닫히는 괄호 들어오면 그와 일치하는 열린 괄호 리턴
            case '}':    return '{';
            case ')':    return '(';
            case ']':    return '[';
            case '>':    return '<';
        }
        return ' ';
    }
}
