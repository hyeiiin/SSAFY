import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
    static List<Food> foodList;
    static int N, L;
    static int max;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //재료의 개수
            L = Integer.parseInt(st.nextToken()); //제한 칼로리
            foodList = new ArrayList<>();
            max = Integer.MIN_VALUE;
 
            for (int t = 0; t < N; t++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calories = Integer.parseInt(st.nextToken());
                foodList.add(new Food(score, calories));
            }
            cooking(0, 0, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("#" + (i + 1) + " ");
            sb.append(max);
            System.out.println(sb);
 
        }
 
    }
 
    public static void cooking(int index, int sumCalories, int sumScore) {
        if (sumCalories <= L) {
            max = Math.max(max, sumScore);
        }
        if (sumCalories > L) {
            return;
        }
        if (index == N) {
            return;
        }
 
        //이 재료를 고를 때
        cooking(index + 1, sumCalories + foodList.get(index).calories, sumScore + foodList.get(index).score);
        //이 재료를 고르지 않을 때
        cooking(index + 1, sumCalories, sumScore);
 
 
    }
}
 
class Food{
    int score, calories;
 
    public Food(int score, int calories) {
        this.score = score;
        this.calories = calories;
    }
}
