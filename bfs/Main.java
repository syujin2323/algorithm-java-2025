package bfs; 
import java.util.*;
 
public class Main
{
    public static void main(String[] args)
    {
        int N = 10;
        List<Edge>[] adjList = new List[N];

        for (int i = 0; i < N; i++) //각 정점에 대해 LinkedList 생성 (그래프 초기화)
        {
            adjList[i] = new LinkedList<>();
        }

        //그래프 간선 추가
        adjList[0].add(new Edge(2));
        adjList[0].add(new Edge(1));

        adjList[1].add(new Edge(0));
        adjList[1].add(new Edge(3));

        adjList[2].add(new Edge(0));
        adjList[2].add(new Edge(3));

        adjList[3].add(new Edge(1));
        adjList[3].add(new Edge(2));
        adjList[3].add(new Edge(9));
        adjList[3].add(new Edge(8));

        adjList[4].add(new Edge(5));

        adjList[5].add(new Edge(4));
        adjList[5].add(new Edge(6));
        adjList[5].add(new Edge(7));

        adjList[6].add(new Edge(5));
        adjList[6].add(new Edge(7));

        adjList[7].add(new Edge(5));
        adjList[7].add(new Edge(6));

        adjList[8].add(new Edge(3));

        adjList[9].add(new Edge(3));

        printGraphMatrix(adjList, N); //인접 행렬 출력

        new BFS(adjList);
        System.out.println();
    }

    //인접 리스트를 인접 행렬로 변환하여 출력
    public static void printGraphMatrix(List<Edge>[] adjList, int N)
    {
        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) //인접 리스트 정보를 인접 행렬로 변환
        {
            for(Edge e : adjList[i])
            {
                matrix[i][e.getVertex()] = 1; //간선이 있으면 1
            }
        }

        for(int i = 0; i < N; i++) //인접 행렬 출력
        {
            for(int j = 0; j < N; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            
            System.out.println();
        }
    }

}