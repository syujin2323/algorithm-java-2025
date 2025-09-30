package bfs;
import java.util.*;  

public class BFS {
    int N;
    List<Edge>[] graph;
    private boolean[] visited; //방문 체크

    public BFS(List<Edge>[] adjList)
    {
        N = adjList.length;
        graph = adjList;
        visited = new boolean[N];

        for(int i = 0; i < N; i++) //visited 초기화
        {
            visited[i] = false;
        }

        for(int i = 0; i < N; i++) // 방문한적이 없으면 bfs 실행
        {
            if(!visited[i])
            {
                System.out.print("BFS(" + i + "): "); //
                bfs(i);
                System.out.println();
            }
        }
    }

    private void bfs(int start) //bfs 탐색 메서드
    {
        Queue<Integer> q = new LinkedList<>(); //탐색할 정점들을 저장할 큐
        visited[start] = true; //시작 정점 방문
        q.add(start);

        while(!q.isEmpty()) //큐가 빌 때까지 반복
        {
            int v = q.poll(); //큐에서 정점을 꺼냄
            System.out.print(v + " "); //방문한 정점 출력

            for(Edge e : graph[v]) //현재 정점과 연결된 모든 인접 정점 탐색
            {
                int next = e.getVertex();
                if(!visited[next]) //아직 방문하지 않은 정점이면 방문 처리 후 큐에 추가
                {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

    }
}