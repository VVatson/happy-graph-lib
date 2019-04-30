package com.kumaev.graph.algorithm;

import static org.junit.Assert.assertEquals;

import com.kumaev.graph.DirectedGraph;
import com.kumaev.graph.Graph;
import com.kumaev.graph.Path;
import com.kumaev.graph.UndirectedGraph;
import com.kumaev.graph.edge.WeightedEdge;
import org.junit.Before;
import org.junit.Test;

public class BFSPathAlgorithmTest extends CommonPathAlgorithmTest {

    @Before
    public void setUp() {
        algorithm = new BFSPathAlgorithm<>();
    }

    @Test
    public void canFindShortestNotOptimalPathInDirectedGraph() {
        Graph<Integer, WeightedEdge<Integer>> graph = new DirectedGraph<>();
        initComplexGraph(graph);

        Path<Integer> path = graph.getPath(1, 4, algorithm).get();

        assertEquals("[(1 -(8)- 4)]", path.toString());
        assertEquals(8, path.getDistance());
    }

    @Test
    public void canFindShortestNotOptimalPathInUndirectedGraph() {
        Graph<Integer, WeightedEdge<Integer>> graph = new UndirectedGraph<>();
        initComplexGraph(graph);

        Path<Integer> path = graph.getPath(4, 1, algorithm).get();

        assertEquals("[(4 -(8)- 1)]", path.toString());
        assertEquals(8, path.getDistance());
    }
}
