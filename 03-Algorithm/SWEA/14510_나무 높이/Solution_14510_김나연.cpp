#include<iostream>
#include<algorithm>
#include<memory.h>
#include<string>
#include<vector>
#include<math.h>
#include<queue>
#include<tuple>
using namespace std;

int t, n, res;
int mx, diff;
int a[104];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> t;

	for (int test_case = 1; test_case <= t; test_case++) {
		fill(a, a + 104, 0);
		res = 0;
		diff = 0;
		mx = 0;

		cin >> n;

		for (int i = 0; i < n; i++) {
			cin >> a[i];
			if (mx < a[i])mx = a[i];
		}

		int odd = 0;
		for (int i = 0; i < n; i++) {
			if ((mx - a[i]) % 2 == 1) odd++;
			diff = diff + (mx - a[i]);
		}

		res = (diff / 3) * 2;
		res = res + diff % 3;

		int temp = res / 2 + res % 2;

		if (temp < odd) {
			if (res % 2 == 0) res -= 1;
			res = res + (odd - temp) * 2;
		}

		cout << "#" << test_case << " " << res << "\n";
	}

	return 0;
}
