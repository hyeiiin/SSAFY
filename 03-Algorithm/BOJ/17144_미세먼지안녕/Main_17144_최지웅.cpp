#include <bits/stdc++.h>
using namespace std;
#define AIR -1
int r, c, t;
int pos;
int a[51][51];
const int dy[] = { -1, 1, 0, 0 };
const int dx[] = { 0, 0, -1, 1 };
bool valid(int y, int x) {
	return 1 <= y && y <= r && 1 <= x && x <= c;
}
bool checkAir(int y, int x) {
	if (pos - 1 <= y && y <= pos && x == 1) return true;
	else return false;
}
void spread() {
	int tmp[51][51] = { 0 };
	for (int y = 1; y <= r; y++) {
		for (int x = 1; x <= c; x++) {
			int remain = a[y][x];
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i], nx = x + dx[i];
				if (valid(ny, nx) && !checkAir(ny, nx)) {
					tmp[ny][nx] += a[y][x] / 5;
					remain -= a[y][x] / 5;
				}
			}
			tmp[y][x] += remain;
		}
	}
	for (int y = 1; y <= r; y++) {
		for (int x = 1; x <= c; x++) {
			if (checkAir(y, x)) a[y][x] = AIR;
			else a[y][x] = tmp[y][x];
		}
	}
}
void cleanAir() {
	// upper (counter)
	for (int y = pos - 2; y >= 2; y--) a[y][1] = a[y - 1][1];
	for (int x = 2; x <= c; x++) a[1][x - 1] = a[1][x];
	for (int y = 2; y <= pos - 1; y++) a[y - 1][c] = a[y][c];
	for (int x = c - 1; x >= 2; x--) a[pos - 1][x + 1] = a[pos - 1][x];
	a[pos - 1][2] = 0;
	// lower (clock)
	for (int y = pos + 2; y <= r; y++) a[y - 1][1] = a[y][1];
	for (int x = 2; x <= c; x++) a[r][x - 1] = a[r][x];
	for (int y = r - 1; y >= pos; y--) a[y + 1][c] = a[y][c];
	for (int x = c - 1; x >= 2; x--) a[pos][x + 1] = a[pos][x];
	a[pos][2] = 0;
}
int count() {
	int sum = 0;
	for (int y = 1; y <= r; y++) {
		for (int x = 1; x <= c; x++) {
			if (a[y][x] > 0) sum += a[y][x];
		}
	}
	return sum;
}
int main() {
	cin >> r >> c >> t;
	for (int y = 1; y <= r; y++) {
		for (int x = 1; x <= c; x++) {
			cin >> a[y][x];
			if (a[y][x] == AIR) pos = y;
		}
	}
	while (t--) {
		spread();
		cleanAir();
	}
	cout << count();
}