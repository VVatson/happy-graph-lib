package com.kumaev.graph;

import static org.junit.Assert.assertEquals;

import com.kumaev.graph.edge.Edge;
import com.kumaev.graph.edge.UnweightedEdge;
import com.kumaev.graph.exception.YouMadeGraphLibSadException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UndirectedGraphTest {

    private UndirectedGraph<Integer, UnweightedEdge<Integer>> graph;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        graph = new UndirectedGraph<>();
    }

    @Test
    public void canAddEdge() {
        Set<Edge<Integer>> expectedAdjEdgesFromOne = new HashSet<>();
        expectedAdjEdgesFromOne.add(UnweightedEdge.of(1, 2));
        Set<Edge<Integer>> expectedAdjEdgesFromTwo = new HashSet<>();
        expectedAdjEdgesFromTwo.add(UnweightedEdge.of(2, 1));

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(UnweightedEdge.of(1, 2));
        Map<Integer, Set<Edge<Integer>>> verticesToEdges = graph.getVerticesToEdges();

        assertEquals(expectedAdjEdgesFromOne, verticesToEdges.get(1));
        assertEquals(expectedAdjEdgesFromTwo, verticesToEdges.get(2));
    }

    @Test
    public void cannotAddNullEdge() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage("Edge cannot be null.");

        graph.addEdge(null);
    }

    @Test
    public void canGetPath() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(UnweightedEdge.of(1, 2));
        graph.addEdge(UnweightedEdge.of(2, 3));

        Path<Integer> actual = graph.getPath(3, 1).get();

        assertEquals("[(3 - 2), (2 - 1)]", actual.toString());
    }
}
