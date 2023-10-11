#include <bits/stdc++.h>
using namespace std;
#define NONE 0
#define ROW 1
#define COL 2
int sx, sy, ex, ey;
struct S {
	int t, x, y, move;
};
bool visit[201][201][3];
const int dx[] = { 1, -1 };
const int dy[] = { 1, -1 };
bool valid(int x, int y) {
	return 0 <= x && x <= 200 && 0 <= y && y <= 200;
}
int BFS(S init) {
	memset(visit, false, sizeof(visit));
	visit[init.x][init.y][init.move] = true;
	queue<S> q;
	q.push(init);
	while (!q.empty()) {
		S cur = q.front(); q.pop();
		int t = cur.t, x = cur.x, y = cur.y;
		int move = cur.move;
		if (x == ex && y == ey) return t;
		if (move != ROW) {
			for (int i = 0; i < 2; i++) {
				int nx = x + dx[i], ny = y;
				if (valid(nx, ny) && !visit[nx][ny][ROW]) {
					visit[nx][ny][ROW] = true;
					q.push({ t + 1, nx, ny, ROW });
				}
			}
		}
		if (move != COL) {
			for (int i = 0; i < 2; i++) {
				int nx = x, ny = y + dy[i];
				if (valid(nx, ny) && !visit[nx][ny][COL]) {
					visit[nx][ny][COL] = true;
					q.push({ t + 1, nx, ny, COL });
				}
			}
		}
	}
}
int main() {
	int t; cin >> t;
	for (int tc = 1; tc <= t; tc++) {
		cin >> sx >> sy >> ex >> ey;
		sx += 100, sy += 100, ex += 100, ey += 100;
		cout << "#" << tc << " " << BFS({ 0, sx, sy, NONE }) << endl;
	}
}