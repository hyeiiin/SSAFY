#include<bits/stdc++.h>
using namespace std;

int forecaste[50][9]; // 이닝별 각 선수의 결과를 저장하는 배열
int n, ans = 0;

int playGame(vector<int>& order) {
    int score = 0;
    int curPlayer = 0; // 현재 타석에 서 있는 선수

    for (int inning = 0; inning < n; ++inning) {
        int outCount = 0;
        vector<int> bases(3, 0);

        while (outCount < 3) {
            int result = forecaste[inning][order[curPlayer]];

            if (result == 0) {
                outCount++;
            } else {
                for (int i = 2; i >= 0; i--) {
                    if (bases[i] == 1) {
                        if (i + result >= 3) {
                            score++;
                            bases[i] = 0;
                        } else {
                            bases[i + result] = 1;
                            bases[i] = 0;
                        }
                    }
                }

                if (result != 4) {
                    bases[result - 1] = 1;
                } else {
                    score++;
                }
            }

            curPlayer = (curPlayer + 1) % 9;
        }
    }

    return score;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 9; j++) {
            cin >> forecaste[i][j];
        }
    }

    vector<int> order(8);
    for (int i = 1; i < 9; i++) {
        order[i - 1] = i;
    }

    do {
        vector<int> currentOrder = order;
        currentOrder.insert(currentOrder.begin() + 3, 0); // 4번 타자를 항상 1번 타자로 고정

        int score = playGame(currentOrder);
        ans = max(ans, score);
    } while (next_permutation(order.begin(), order.end()));

    cout << ans << endl;

    return 0;
}

