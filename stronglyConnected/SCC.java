package stronglyConnected;
import java.util.*;

public class SCC {

    private final int N;                       
    private final List<Integer>[] adj;         // 원 그래프 인접 리스트
    private final List<Integer>[] rev;         // 역그래프 인접 리스트
    private final boolean[] visited1, visited2;
    private final Deque<Integer> order;        // 완료 순서 스택

    public SCC(int n, List<Edge> edges) {
        this.N = n;
        this.adj = new List[n];
        this.rev = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }
        // Edge 리스트로부터 인접 리스트 구성
        for (Edge e : edges) {
            adj[e.u].add(e.v); // u -> v
            rev[e.v].add(e.u); // 역그래프 v -> u
        }
        this.visited1 = new boolean[n];
        this.visited2 = new boolean[n];
        this.order = new ArrayDeque<>();
    }

    public SCC(List<Integer>[] adjList) {
        this.N = adjList.length;
        this.adj = adjList;
        this.rev = new List[N];
        for (int i = 0; i < N; i++) rev[i] = new ArrayList<>();
        for (int u = 0; u < N; u++) for (int v : adj[u]) rev[v].add(u);
        this.visited1 = new boolean[N];
        this.visited2 = new boolean[N];
        this.order = new ArrayDeque<>();
    }

    // DFS 완료 순서를 쌓기(모든 인접 정점 처리 후 push)
    private void dfs1(int v) {
        visited1[v] = true;
        for (int w : adj[v]) if (!visited1[w]) dfs1(w);
        order.push(v);
    }

    // DFS 역그래프에서 하나의 SCC 수집
    private void dfs2(int v, List<Integer> comp) {
        visited2[v] = true;
        comp.add(v);
        for (int w : rev[v]) if (!visited2[w]) dfs2(w, comp);
    }

    public void start() {
        // 완료 순서 만들기
        for (int v = 0; v < N; v++) if (!visited1[v]) dfs1(v);

        // 완료 순서의 역순으로 역그래프 DFS → SCC 추출
        int idx = 1;
        while (!order.isEmpty()) {
            int v = order.pop();
            if (visited2[v]) continue;
            List<Integer> comp = new ArrayList<>();
            dfs2(v, comp);
            System.out.println("SCC " + idx++ + ": " + comp);
        }
    }
}