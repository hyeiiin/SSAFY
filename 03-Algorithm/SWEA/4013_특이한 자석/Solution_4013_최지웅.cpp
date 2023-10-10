#include <bits/stdc++.h>
using namespace std;
#define N 0
#define S 1
#define RIGHT 2
#define LEFT 6
#define CLOCK 1
#define COUNTER -1
#define NONE 0
deque<int> m[5];
void init() {
	for (int i = 1; i <= 4; i++) m[i].clear();
}
void rotate(int id, int dir) {
	int dirs[5] = { NONE };
	dirs[id] = dir;
	if (id == 1) {
		if (m[1][RIGHT] != m[2][LEFT]) dirs[2] = -dirs[1];
		if (dirs[2] && m[2][RIGHT] != m[3][LEFT]) dirs[3] = -dirs[2];
		if (dirs[3] && m[3][RIGHT] != m[4][LEFT]) dirs[4] = -dirs[3];
	}
	else if (id == 2) {
		if (m[1][RIGHT] != m[2][LEFT]) dirs[1] = -dirs[2];
		if (m[3][LEFT] != m[2][RIGHT]) dirs[3] = -dirs[2];
		if (dirs[3] && m[3][RIGHT] != m[4][LEFT]) dirs[4] = -dirs[3];
	}
	else if (id == 3) {
		if (m[3][RIGHT] != m[4][LEFT]) dirs[4] = -dirs[3];
		if (m[2][RIGHT] != m[3][LEFT]) dirs[2] = -dirs[3];
		if (dirs[2] && m[2][LEFT] != m[1][RIGHT]) dirs[1] = -dirs[2];
	}
	else if (id == 4) {
		if (m[3][RIGHT] != m[4][LEFT]) dirs[3] = -dirs[4];
		if (dirs[3] && m[3][LEFT] != m[2][RIGHT]) dirs[2] = -dirs[3];
		if (dirs[2] && m[2][LEFT] != m[1][RIGHT]) dirs[1] = -dirs[2];
	}
	for (int i = 1; i <= 4; i++) {
		if (dirs[i] == CLOCK) {
			int tmp = m[i].back();
			m[i].pop_back();
			m[i].push_front(tmp);
		}
		else if (dirs[i] == COUNTER) {
			int tmp = m[i].front();
			m[i].pop_front();
			m[i].push_back(tmp);
		}
	}
}
int score() {
	int sum = 0;
	if (m[1][0] == S) sum += 1;
	if (m[2][0] == S) sum += 2;
	if (m[3][0] == S) sum += 4;
	if (m[4][0] == S) sum += 8;
	return sum;
}
int main() {
	freopen("input.txt", "r", stdin);
	int t; cin >> t;
	for (int tc = 1; tc <= t; tc++) {
		init();
		int k; cin >> k;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 8; j++) {
				int x; cin >> x; m[i].push_back(x);
			}
		}
		for (int n = 1; n <= k; n++) {
			int id, dir; cin >> id >> dir;
			rotate(id, dir);
		}
		cout << "#" << tc << " " << score() << endl;
	}
}