package com.kumaev.graph.edge;

import static com.kumaev.graph.exception.SadnessExceptionConstants.CANNOT_CREATE_EDGE_BETWEEN_SAME_VERTICES_MESSAGE;
import static com.kumaev.graph.exception.SadnessExceptionConstants.EDGE_WEIGHT_MUST_BE_GREATER_THAN_ZERO_MESSAGE;
import static com.kumaev.graph.exception.SadnessExceptionConstants.VERTEX_CANNOT_BE_NULL_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.kumaev.graph.exception.YouMadeGraphLibSadException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WeightedEdgeTest {

    private static int WEIGHT = 5;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void canCreateWeightedEdge() {
        AbstractEdge<String> edge = WeightedEdge.of("one", "two", WEIGHT);

        assertEquals("one", edge.getFrom());
        assertEquals("two", edge.getTo());
        assertEquals(WEIGHT, edge.getWeight());
    }

    @Test
    public void canRevertWeightedEdge() {
        AbstractEdge<String> expected = WeightedEdge.of("two", "one",WEIGHT);
        AbstractEdge<String> edge = WeightedEdge.of("one", "two", WEIGHT);

        AbstractEdge<String> actual = edge.revert();

        assertEquals(expected, actual);
    }

    @Test
    public void cannotCreateWeightedEdgeWithNegativeWeight() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(EDGE_WEIGHT_MUST_BE_GREATER_THAN_ZERO_MESSAGE);

        WeightedEdge.of(1, 1, -1);
    }

    @Test
    public void cannotCreateWeightedEdgeBetweenSameVertices() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(CANNOT_CREATE_EDGE_BETWEEN_SAME_VERTICES_MESSAGE);

        WeightedEdge.of(1, 1, WEIGHT);
    }

    @Test
    public void cannotCreateWeightedEdgeWhenSourceIsNull() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(VERTEX_CANNOT_BE_NULL_MESSAGE);

        WeightedEdge.of(null, 1, WEIGHT);
    }

    @Test
    public void cannotCreateWeightedEdgeWhenDestinationIsNull() {
        expectedEx.expect(YouMadeGraphLibSadException.class);
        expectedEx.expectMessage(VERTEX_CANNOT_BE_NULL_MESSAGE);

        WeightedEdge.of(1, null, WEIGHT);
    }

    @Test
    public void canCompareTwoEqualWeightedEdges() {
        WeightedEdge<Integer> first = WeightedEdge.of(1, 2, WEIGHT);
        WeightedEdge<Integer> second = WeightedEdge.of(1, 2, WEIGHT);

        assertEquals(first, second);
    }

    @Test
    public void canCompareTwoUnequalWeightedEdges() {
        WeightedEdge<Integer> first = WeightedEdge.of(1, 2, WEIGHT);
        WeightedEdge<Integer> second = WeightedEdge.of(1, 2, 6);

        assertNotEquals(first, second);
    }

    @Test
    public void canCompareTwoOppositeWeightedEdges() {
        WeightedEdge<Integer> first = WeightedEdge.of(1, 2, WEIGHT);
        WeightedEdge<Integer> second = WeightedEdge.of(2, 1, WEIGHT);

        assertNotEquals(first, second);
    }

    @Test
    public void canCompareTwoWeightedEdgesUsingCompareToMethod() {
        WeightedEdge<Integer> first = WeightedEdge.of(1, 2, WEIGHT);
        WeightedEdge<Integer> second = WeightedEdge.of(2, 3, WEIGHT);
        WeightedEdge<Integer> third = WeightedEdge.of(3, 4, WEIGHT - 1);
        WeightedEdge<Integer> fourth = WeightedEdge.of(4, 5, WEIGHT + 1);

        assertEquals(0, first.compareTo(second));
        assertEquals(1, first.compareTo(third));
        assertEquals(-1, first.compareTo(fourth));
    }
}
