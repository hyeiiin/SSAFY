#include<iostream>
#include<algorithm>
#include<tuple>
#include<queue>
#include<cstring>
using namespace std;

int lab[9][9];
int visited[9][9];
int one, two, total;
queue<pair<int, int>> q;
int n, m;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, -1, 0, 1 };
int mx = -12345678;


void bfs(int y, int x) {
	++two;
	visited[y][x] = 1;
	q.push({ y, x });
	while (q.size() != 0) {
		tie(y, x) = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= m || ny >= n)continue;
			if (visited[ny][nx] == 1)continue;
			if (lab[ny][nx] == 1)continue;
			q.push({ny, nx });
			++two;
			visited[ny][nx] =1;
		}
	}
}

void wall(int cnt, int y, int x) {

	if (cnt == 3) {
		memset(visited, 0, 9 * 9 * sizeof(int));
		two = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (lab[i][j] != 2)continue;
				if (visited[i][j] == 1)continue;
				bfs(i, j);
			}
		}
		
		int temp = total - one - two - 3;
		mx = max(mx, temp);
		return;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (lab[i][j] != 0) continue;
			lab[i][j] = 1;
			wall(cnt + 1, i, j+1);
			lab[i][j] = 0;
		}
	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n >> m;
	total = n * m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> lab[i][j];
			if (lab[i][j] == 1)one++;
		}
	}

	wall(0, 0, 0);

	cout << mx << "\n";
}