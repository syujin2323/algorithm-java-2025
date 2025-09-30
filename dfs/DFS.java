package dfs;
import java.util.*;

public class DFS
{
    int N;
    List<Edge>[] graph;
    private boolean[] visited; //방문 체크

    public DFS(List<Edge>[] adjList)
    {
        N = adjList.length;
        graph = adjList;
        visited = new boolean[N];

        for(int i = 0; i < N; i++) //visited 초기화
        {
            visited[i] = false;
        }

        for(int i = 0; i < N; i++) //방문한적이 없으면 dfs 실행
        {
            if(!visited[i])
            {
                dfs(i);
            }
        }
    }

    private void dfs(int v) //dfs 탐색 메서드
    {
        visited[v] = true;
        System.out.print(v + " "); //방문한 정점 출력

        for(Edge e : graph[v])
        {
            int next = e.getVertex(); //연결된 다음 정점
            if(!visited[next])
            {
                dfs(next); //재귀적으로 dfs 탐색
            }
        }
    }
    
}