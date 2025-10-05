package kruskal;

public class Edge {
    int vertex;     // 간선의 시작 정점
    int adjvertex;  // 간선의 끝 정점
    int weight;     // 간선의 가중치
    
    // 두 정점과 가중치를 받아 간선 생성
    public Edge(int u, int v, int wt) {
        vertex = u;
        adjvertex = v;
        weight = wt;
    }
}