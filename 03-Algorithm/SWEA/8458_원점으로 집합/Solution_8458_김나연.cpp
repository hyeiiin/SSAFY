#include<iostream>
#include<algorithm>
#include<memory.h>
#include<string>
#include<vector>
#include<math.h>
#include<queue>
#include<tuple>
using namespace std;

int t, n, a[12], res;
int x, y, mx;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> t;

	for (int test_case = 1; test_case <= t; test_case++) {
		mx = 0;
		res = 0;
		fill(a, a + 12, 0);

		cin >> n;

		for (int i = 0; i < n; i++) {
			cin >> x >> y;

			a[i] = abs(x) + abs(y);
			mx = max(mx, a[i]);
		}

		int flag = 1;
		for (int i = 0; i < n - 1; i++) {
			if (a[i] % 2 != a[i + 1] % 2) {
				flag = 0;
				break;
			}
		}

		int sum = 0;
		int idx = -1;
		while (flag==1) {
			idx++;
			sum += idx;
			if (sum >= mx && (sum - mx) % 2 == 0) break;
		}

		cout << "#" << test_case << " " << idx << "\n";
	}

	return 0;
}
