import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main_2493_탑{
  
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 한개의 Stack으로 관리
        Stack<Tower> stack = new Stack<Tower>();
        int height, position; //높이, 순서
        int N = Integer.parseInt(br.readLine()); // 탑의 개수 
          
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (position=1; position<=N; position++) {
            height = Integer.parseInt(st.nextToken()); //높이 정보를 받아옴
            //stack이 비어있지 않을 때  
            while(!stack.isEmpty()) {  //stack에 남아있는 건물의 정보 몇개 인지 알 수 없음.
                if(stack.peek().height >= height) { // 가장 위의 값과 높이 값을 비교 가장의 값이 크면
                    System.out.print(stack.peek().position + " ");// 건물 높이 출력
                    break;
                }
                stack.pop(); //가장 위의 값을 빼버림. 반복문 돌면서 비교
                // 나보다 작은애들이면, 내 뒤에 오는 건물들은 결국 내가 송신받기 때문에 이전 건물들 고려할 필요 없음.
            }
            if(stack.isEmpty()) //처음 들어온 건물
                System.out.print(0 + " ");
            //건물 정보 넣기.(객체 만들어서 넣기)  
            stack.push(new Tower(height, position));
        }
          
          
        br.close();
    }
    
    //자바의 객체를 활용
    static class Tower {
        int height;    
        int position;       
          
        public Tower(int height, int position) {
            this.height = height;
            this.position = position;
        }
    }
  
}