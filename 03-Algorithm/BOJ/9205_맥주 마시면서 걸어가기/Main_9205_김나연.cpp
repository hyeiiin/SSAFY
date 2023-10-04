#include<iostream>
#include<algorithm>
#include<memory.h>
#include<string>
#include<vector>
#include<math.h>
#include<queue>
#include<tuple>
using namespace std;

int t, n, x, y;
int home_x, home_y;
int fes_x, fes_y;
int visited[104];
vector<pair<int, int>> con;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	
	cin >> t;

	for (int test_case = 0; test_case < t; test_case++) {
		con.clear();
		fill(visited, visited + 104, 0);
		cin >> n;
		cin >> home_x >> home_y;

		for (int i = 0; i < n; i++) {
			cin >> x >> y;
			con.push_back({ x,y });
		}

		cin >> fes_x >> fes_y;

		queue<pair<int, int>> q;

		
		q.push({ home_x, home_y });

		int flag = 0;
		while (q.size()) {
			tie(x, y) = q.front();
			q.pop();

			int cnt = 0;
			for (auto v : con) {
				if (abs(v.first - x) + abs(v.second - y) <= 1000) {
					if (!visited[cnt]) {
						visited[cnt] = 1;
						q.push({ v.first, v.second });
					}
				}
				cnt++;
			}

			if (abs(fes_x - x) + abs(fes_y - y) <= 1000) {
				flag = 1;
				break;
			}
		}

		if (flag == 1) {
			cout << "happy" << "\n";
		}
		else {
			cout << "sad" << "\n";
		}

	}

	return 0;
}
