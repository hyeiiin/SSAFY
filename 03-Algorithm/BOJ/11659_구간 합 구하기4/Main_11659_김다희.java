import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int[] total=new int[n];
        int x,y;
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            if(i==0) total[i]=Integer.parseInt(st.nextToken());
            else total[i]=total[i-1]+Integer.parseInt(st.nextToken());
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            x=Integer.parseInt(st.nextToken())-1;
            y=Integer.parseInt(st.nextToken())-1;
            if(x==0) sb.append(total[y]).append("\n");
            else sb.append(total[y]-total[x-1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
