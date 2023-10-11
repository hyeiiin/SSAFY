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
		res = res + diff % 3; // 나머지 날짜 더해주기

		// (차이가 홀수인 나무 개수 < res의 홀수 일수) 이면 날짜 더 세어줘야함
		// ex) 4 3 3 3 3 => 7일 
		int temp = res / 2 + res % 2; // res의 홀수 날 일수
		if (temp < odd) { 
			if (res % 2 == 0) res -= 1; // 끝난 날이 짝수 날이면 -1 (짝수 날: 0, 홀수 날:1 반복하기 위해)
			res = res + (odd - temp) * 2;
		}

		cout << "#" << test_case << " " << res << "\n";
	}

	return 0;
}
