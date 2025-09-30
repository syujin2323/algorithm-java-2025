package stronglyConnected;

// 모든 그래프 간선을 (u -> v) 형태로 표현
public class Edge {
    public final int u;
    public final int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    // 디버깅/출력 편의를 위한 문자열   
    public String toString() {
        return u + " " + v;
    }
}