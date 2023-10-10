import java.io.*;
import java.util.*;

public class Solution {
    private static ArrayDeque<Integer>[] topGear;
    private static int[] operation;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            String str;
            StringTokenizer st;
            topGear = new ArrayDeque[4];
            operation = new int[5];
            Arrays.fill(operation, 1);
            int k, pos, direction;
            k = Integer.parseInt(br.readLine());
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                topGear[i] = new ArrayDeque<>();
                for (int j = 0; j < 8; j++) {
                    topGear[i].add(Integer.parseInt(st.nextToken()));
                }
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                pos = Integer.parseInt(st.nextToken());
                direction = Integer.parseInt(st.nextToken());
                rotate(pos - 1, direction);
            }
            sb.append(getTwelve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void rotate(int pos, int direction) {
        visited = new boolean[4];
        Gear curr;
        Deque<Gear> q = new ArrayDeque<>();
        q.add(new Gear(pos, direction));
        visited[pos] = true;
        while (!q.isEmpty()) {
            curr = q.pollLast();
            if (isPossible(curr, -1)) {
                visited[curr.pos - 1] = true;
                q.add(new Gear(curr.pos - 1, -curr.direction));
            }
            if (isPossible(curr, 1)) {
                visited[curr.pos + 1] = true;
                q.add(new Gear(curr.pos + 1, -curr.direction));
            }
            if (curr.direction > 0) {
                topGear[curr.pos].addFirst(topGear[curr.pos].pollLast());
            } else {
                topGear[curr.pos].addLast(topGear[curr.pos].pollFirst());
            }
        }

    }

    public static int getTwelve() {
        int total = 0;
        int[] add = {1, 2, 4, 8};
        for (int i = 0; i < 4; i++) {
            total += topGear[i].peekFirst() * add[i];
        }
        return total;
    }

    public static boolean isPossible(Gear curr, int next) {
        int curr01, curr02, next01, next02;
        if (curr.pos + next >= 0 && curr.pos + next < 4) {
            if (!visited[curr.pos + next]) {
                if (next == 1) {
                    curr01 = topGear[curr.pos].pollFirst();
                    curr02 = topGear[curr.pos].pollFirst();
                    next01 = topGear[curr.pos + 1].pollLast();
                    if (topGear[curr.pos].peekFirst() != topGear[curr.pos + 1].peekLast()) {
                        topGear[curr.pos].addFirst(curr02);
                        topGear[curr.pos].addFirst(curr01);
                        topGear[curr.pos + 1].addLast(next01);
                        return true;
                    }
                    topGear[curr.pos].addFirst(curr02);
                    topGear[curr.pos].addFirst(curr01);
                    topGear[curr.pos + 1].addLast(next01);

                } else if (next == -1) {
                    next01 = topGear[curr.pos - 1].pollFirst();
                    next02 = topGear[curr.pos - 1].pollFirst();
                    curr01 = topGear[curr.pos].pollLast();
                    if (topGear[curr.pos].peekLast() != topGear[curr.pos - 1].peekFirst()) {
                        topGear[curr.pos - 1].addFirst(next02);
                        topGear[curr.pos - 1].addFirst(next01);
                        topGear[curr.pos].addLast(curr01);
                        return true;
                    }
                    topGear[curr.pos - 1].addFirst(next02);
                    topGear[curr.pos - 1].addFirst(next01);
                    topGear[curr.pos].addLast(curr01);
                }
            }
        }
        return false;
    }

    public static class Gear {
        int pos;
        int direction;

        public Gear(int pos, int direction) {
            this.pos = pos;
            this.direction = direction;
        }
    }
}
