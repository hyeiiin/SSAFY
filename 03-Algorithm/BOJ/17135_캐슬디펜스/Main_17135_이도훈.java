import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17135_이도훈 {

    static int N;
    static int M;
    static int D;
    static LinkedList<Enemy> enemies;
    static int max = Integer.MIN_VALUE;
    static Archer[] archers = new Archer[3];


    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        enemies = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            archers[i] = new Archer();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) {
                    enemies.add(new Enemy(j, i));
                }
            }
        }

        Collections.sort(enemies, (o1, o2) -> {
            if (o1.x == o2.x) {
                return o2.y - o1.y;
            }
            return o1.x - o2.x;
        });


        // 궁수 최대 위치 경우의 수: 15C3 = 455
        comb(0, -1);
        System.out.println(max);
    }

    static void comb(int depth, int prev) {
        if (depth == 3) {

            List<Enemy> copy = copy(enemies);

            // 디펜스 시작
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (copy.size() == 0) break;
                // 화살 발사
                Enemy one = archers[0].fire(copy);
                Enemy two = archers[1].fire(copy);
                Enemy three = archers[2].fire(copy);

                if (copy.remove(one)) {
                    cnt++;
                }
                if (copy.remove(two)) {
                    cnt++;
                }
                if (copy.remove(three)) {
                    cnt++;
                }

                // 이동
                if (enemies.size() != 0) {
                    int copyIdx = 0;
                    while (copyIdx < copy.size()) {
                        Enemy cur = copy.get(copyIdx);
                        if (!cur.move()) {
                            copy.remove(cur);
                        } else {
                            copyIdx++;
                        }
                    }
                }
            }

            max = Math.max(max, cnt);
            return;
        }

        for (int i = prev + 1; i < M; i++) {
            archers[depth].x = i;
            comb(depth + 1, i);
        }
    }

    static List<Enemy> copy(List<Enemy> list) {
        List<Enemy> result = new LinkedList<>();
        for (Enemy enemy : list) {
            result.add(enemy.copy());
        }
        return result;
    }


    static class Archer {
        int x;
        int y;
        int range;

        public Archer() {
            this.y = N;
            this.range = D;
        }

        public Enemy fire(List<Enemy> enemies) {
            int min = range + 1;
            Enemy result = null;
            for (Enemy enemy : enemies) {
                int dist = Math.abs(enemy.x - this.x) + Math.abs(enemy.y - this.y);
                if (dist < min) {
                    min = dist;
                    result = enemy;
                }
            }
            return result;
        }
    }

    static class Enemy {
        int x;
        int y;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Enemy copy() {
            return new Enemy(this.x, this.y);
        }

        public boolean move() {
            y++;

            if (y == N) return false;

            return true;
        }
    }


}

