package com.kumaev.graph;

import static com.kumaev.graph.exception.SadnessExceptionConstants.*;
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

public class DirectedGraphTest {

    private DirectedGraph<Integer, UnweightedEdge<Integer>> graph;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        graph = new DirectedGraph<>();
    }

    @Test
    public void canAddVertex() {
        Set<Edge<Integer>> expectedAdjEdges = new HashSet<>();

        graph.addVertex(1);
        Map<Integer, Set<Edge<Integer>>> verticesToEdges = graph.getVerticesToEdges();

        assertEquals(expectedAdjEdges, verticesToEdges.get(1));
    }

    @Test
    public void canAddEdge() {
        Set<Edge<Integer>> expectedAdjEdgesFromOne = new HashSet<>();
        expectedAdjEdgesFromOne.add(UnweightedEdge.of(1, 2));
        Set<Edge<Integer>> expectedAdjEdgesFromTwo = new HashSet<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(UnweightedEdge.of(1, 2));
        Map<Integer, Set<Edge<Integer>>> verticesToEdges = graph.getVerticesToEdges();

        assertEquals(expectedAdjEdgesFromOne, verticesToEdges.get(1));
        assertEquals(expectedAdjEdgesFromTwo, verticesToEdges.get(2));
    }

    @Test
    public void canGetPath() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(UnweightedEdge.of(1, 2));
        graph.addEdge(UnweightedEdge.of(2, 3));

        Path<Integer> actual = graph.getPath(1, 3).get();

        assertEquals("[(1 - 2), (2 - 3)]", actual.toString());
    }

    @Test
    public void cannotAddNullVertex() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(VERTEX_CANNOT_BE_NULL_MESSAGE);

        graph.addVertex(null);
    }

    @Test
    public void cannotAddNullEdge() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(EDGE_CANNOT_BE_NULL_MESSAGE);

        graph.addEdge(null);
    }

    @Test
    public void cannotAddEdgeWhenSourceDoesNotExist() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(CANNOT_CREATE_EDGE_WHEN_SOURCE_IS_NOT_PRESENT_MESSAGE);

        graph.addVertex(2);
        graph.addEdge(UnweightedEdge.of(1, 2));
    }

    @Test
    public void cannotAddEdgeWhenDestinationDoesNotExist() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(CANNOT_CREATE_EDGE_WHEN_DESTINATION_IS_NOT_PRESENT_MESSAGE);

        graph.addVertex(1);
        graph.addEdge(UnweightedEdge.of(1, 2));
    }

    @Test
    public void cannotGetPathWhenSourceIsNull() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(VERTEX_CANNOT_BE_NULL_MESSAGE);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(UnweightedEdge.of(1, 2));

        graph.getPath(null, 2);
    }

    @Test
    public void cannotGetPathWhenDestinationIsNull() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(VERTEX_CANNOT_BE_NULL_MESSAGE);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(UnweightedEdge.of(1, 2));

        graph.getPath(2, null);
    }

    @Test
    public void cannotGetPathWhenSourceIsNotPresentInGraph() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(CANNOT_FIND_PATH_WHEN_VERTEX_IS_NOT_PRESENT_MESSAGE);

        graph.addVertex(2);

        graph.getPath(1, 2);
    }

    @Test
    public void cannotGetPathWhenDestinationIsNotPresentInGraph() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(CANNOT_FIND_PATH_WHEN_VERTEX_IS_NOT_PRESENT_MESSAGE);

        graph.addVertex(1);

        graph.getPath(1, 2);
    }

    @Test
    public void cannotGetPathBetweenTwoTheSameVertices() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(CANNOT_FIND_PATH_BETWEEN_SAME_VERTICES_MESSAGE);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(UnweightedEdge.of(1, 2));

        graph.getPath(1, 1);
    }

    @Test
    public void cannotGetPathWhenAlgorithmIsNul() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(ALGORITHM_CANNOT_BE_NULL_MESSAGE);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(UnweightedEdge.of(1, 2));

        graph.getPath(1, 2, null);
    }
}
