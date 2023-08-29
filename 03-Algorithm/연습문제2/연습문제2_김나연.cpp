#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <stack>
#include <queue>
#include <memory.h>
#include<tuple>
using namespace std;

int a[100];

int main(){
	a[1] = 2;
	a[2] = 5;

	for (int i = 3; i < 100; i++) {
		a[i] = 2 * a[i - 1] + a[i - 2];
	}

	cout << a[6] << "\n";

	return 0;
}
