package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class NumberPair {
    int absValue;
    int originalValue;

    public NumberPair(int absValue, int originalValue) {
        this.absValue = absValue;
        this.originalValue = originalValue;
    }
}

public class Main_11286_김도현 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<NumberPair> list = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                if (list.isEmpty()) {
                    System.out.println("0");
                } else {
                    NumberPair removed = list.poll();
                    System.out.println(removed.originalValue);
                }
            } else {
                list.add(new NumberPair(Math.abs(command), command));
                ArrayList<NumberPair> tempList = new ArrayList<>(list);
                Collections.sort(tempList, new Comparator<NumberPair>() {
                    @Override
                    public int compare(NumberPair o1, NumberPair o2) {
                        if (o1.absValue == o2.absValue) {
                            return Integer.compare(o1.originalValue, o2.originalValue);
                        }
                        return Integer.compare(o1.absValue, o2.absValue);
                    }
                });
                list = new ArrayDeque<>(tempList);
            }
        }
    }
}
