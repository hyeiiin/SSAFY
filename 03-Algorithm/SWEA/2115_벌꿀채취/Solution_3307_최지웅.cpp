#include <bits/stdc++.h>
using namespace std;
#define endl '\n'
void fastIO() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
}
int n, m, c;
const int MAXN = 10;
int a[MAXN][MAXN];
bool v[MAXN][MAXN];
bool comp(int a, int b) {
	return a > b;
}
int collect(int y, int x) {
	int sum = 0;
	int mx = 0;
	vector<int> list;
	for (int dx = 0; dx < m; dx++) {
		list.push_back(a[y][x + dx]);
	}
	for (int bits = 0; bits < 1 << m; bits++) {
		int sum = 0;
		for (int i = 0; i < m; i++) {
			if (bits & (1 << i)) {
				sum += list[i];
			}
		}
		if (sum <= c) {
			int profit = 0;
			for (int i = 0; i < m; i++) {
				if (bits & (1 << i)) {
					profit += list[i] * list[i];
				}
			}
			mx = max(mx, profit);
		}
	}
	return mx;
}
bool check(int y, int x) {
	for (int dx = 0; dx < m; dx++) {
		if (v[y][x + dx]) return false;
	}
	return true;
}
int main() {
	//freopen("input.txt", "r", stdin);
	fastIO();
	int t; cin >> t;
	for (int i = 1; i <= t; i++) {
		cin >> n >> m >> c;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				cin >> a[r][c];
			}
		}
		int ans = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x <= n - m; x++) {
				int sum = 0;
				sum += collect(y, x);
				for (int dx = 0; dx < m; dx++) {
					v[y][x + dx] = true;
				}
				for (int ny = 0; ny < n; ny++) {
					for (int nx = 0; nx <= n - m; nx++) {
						if (check(ny, nx)) {
							sum += collect(ny, nx);
							ans = max(ans, sum);
							sum -= collect(ny, nx);
						}
					}
				}
				for (int dx = 0; dx < m; dx++) {
					v[y][x + dx] = false;
				}
				sum -= collect(y, x);
			}
		}
		cout << "#" << i << " " << ans << endl;
	}
	return 0;
}