#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
struct edge{
	ll x, y, val;
	edge(ll a, ll b, ll c){
		x = a;
		y = b;
		val = c;
	}
	bool operator<(const edge &b) const {
		return val < b.val;
	}
};
double e;
int n;
int p[1001];

int find(int a){
	if(p[a] == a)return a;
	else p[a] = find(p[a]);
}


bool uni(int a, int b){
	int aRoot = find(a);
	int bRoot = find(b);
	if(aRoot == bRoot)return false;
	p[bRoot] = aRoot;
	return true;
}

int main(){
	int t;
	scanf("%d", &t);
	for(int test = 1; test <= t; test++){
		pair<int, int> map[1001];
		vector<edge> v;
		memset(p, 0, sizeof(p));
		
		scanf("%d", &n);
		
		for(int i = 0; i <n; i++){
			p[i] = i;
			scanf("%d", &map[i].first);
		}
		
		for(int i = 0; i <n; i++){
			scanf("%d", &map[i].second);
		}
		ll a = 0;
		ll b = 0;
		scanf("%lf", &e);
		for(int i = 0; i < n; i++){
			for(int j = i+1;j < n; j++){
				a = pow((map[i].first - map[j].first), 2);
				b = pow((map[i].second - map[j].second), 2);
				ll val = a+b;
				v.push_back(edge(i, j, val));
			}
		}
		sort(v.begin(), v.end());
		ll ans = 0;
		for(int i = 0; i < v.size(); i++){
			if(uni(v[i].x, v[i].y)) ans += v[i].val;
		}
		printf("#%d %.0f \n",test , (double)ans*e);
	}
}
