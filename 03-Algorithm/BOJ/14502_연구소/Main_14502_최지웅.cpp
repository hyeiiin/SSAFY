#include <bits/stdc++.h>
using namespace std;
void fastIO() {
	ios::sync_with_stdio;
	cin.tie(nullptr);
	cout.tie(nullptr);
}
int n, m;
const int MAXN = 8;
const int MAXM = 8;
int arr[MAXN][MAXM];
enum { EMPTY, WALL, VIRUS };
struct Pos {
	int r, c;
	Pos(int r, int c) {
		this->r = r;
		this->c = c;
	}
};
vector<Pos> emptyList;
bool visited[MAXN][MAXM];
const int dy[4] = { -1, 1, 0, 0 };
const int dx[4] = { 0, 0, -1, 1 };
bool valid(int r, int c) {
	return 0 <= r && r < n && 0 <= c && c < m;
}
void spread(int y, int x) {
	visited[y][x] = true;
	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (valid(ny, nx) && arr[ny][nx] == EMPTY && !visited[ny][nx]) spread(ny, nx);
	}
}
int simul() {
	memset(visited, false, sizeof(visited));
	for (int r = 0; r < n; r++) {
		for (int c = 0; c < m; c++) {
			if (arr[r][c] == VIRUS) spread(r, c);
		}
	}
	int count = 0;
	for (int r = 0; r < n; r++) {
		for (int c = 0; c < m; c++) {
			if (arr[r][c] == EMPTY && !visited[r][c]) count++;
		}
	}
	return count;
}
int main() {
	freopen("input.txt", "r", stdin);
	fastIO();
	cin >> n >> m;
	for (int r = 0; r < n; r++) {
		for (int c = 0; c < m; c++) {
			cin >> arr[r][c];
			if (arr[r][c] == EMPTY) {
				emptyList.push_back(Pos(r, c));
			}
		}
	}
	int ans = 0;
	for (int i = 0; i < emptyList.size(); i++) {
		for (int j = 0; j < i; j++) {
			for (int k = 0; k < j; k++) {
				arr[emptyList[i].r][emptyList[i].c] = WALL;
				arr[emptyList[j].r][emptyList[j].c] = WALL;
				arr[emptyList[k].r][emptyList[k].c] = WALL;
				ans = max(ans, simul());
				arr[emptyList[i].r][emptyList[i].c] = EMPTY;
				arr[emptyList[j].r][emptyList[j].c] = EMPTY;
				arr[emptyList[k].r][emptyList[k].c] = EMPTY;
			}
		}
	}
	cout << ans << endl;
	return 0;
}