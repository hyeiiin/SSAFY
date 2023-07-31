import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int[] arr1, arr2;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String str = br.readLine(); //100
            result = 0;
            arr1 = new int[str.length()]; //1 0 0
            arr2 = new int[str.length()]; //0 0 0
            for (int j = 0; j < str.length(); j++) {
                arr1[j] = str.charAt(j) - '0';
            }
            dfs(0);
            System.out.println("#" + (i + 1) + " " + result);
        }
    }

    public static void dfs(int index) {
        if (index == arr1.length) {
            return;
        }

        if (arr1[index] != arr2[index]) {
            for (int k = index; k < arr1.length; k++) {
                arr2[k] = arr1[index];
            }
            result++;
        }
        dfs(index + 1);
    }
}
