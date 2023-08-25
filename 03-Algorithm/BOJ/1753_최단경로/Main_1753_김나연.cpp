#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <stack>
#include <queue>
#include <memory.h>
#include<tuple>
using namespace std;

const int INF = 987654321;
int v_num, e_num, k, u, v, w;
int dist[20001];
vector <pair<int, int>> adj[20001];
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

int main(){
	cin >> v_num >> e_num;
	cin >> k;

	fill(dist, dist + 20001, INF); // 거리 값 INF 초기화

	for (int i = 0; i < e_num; i++) {
		cin >> u >> v >> w;
		adj[u].push_back({ w,v }); // u -> v 가중치가 w
	}

	pq.push({ 0,k }); // 시작점 pq 삽입 // 시작점 부터 현재 정점까지 거리, 현재 정점 pq에 삽입
	dist[k] = 0; // 시작점 dist 0

	while (pq.size()) {
		int here = pq.top().second; // 현재 정점
		int here_dist = pq.top().first; // 시작 정점부터 현재 정점까지 거리
		pq.pop();

		if (dist[here] != here_dist) continue;
		for (pair<int, int> there : adj[here]) { // 현재 정점과 연결되어 있는 정점 탐색
			int _dist = there.first; // here부터 there까지 거리
			int _there = there.second; // there 정점

			if (dist[_there] > dist[here] + _dist) { // (시작점~there 거리)가 (시작점~here까지 거리 + here부터 there까지 거리) 보다 크다면
				dist[_there] = dist[here] + _dist; // 시작점~there 거리 최소값으로 갱신 
				pq.push({ dist[_there], _there }); // 시작점~there 거리로 오름차순 정렬 // 해당 정점에 대해 더 짧은 거리를 가지는 첫번째 정점만 처리
			}
		}
	}

	for (int i = 1; i <= v_num; i++) {
		if (dist[i] == INF)cout << "INF" << "\n";
		else cout << dist[i] << "\n";
	}

	return 0;
}
