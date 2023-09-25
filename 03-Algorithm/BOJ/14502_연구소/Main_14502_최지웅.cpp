#include <bits/stdc++.h>
using namespace std;
enum { EMPTY, WALL, VIRUS };
void fastIO() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
}
const int MAXN = 8;
const int MAXM = 8;
int N, M;
int arr[MAXN][MAXM];
int arrCopy[MAXN][MAXM];
struct Pos {
	int y, x;
	Pos(int y, int x) {
		this->y = y;
		this->x = x;
	}
	string toString() {
		return to_string(y) + " " + to_string(x);
	}
};
vector<Pos> virusList;
void copyArr() {
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			arrCopy[y][x] = arr[y][x];
		}
	}
}
vector<Pos> wallList;
void setWall() {
	for (Pos pos : wallList) {
		arrCopy[pos.y][pos.x] = WALL;
	}
}
const int dy[4] = { -1, 1, 0, 0 };
const int dx[4] = { 0, 0, -1, 1 };
bool valid(int y, int x) {
	return 0 <= y && y < N && 0 <= x && x < M;
}
bool visited[MAXN][MAXM];
void initVisited() {
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			visited[y][x] = false;
		}
	}
}
void spread(Pos pos) {
	queue<Pos> q;
	visited[pos.y][pos.x] = true;
	q.push(pos);
	while (!q.empty()) {
		Pos pos = q.front();
		q.pop();
		//cout << pos.y << " " << pos.x << endl;
		arrCopy[pos.y][pos.x] = VIRUS;
		for (int i = 0; i < 4; i++) {
			int ny = pos.y + dy[i];
			int nx = pos.x + dx[i];
			if (valid(ny, nx) && arrCopy[ny][nx] == EMPTY && !visited[ny][nx]) {
				visited[ny][nx] = true;
				q.push(Pos(ny, nx));
			}
		}
	}
}
int countSafeArea() {
	int area = 0;
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			if (arrCopy[y][x] == EMPTY) area++;
		}
	}
	return area;
}
int main() {
	freopen("input.txt", "r", stdin);
	fastIO();
	cin >> N >> M;
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < M; x++) {
			cin >> arr[y][x];
			if (arr[y][x] == VIRUS) {
				virusList.push_back(Pos(y, x));
			}
		}
	}
	int maxSafeArea = 0;
	for (int i = 0; i < N * M; i++) {
		for (int j = i + 1; j < N * M; j++) {
			for (int k = j + 1; k < N * M; k++) {
				if (arr[i / M][i % M] == EMPTY && arr[j / M][j % M] == EMPTY && arr[k / M][k % M] == EMPTY) {
					copyArr();
					wallList.push_back(Pos(i / M, i % M));
					wallList.push_back(Pos(j / M, j % M));
					wallList.push_back(Pos(k / M, k % M));
					setWall();
					initVisited();
					for (Pos pos : virusList) {
						if (!visited[pos.y][pos.x]) spread(pos);
					}
					maxSafeArea = max(maxSafeArea, countSafeArea());
					wallList.clear();
				}
			}
		}
	}
	cout << maxSafeArea << endl;
	return 0;
}