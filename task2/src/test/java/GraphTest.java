import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itmo.Graph;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    @DisplayName("Тест пустого графа")
    void testEmptyGraph() {
        int[][] mat = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        List<String> breadcrumbs = new ArrayList<>();
        Graph graph = new Graph(mat, mat.length);
        int[] distances = Graph.shortestPath(graph, 0, breadcrumbs);

        assertArrayEquals(new int[]{0, Integer.MAX_VALUE, Integer.MAX_VALUE}, distances);
        assertEquals("[Start Vertex: 0, Visited Vertex: 0, Distance: 0, Marked Vertex 0 as known, No more reachable vertices]", breadcrumbs.toString());
    }

    @Test
    @DisplayName("Тест не связного графа")
    void testNonConnectedGraph() {
        int[][] mat = {
                {0, 10, 0},
                {10, 0, 0},
                {0, 0, 0}
        };

        Graph graph = new Graph(mat, mat.length);
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
        Graph g = new Graph(mat, mat.length);
        assertThrows(IllegalArgumentException.class, () -> Graph.shortestPath(g, 0));
    }

    @Test
    @DisplayName("Тест отсутствия графа")
    void testNoGraph() {
        int[][] mat = new int[0][0];
        Graph g = new Graph(mat, mat.length);
        assertThrows(IllegalArgumentException.class, () -> Graph.shortestPath(g, 0));
        Graph g2 = new Graph(mat, 1);
        assertThrows(IllegalArgumentException.class, () -> Graph.shortestPath(g2, 0));
    }

    @Test
    @DisplayName("Простой тест")
    void testLoop() {
        int[][] mat = {
                {10, 10, 25, 0},
                {10, 0, 0, 15},
                {25, 0, 0, 0},
                {0, 15, 0, 0}
        };

        Graph graph = new Graph(mat, mat.length);
        int[] distances = Graph.shortestPath(graph, 0);

        assertArrayEquals(new int[]{0, 10, 25, 25}, distances);
    }

    @Test
    @DisplayName("Тест ориентированного графа")
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
    @DisplayName("Тест отсутствия пути к одной из вершин")
    void testNoWay() {
        int[][] mat = {
                {0,10,0,0},
                {0,0,20,0},
                {0,0,0,0},
                {10,0,0,0}
        };

        List<String> breadcrumbs = new ArrayList<>();
        Graph graph = new Graph(mat, mat.length);
        int[] distances = Graph.shortestPath(graph, 0, breadcrumbs);
        assertArrayEquals(new int[]{0,10,30,Integer.MAX_VALUE}, distances);
        assertTrue(breadcrumbs.contains("No more reachable vertices"));
    }

    @Test
    @DisplayName("Тест разных путей к одной вершине")
    void testDiffWays(){
        int[][] mat = {
                {0,100,1},
                {0,0,0},
                {0,1,0}
        };
        Graph graph = new Graph(mat, mat.length);
        int[] distances = Graph.shortestPath(graph, 0);
        assertArrayEquals(new int[]{0,2,1}, distances);
    }

    @Test
    @DisplayName("Тест на несуществующую начальную вершину")
    void testInvalidStartVertex() {
        int[][] mat = {
                {0, 10, 0},
                {10, 0, 5},
                {0, 5, 0}
        };
        Graph graph = new Graph(mat, mat.length);
        assertThrows(IllegalArgumentException.class, () -> Graph.shortestPath(graph, -1));
        assertThrows(IllegalArgumentException.class, () -> Graph.shortestPath(graph, 3));
    }

    @Test
    @DisplayName("Тест двух путей до вершины")
    void testWorsePath() {
        int[][] mat = {
                {0, 1, 5},
                {0, 0, 10},
                {0, 0, 0}
        };
        List<String> breadcrumbs = new ArrayList<>();
        Graph graph = new Graph(mat, mat.length);
        int[] distances = Graph.shortestPath(graph, 0, breadcrumbs);
        assertArrayEquals(new int[]{0, 1, 5}, distances);

        assertTrue(breadcrumbs.contains("Start Vertex: 0"));
        assertTrue(breadcrumbs.contains("Marked Vertex 0 as known"));
        assertTrue(breadcrumbs.contains("Updated distance for vertex 1 to 1"));
        assertTrue(breadcrumbs.contains("Updated distance for vertex 2 to 5"));
        assertTrue(
                breadcrumbs.contains("Checking vertex 2: current distance = 5, new distance = 11"));
    }

}