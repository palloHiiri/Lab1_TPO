import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.Graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphTest {

    @Test
    @DisplayName("Тест пустого графа")
    void testEmptyGraph() {
        int[][] adjMatrix = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(adjMatrix, 3);
        int[] distances = Graph.shortestPath(graph, 0);

        assertArrayEquals(new int[]{0, Integer.MAX_VALUE, Integer.MAX_VALUE}, distances);
    }

    @Test
    @DisplayName("Тест не связного графа")
    void testNonConnectedGraph() {
        int[][] adjMatrix = {
                {0, 10, 0},
                {10, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(adjMatrix, 3);
        int[] distances = Graph.shortestPath(graph, 0);

        assertArrayEquals(new int[]{0, 10, Integer.MAX_VALUE}, distances);
    }

    @Test
    @DisplayName("Тест отрицательных весов")
    void testNegativeWeight() {
        int[][] mat = {
                {0, 5, 6},
                {5, 0, 99},
                {6, -3, 0}
        };
        Graph g = new Graph(mat, 3);
        assertThrows(IllegalArgumentException.class, () -> Graph.shortestPath(g, 0));
    }

    @Test
    @DisplayName("Тест отсутствия графа")
    void testNoGraph() {
        int[][] mat = new int[0][0];
        Graph g = new Graph(mat, 0);
        assertThrows(IllegalArgumentException.class, () -> Graph.shortestPath(g, 0));
    }

    @Test
    @DisplayName("Тупой тест")
    void testLoop() {
        int[][] adjMatrix = {
                {10, 10, 25, 0},
                {10, 0, 0, 15},
                {25, 0, 0, 0},
                {0, 15, 0, 0}
        };

        Graph graph = new Graph(adjMatrix, 4);
        int[] distances = Graph.shortestPath(graph, 0);

        assertArrayEquals(new int[]{0, 10, 25, 25}, distances);
    }

    @Test
    @DisplayName("")
    void testDirectedGraph() {
        int[][] mat = {
                {0, 4, 12, 0},
                {0, 0, 3, 7},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };

        Graph graph = new Graph(mat, 4);
        int[] distances = Graph.shortestPath(graph, 0);

        assertArrayEquals(new int[]{0, 4, 7, 9}, distances);
    }

    @Test
    void testNoWay(){
        int[][] adjMatrix = {
                {0,10,0,0},
                {0,0,20,0},
                {0,0,0,0},
                {10,0,0,0}
        };
        Graph graph = new Graph(adjMatrix, 4);
        int[] distances = Graph.shortestPath(graph, 0);
        assertArrayEquals(new int[]{0,10,30,Integer.MAX_VALUE}, distances);
    }

    @Test
    void testDiffWays(){
        int[][] adjMatrix = {
                {0,100,1},
                {0,0,0},
                {0,1,0}
        };
        Graph graph = new Graph(adjMatrix, 3);
        int[] distances = Graph.shortestPath(graph, 0);
        assertArrayEquals(new int[]{0,2,1}, distances);
    }


}