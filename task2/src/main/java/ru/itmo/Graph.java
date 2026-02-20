package ru.itmo;

public class Graph {
    int[][] adjMatrix;
    int numOfVertices;

    public Graph(int[][] mat, int v) {
        this.adjMatrix = mat;
        this.numOfVertices = v;
    }

    public static int getClosestVertex(int[] distance, boolean[] visited){
        int min = Integer.MAX_VALUE;
        int minIndx = -1;
        for(int i = 0; i < distance.length; i++){
            if(distance[i] < min){
                if(!visited[i]){
                    min = distance[i];
                    minIndx = i;
                }
            }
        }
        return minIndx;
    }

    public static int[] shortestPath(Graph graph, int src){
        if(graph.numOfVertices <= 0 || graph.adjMatrix.length == 0)
            throw new IllegalArgumentException("Graph must have at least one vertex!");

        if(src < 0 || src >= graph.numOfVertices)
            throw new IllegalArgumentException("Source vertex index out of bounds!");

        for (int i = 0; i < graph.numOfVertices; i++) {
            for (int j = 0; j < graph.numOfVertices; j++) {
                if(graph.adjMatrix[i][j] < 0)
                    throw new IllegalArgumentException("Negative weights not supported!");
            }
        }

        int[] distance = new int[graph.numOfVertices];
        boolean[] visited = new boolean[graph.numOfVertices];

        for(int v = 0; v < graph.numOfVertices; v++){
            distance[v] = Integer.MAX_VALUE;
            visited[v] = false;
        }
        distance[src] = 0;

        for(int v = 0; v < graph.numOfVertices; v++){
            int closestVertex = getClosestVertex(distance, visited);
            if(closestVertex == -1){
                return distance;
            }

            visited[closestVertex] = true;
            for(int w = 0; w < graph.numOfVertices; w++){
                if(!visited[w]){
                    if(graph.adjMatrix[closestVertex][w] != 0){
                        int newDist = distance[closestVertex] + graph.adjMatrix[closestVertex][w];
                        if(newDist < distance[w]){
                            distance[w] = newDist;
                        }
                    }
                }
            }
        }
        return distance;
    }
}
