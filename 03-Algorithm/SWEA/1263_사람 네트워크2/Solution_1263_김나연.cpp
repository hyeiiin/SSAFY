#include<iostream>
#include<algorithm>
#include<memory.h>
#include<string>
#include<vector>
#include<math.h>
using namespace std;

int t, n;
int dis[1004][1004];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	
	cin >> t;

	for (int test_case = 1; test_case <= t; test_case++) {
		fill(&dis[0][0], &dis[0][0] + 1004 * 1004, 0);

		cin >> n;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				cin >> dis[i][j];
				if (dis[i][j] == 0) dis[i][j] = 987654321;
			}
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					dis[i][j] = min(dis[i][k] + dis[k][j], dis[i][j]);
				}
			}
		}

		int res = 987654321;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= n; j++) {
				if (i == j)continue;
				sum += dis[i][j];
			}
			if (res > sum) {
				res = sum;
			}
		}

		cout << "#" << test_case << " " << res << "\n";
	}
	
	return 0;
}
