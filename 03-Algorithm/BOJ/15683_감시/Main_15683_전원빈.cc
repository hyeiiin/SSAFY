#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int n, m;
int mp[10][10];
int visited[10][10];
vector<pair<int, int>> v;
int ans = 987654321;
int tempans;

void left(int y, int x) {
	if(mp[y][x] == 0)visited[y][x] = 1;
	int nx = x - 1;
	if (nx < 1)return;
	if (mp[y][nx] == 6)return;
	left(y, nx);
}

void right(int y, int x) {
	if(mp[y][x] == 0)visited[y][x] = 1;
	int nx = x + 1;
	if (nx > m)return;
	if (mp[y][nx] == 6)return;
	right(y, nx);
}

void up(int y, int x) {
	if(mp[y][x] == 0)visited[y][x] = 1;
	int ny = y - 1;
	if (ny < 0)return;
	if (mp[ny][x] == 6)return;
	up(ny, x);
}

void down(int y, int x) {
	if(mp[y][x] == 0)visited[y][x] = 1;
	int ny = y + 1;
	if (ny > n)return;
	if (mp[ny][x] == 6)return;
	down(ny, x);
}

void fs(int cnt) {
	if (cnt == v.size()) {
		tempans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (visited[i][j] == 0 && mp[i][j] == 0) tempans++;
			}
		}
		ans = min(ans, tempans);
		return;
	}

	int y = v[cnt].first;
	int x = v[cnt].second;

	if (mp[y][x] == 1) {
		int subvisited[10][10];
		for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					subvisited[i][j] = visited[i][j];
				}
		}
		for (int k = 0; k < 4; k++) {
			if (k == 0) {
				left(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];

					}
				}
			}
			else if (k == 1) {
				up(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}
			else if (k == 2) {
				right(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];

					}
				}
			}
			else if (k == 3) {
				down(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}

		}
	}
	else if (mp[y][x] == 2) {
		int subvisited[10][10];
		for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					subvisited[i][j] = visited[i][j];
				}
		}
		for (int k = 0; k < 2; k++) {
			if (k == 0) {
				left(y, x);
				right(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}
			else if (k == 1) {
				up(y, x);
				down(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}
		}
	}
	else if (mp[y][x] == 3) {
		int subvisited[10][10];
		for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					subvisited[i][j] = visited[i][j];
				}
		}
		for (int k = 0; k < 4; k++) {
			if (k == 0) {
				up(y, x);
				right(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}
			else if (k == 1) {
				right(y,x);
				down(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}
			else if (k == 2) {
				down(y, x);
				left(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}
			else if (k == 3) {
				left(y, x);
				up(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];
					}
				}
			}
		}
	}
	else if (mp[y][x] == 4) {
		int subvisited[10][10];
		for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					subvisited[i][j] = visited[i][j];
				}
			}
		for (int k = 0; k < 4; k++) {
			if (k == 0) {
				left(y, x);
				right(y, x);
				up(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];

					}
				}
			}
			else if (k == 1) {
				right(y, x);
				left(y, x);
				down(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];

					}
				}
			}
			else if (k == 2) {
				up(y, x);
				left(y, x);
				down(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];

					}
				}
			}
			else if (k == 3) {
				right(y, x);
				up(y, x);
				down(y, x);
				fs(cnt + 1);
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= m; j++) {
						visited[i][j] = subvisited[i][j];

					}
				}
			}
		}
	}
	else if (mp[y][x] == 5) {
		int subvisited[10][10];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				subvisited[i][j] = visited[i][j];
			}
		}
		up(y, x);
		right(y, x);
		left(y, x);
		down(y, x);
		fs(cnt + 1);
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				visited[i][j] = subvisited[i][j];
			}
		}
	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n >> m;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			cin >> mp[i][j];
			if (mp[i][j] != 0 && mp[i][j] != 6) {
				v.push_back({i, j});
			}
		}
	}
	fs(0);
	cout << ans;
}