#include<bits/stdc++.h>
using namespace std;

int v, e, start;
int dist[20001];
vector<pair<int, int>> vv[20001];

void gogo(){
	priority_queue<pair<int, int>> pq;
	pq.push(make_pair(0, start));
	dist[start] = 0;
	
	while(!pq.empty()){
		int cost = pq.top().first;
		int to = pq.top().second;
		pq.pop();
		
		for(int i = 0; i < vv[to].size(); i++){
			int next = vv[to][i].first;
			int ncost = vv[to][i].second;
			if(dist[next] > cost + ncost){
				dist[next] = cost +ncost;
				pq.push(make_pair(dist[next], next));
			}
		}
	}
	for(int i = 1; i <= v; i++){
		if(dist[i] == 987654321)cout <<"INF"<<"\n";
		else cout << dist[i] << "\n";
	}
}


int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> v >> e>> start;
	for(int i = 0; i < e; i++){
		int a, b, c;
		cin >> a >> b >> c;
		vv[a].push_back({b, c});
	}
	for(int i = 1; i <= v; i++){
		dist[i] = 987654321;
	}
	gogo();
}
