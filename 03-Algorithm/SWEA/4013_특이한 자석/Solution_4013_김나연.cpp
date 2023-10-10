#include<iostream>
#include<algorithm>
#include<memory.h>
#include<string>
#include<vector>
#include<math.h>
#include<queue>
#include<tuple>
using namespace std;

int t, k, res;
int a[10][10], top[4];
int num, spin;

void go1() {
	if (a[0][(top[0] + 2)%8] == a[1][(top[1] + 6)%8]) {
		if (spin == 1) top[0] = (top[0] + 7) % 8;
		if (spin == -1) top[0] = (top[0] + 1) % 8;
	}
	else {
		if (a[1][(top[1] + 2)%8] != a[2][(top[2] + 6)%8]) {
			if (a[2][(top[2] + 2)%8] != a[3][(top[3] + 6)%8]) {
				if (spin == 1) {
					top[0] = (top[0] + 7) % 8;
					top[1] = (top[1] + 1) % 8;
					top[2] = (top[2] + 7) % 8;
					top[3] = (top[3] + 1) % 8;
				}
				else if (spin == -1) {
					top[0] = (top[0] + 1) % 8;
					top[1] = (top[1] + 7) % 8;
					top[2] = (top[2] + 1) % 8;
					top[3] = (top[3] + 7) % 8;
				}
			}
			else {
				if (spin == 1) {
					top[0] = (top[0] + 7) % 8;
					top[1] = (top[1] + 1) % 8;
					top[2] = (top[2] + 7) % 8;
				}
				else if (spin == -1) {
					top[0] = (top[0] + 1) % 8;
					top[1] = (top[1] + 7) % 8;
					top[2] = (top[2] + 1) % 8;
				}
			}
		}
		else {
			if (spin == 1) {
				top[0] = (top[0] + 7) % 8;
				top[1] = (top[1] + 1) % 8;
			}
			else if (spin == -1) {
				top[0] = (top[0] + 1) % 8;
				top[1] = (top[1] + 7) % 8;
			}
		}
	}

	return;
}

void go2() {
	if (a[0][(top[0] + 2)%8] != a[1][(top[1] + 6)%8]) {
		if (spin == 1) top[0] = (top[0] + 1) % 8; 
		if (spin == -1) top[0] = (top[0] + 7) % 8;
	}

	if (a[1][(top[1] + 2) % 8] == a[2][(top[2] + 6) % 8]) {
		if (spin == 1) top[1] = (top[1] + 7) % 8;
		if (spin == -1) top[1] = (top[1] + 1) % 8;
	}
	else {
		if (a[2][(top[2] + 2)%8] != a[3][(top[3] + 6) % 8]) {
			if (spin == -1) {
				top[1] = (top[1] + 1) % 8;
				top[2] = (top[2] + 7) % 8;
				top[3] = (top[3] + 1) % 8;
			}
			else if (spin == 1) {
				top[1] = (top[1] + 7) % 8;
				top[2] = (top[2] + 1) % 8;
				top[3] = (top[3] + 7) % 8;
			}
		}
		else {
			if (spin == -1) {
				top[1] = (top[1] + 1) % 8;
				top[2] = (top[2] + 7) % 8;
			}
			else if (spin == 1) {
				top[1] = (top[1] + 7) % 8;
				top[2] = (top[2] + 1) % 8;
			}
		}
	}
	
	return;
}

void go3() {
	if (a[2][(top[2] + 2) % 8] != a[3][(top[3] + 6) % 8]) {
		if (spin == 1) top[3] = (top[3] + 1) % 8;
		if (spin == -1) top[3] = (top[3] + 7) % 8;
	}

	if (a[1][(top[1] + 2) % 8] == a[2][(top[2] + 6) % 8]) {
		if (spin == 1) top[2] = (top[2] + 7) % 8;
		if (spin == -1) top[2] = (top[2] + 1) % 8;
	}
	else {
		if (a[0][(top[0] + 2) % 8] != a[1][(top[1] + 6) % 8]) {
			if (spin == -1) {
				top[0] = (top[0] + 1) % 8;
				top[1] = (top[1] + 7) % 8;
				top[2] = (top[2] + 1) % 8;
			}
			else if (spin == 1) {
				top[0] = (top[0] + 7) % 8;
				top[1] = (top[1] + 1) % 8;
				top[2] = (top[2] + 7) % 8;
			}
		}
		else {
			if (spin == 1) {
				top[1] = (top[1] + 1) % 8;
				top[2] = (top[2] + 7) % 8;
			}
			else if (spin == -1) {
				top[1] = (top[1] + 7) % 8;
				top[2] = (top[2] + 1) % 8;
			}
		}
	}

	return;
}

void go4() {
	if (a[2][(top[2] + 2) % 8] == a[3][(top[3] + 6) % 8]) {
		if (spin == 1) top[3] = (top[3] + 7) % 8;
		if (spin == -1) top[3] = (top[3] + 1) % 8;
	}
	else {
		if (a[1][(top[1] + 2) % 8] != a[2][(top[2] + 6) % 8]) {
			if (a[0][(top[0] + 2)%8] != a[1][(top[1] + 6)%8]) {
				if (spin == -1) {
					top[0] = (top[0] + 7) % 8;
					top[1] = (top[1] + 1) % 8;
					top[2] = (top[2] + 7) % 8;
					top[3] = (top[3] + 1) % 8;
				}
				else if (spin == 1) {
					top[0] = (top[0] + 1) % 8;
					top[1] = (top[1] + 7) % 8;
					top[2] = (top[2] + 1) % 8;
					top[3] = (top[3] + 7) % 8;
				}
			}
			else {
				if (spin == -1) {
					top[1] = (top[1] + 1) % 8;
					top[2] = (top[2] + 7) % 8;
					top[3] = (top[3] + 1) % 8;
				}
				else if (spin == 1) {
					top[1] = (top[1] + 7) % 8;
					top[2] = (top[2] + 1) % 8;
					top[3] = (top[3] + 7) % 8;
				}
			}
		}
		else {
			if (spin == -1) {
				top[2] = (top[2] + 7) % 8;
				top[3] = (top[3] + 1) % 8;
			}
			else if (spin == 1) {
				top[2] = (top[2] + 1) % 8;
				top[3] = (top[3] + 7) % 8;
			}
		}
	}

	return;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> t;

	for (int test_case = 1; test_case <= t; test_case++) {
		res = 0;
		fill(top, top + 4, 0);
		fill(&a[0][0], &a[0][0] + 10 * 10, 0);
		cin >> k;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				cin >> a[i][j];
			}
		}

		for (int i = 0; i < k; i++) {
			cin >> num >> spin;
			if (num == 1)go1();
			else if (num == 2) go2();
			else if (num == 3) go3();
			else if (num == 4) go4();
		}

		/* for (int i = 0; i < 4; i++) {
			cout << top[i] << " ";
		} 
		cout << "\n"; */

		if (a[0][top[0]] == 1)res += 1;
		if (a[1][top[1]] == 1)res += 2;
		if (a[2][top[2]] == 1)res += 4;
		if (a[3][top[3]] == 1)res += 8;

		cout << "#" << test_case << " " << res << "\n";
	}

	return 0;
}
