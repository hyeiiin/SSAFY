#include <bits/stdc++.h>
using namespace std;
#define INF 1e9
int n;
const int dr[4] = { -1, 1, 0, 0 };
const int dc[4] = { 0, 0, -1, 1 };
struct Pos {
	int r, c, cost;
};
struct comp {
	bool operator() (Pos a, Pos b) {
		return a.cost > b.cost;
	}
};
bool valid(int r, int c) {
	return 0 <= r && r < n && 0 <= c && c < n;
}
int main() {
	int t = 0;
	int a[125][125];
	while (true) {
		cin >> n; if (n == 0) break;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> a[i][j];
			}
		}
		bool v[125][125];
		memset(v, false, sizeof(v));
		priority_queue<Pos, vector<Pos>, comp> pq;
		pq.push({ 0, 0, a[0][0] });
		while (!pq.empty()) {
			Pos pos = pq.top(); pq.pop();
			v[pos.r][pos.c] = true;
			if (pos.r == n - 1 && pos.c == n - 1) {
				cout << "Problem " << ++t << ": " << pos.cost << endl;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = pos.r + dr[i], nc = pos.c + dc[i];
				if (valid(nr, nc) && !v[nr][nc]) {
					int ncost = pos.cost + a[nr][nc];
					pq.push({ nr, nc, ncost });
				}
			}
		}
	}
}