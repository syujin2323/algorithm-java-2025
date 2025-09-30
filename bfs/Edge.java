package bfs; 

public class Edge //그래프의 간선을 표현하는 클래스
{
    int vertex; //간선이 가리키는 도착 정점 번호

    public Edge(int v) //연결할 정점 번호를 받아 저장
    {
        vertex = v;
    }

    public int getVertex() //도착 정점 번호 반환
    {
        return vertex;
    }
}