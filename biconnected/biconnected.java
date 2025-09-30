package biconnected;
import java.util.*;

public class biconnected {

    int N;                      
    List<Integer>[] adjList;    
    boolean[] visited;          
    int sequence = 1;           // DFS 방문 시각(1부터)
    int[] dfsnum, lownum;       
    Stack<Edge> stack;         

    public biconnected(List<Integer>[] adjLists) {
        this.N = adjLists.length;
        this.adjList = adjLists;
        this.visited = new boolean[N];
        this.dfsnum = new int[N];
        this.lownum = new int[N];
        this.stack = new Stack<>();
    }

    // DFS 시작: v는 현재 정점, u는 v의 부모(루트면 -1).
    public void bc(int v, int u) {
        visited[v] = true;
        dfsnum[v] = lownum[v] = sequence++;

        for (int w : adjList[v]) {
            if (w == u) continue; // 부모로 되돌아가는 간선은 무시

            if (!visited[w]) {
                // 스택에 push 후 DFS
                stack.push(new Edge(v, w));
                bc(w, v);

                // 자식이 처리되면 low 갱신
                lownum[v] = Math.min(lownum[v], lownum[w]);

                // BCC 경계 조건: low[w] >= dfsnum[v]
                if (lownum[w] >= dfsnum[v]) {
                    // (v,w)까지 팝하여 한 컴포넌트를 이룸
                    while (!stack.isEmpty()) {
                        Edge e = stack.pop();
                        System.out.println(e.u + " " + e.v);
                        if (e.u == v && e.v == w) break;
                    }
                    System.out.println("----------------------");
                }
            } else if (dfsnum[w] < dfsnum[v]) {
                // 뒷간선 조건: 이미 방문했고 나보다 먼저 방문된 정점
                // 스택에 한번만 쌓이도록
                stack.push(new Edge(v, w));
                lownum[v] = Math.min(lownum[v], dfsnum[w]);
            }
        }
    }
}