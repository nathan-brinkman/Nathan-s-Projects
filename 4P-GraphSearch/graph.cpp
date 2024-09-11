/*
 * graph.cpp
 *
 * Method definitions for the graph class.
 *
 * Author: <your name here>
 */

#include "graph.h"

#include <vector>
#include <queue>

graph::graph(int vertex_count){
    verticies.resize(vertex_count);
}

void graph::add_edge(int from, int to){
    if((unsigned)from < verticies.size()){
        verticies[from].push_back(to);
    }
}

vector<int> graph::find_shortest_path(int from, int to){

    vector<bool> visited(verticies.size());
    for(unsigned i=0; i<visited.size(); i++){
        visited[i] = false;
    }

    graph P(verticies.size());

    queue<int> Q;

    visited[from] = true;
    Q.push(from);

    while(!Q.empty() && visited[to] == false){
        int u = Q.front();
        Q.pop();
        for(unsigned i=0; i < verticies[u].size(); i++){
            if(visited[verticies[u][i]] == false){
                Q.push(verticies[u][i]);
                visited[verticies[u][i]] = true;
                P.add_edge(verticies[u][i], u);
            }
        }
    }

    vector<int> L;

    int src = from;
    int dest = to;

    if(visited[dest] == true){
        L.push_back(to);
        while(dest != src){
            dest = P.verticies[dest][0];
            L.push_back(dest);
        }
    }

    vector<int> rout;
    for(int i=L.size()-1; i>=0; i--){
        rout.push_back(L[i]);
    }

    return rout;
    
}
