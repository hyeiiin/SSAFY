import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Node> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            int result = 0;
            
            while (!stack.isEmpty()) {
                Node temp = stack.peek();
                if (height < temp.height) {
                    result = temp.index;
                    break;
                }
                else {
                    stack.pop();
                }
            }
            sb.append(result).append(" ");
            stack.push(new Node((i + 1), height));

        }
        System.out.println(sb);

    }
}
class Node
{
    int index, height;
    public Node(int index, int height) {
        this.index = index;
        this.height = height;
    }
}
