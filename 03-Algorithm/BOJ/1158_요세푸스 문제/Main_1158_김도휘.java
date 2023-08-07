import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        int index = K - 1;
        bw.write("<");

        while (list.size() > 1) { //사이즈 1개 남을 때까지

            int out = list.remove(index);
            bw.write(out + ", ");
            index += K;
            index--;
            if (index >= list.size()) {
                index = index % list.size();
            }

        }
        bw.write(list.remove(0) + ">");
        bw.flush();

    }
}