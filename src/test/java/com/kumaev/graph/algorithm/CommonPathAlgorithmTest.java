package com.kumaev.graph.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.kumaev.graph.DirectedGraph;
import com.kumaev.graph.Graph;
import com.kumaev.graph.Path;
import com.kumaev.graph.UndirectedGraph;
import com.kumaev.graph.edge.Edge;
import com.kumaev.graph.edge.WeightedEdge;
import org.junit.Test;

import java.util.Optional;

public abstract class CommonPathAlgorithmTest {

    static PathAlgorithm<Integer> algorithm;

    @Test
    public void canFindPathInDirectedGraph() {
        Graph<Integer, Edge<Integer>> graph = new DirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(Edge.of(1, 2));
        graph.addEdge(Edge.of(2, 3));

        Path<Integer> path = graph.getPath(1, 3, algorithm).get();

        assertEquals("[(1 - 2), (2 - 3)]", path.toString());
    }

    @Test
    public void canFindPathInUndirectedGraph() {
        Graph<Integer, Edge<Integer>> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(Edge.of(1, 2));
        graph.addEdge(Edge.of(2, 3));

        Path<Integer> path = graph.getPath(3, 1, algorithm).get();

        assertEquals("[(3 - 2), (2 - 1)]", path.toString());
    }

    @Test
    public void canFindPathInDirectedWeightedGraph() {
        Graph<Integer, WeightedEdge<Integer>> graph = new DirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(WeightedEdge.of(1, 2, 3));
        graph.addEdge(WeightedEdge.of(2, 3, 5));

        Path<Integer> path = graph.getPath(1, 3, algorithm).get();

        assertEquals("[(1 -(3)- 2), (2 -(5)- 3)]", path.toString());
        assertEquals(8, path.getDistance());
    }

    @Test
    public void canFindPathInUndirectedWeightedGraph() {
        Graph<Integer, WeightedEdge<Integer>> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(WeightedEdge.of(1, 2, 3));
        graph.addEdge(WeightedEdge.of(2, 3, 5));

        Path<Integer> path = graph.getPath(3, 1, algorithm).get();

        assertEquals("[(3 -(5)- 2), (2 -(3)- 1)]", path.toString());
        assertEquals(8, path.getDistance());
    }

    @Test
    public void cannotFindPathInDirectedGraph() {
        Graph<Integer, Edge<Integer>> graph = new DirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(Edge.of(1, 2));
        graph.addEdge(Edge.of(2, 3));

        Optional<Path<Integer>> path = graph.getPath(3, 1, algorithm);

        assertFalse(path.isPresent());
    }

    @Test
    public void cannotFindPathBetweenDisconnectedVertices() {
        Graph<Integer, Edge<Integer>> graph = new DirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(Edge.of(1, 2));

        Optional<Path<Integer>> path = graph.getPath(1, 3, algorithm);

        assertFalse(path.isPresent());
    }

    static void initComplexGraph(Graph<Integer, WeightedEdge<Integer>> graph) {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        // long path but optimal distance
        graph.addEdge(WeightedEdge.of(1, 2, 2));
        graph.addEdge(WeightedEdge.of(2, 3, 2));
        graph.addEdge(WeightedEdge.of(3, 4, 2));

        // average path but not optimal distance
        graph.addEdge(WeightedEdge.of(2, 4, 5));

        // short path but not optimal distance
        graph.addEdge(WeightedEdge.of(1, 4, 8));
    }
}
