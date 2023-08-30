#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <stack>
#include <queue>
#include <memory.h>
#include<tuple>
using namespace std;

int t, n, m;

int main(){
	ios_base::sync_with_stdio();
	cin.tie(0); cout.tie(0);

	cin >> t;
	
	for (int i = 0; i < t; i++) {
		cin >> n >> m;

		int res = 1;
		int cnt = 1;
		int x = m - n;

		for (int i = m; i > x;i--) {
			res *= i;
			res /= cnt++;
		}

		cout << res << "\n";
	}

	return 0;
}
