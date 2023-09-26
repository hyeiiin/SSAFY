#include<iostream>
#include<algorithm>
#include<memory.h>
using namespace std;

int t, n, a[1004];
int lis[1004], res;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	
	cin >> t;

	for (int test_case = 1; test_case <= t; test_case++) {
		cin >> n;
		res = 0;
		fill(lis, lis + 1004, 0);
		fill(a, a + 1004, 0);

		for (int i = 0; i < n; i++) {
			cin >> a[i];
		}

		for (int i = 0; i < n; ++i) {
			lis[i] = 1; 
			for (int j = 0; j < i; ++j) { 
				if (a[j] < a[i] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}

			if (res < lis[i]) res = lis[i];
		}

		cout << "#" << test_case << " " << res << "\n";
	}

}
