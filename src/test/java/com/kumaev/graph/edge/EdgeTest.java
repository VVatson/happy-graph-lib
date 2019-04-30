package com.kumaev.graph.edge;

import static com.kumaev.graph.exception.SadnessExceptionConstants.CANNOT_CREATE_EDGE_BETWEEN_SAME_VERTICES_MESSAGE;
import static com.kumaev.graph.exception.SadnessExceptionConstants.VERTEX_CANNOT_BE_NULL_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.kumaev.graph.exception.YouMadeGraphLibSadException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EdgeTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void canCreateEdge() {
        AbstractEdge<String> edge = Edge.of("one", "two");

        assertEquals("one", edge.getFrom());
        assertEquals("two", edge.getTo());
    }

    @Test
    public void canRevertEdge() {
        AbstractEdge<String> expected = Edge.of("two", "one");
        AbstractEdge<String> edge = Edge.of("one", "two");

        AbstractEdge<String> actual = edge.revert();

        assertEquals(expected, actual);
    }

    @Test
    public void cannotCreateEdgeBetweenSameVertices() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(CANNOT_CREATE_EDGE_BETWEEN_SAME_VERTICES_MESSAGE);

        Edge.of(1, 1);
    }

    @Test
    public void cannotCreateEdgeWhenSourceIsNull() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(VERTEX_CANNOT_BE_NULL_MESSAGE);

        Edge.of(null, 1);
    }

    @Test
    public void cannotCreateEdgeWhenDestinationIsNull() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(VERTEX_CANNOT_BE_NULL_MESSAGE);

        Edge.of(1, null);
    }

    @Test
    public void canCompareTwoEqualEdges() {
        Edge<Integer> first = Edge.of(1, 2);
        Edge<Integer> second = Edge.of(1, 2);

        assertEquals(first, second);
    }

    @Test
    public void canCompareTwoUnequalVertices() {
        Edge<Integer> first = Edge.of(1, 2);
        Edge<Integer> second = Edge.of(2, 3);

        assertNotEquals(first, second);
    }

    @Test
    public void canCompareTwoOppositeVertices() {
        Edge<Integer> first = Edge.of(1, 2);
        Edge<Integer> second = Edge.of(2, 1);

        assertNotEquals(first, second);
    }
}
