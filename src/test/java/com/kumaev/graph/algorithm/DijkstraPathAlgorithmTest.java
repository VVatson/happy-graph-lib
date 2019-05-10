package com.kumaev.graph.algorithm;

import static org.junit.Assert.assertEquals;

import com.kumaev.graph.DirectedGraph;
import com.kumaev.graph.Graph;
import com.kumaev.graph.Path;
import com.kumaev.graph.UndirectedGraph;
import com.kumaev.graph.edge.WeightedEdge;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraPathAlgorithmTest extends CommonPathAlgorithmTest {

    @BeforeClass
    public static void setUp() {
        algorithm = new DijkstraPathAlgorithm<>();
    }

    @Test
    public void canFindOptimalPathInDirectedGraph() {
        Graph<Integer, WeightedEdge<Integer>> graph = new DirectedGraph<>();
        initComplexGraph(graph);

        Path<Integer> path = graph.getPath(1, 4, algorithm).get();

        assertEquals("[(1 -(2)- 2), (2 -(2)- 3), (3 -(2)- 4)]", path.toString());
        assertEquals(6, path.getDistance());
    }

    @Test
    public void canFindOptimalPathInUndirectedGraph() {
        Graph<Integer, WeightedEdge<Integer>> graph = new UndirectedGraph<>();
        initComplexGraph(graph);

        Path<Integer> path = graph.getPath(4, 1, algorithm).get();

        assertEquals("[(4 -(2)- 3), (3 -(2)- 2), (2 -(2)- 1)]", path.toString());
        assertEquals(6, path.getDistance());
    }
}
