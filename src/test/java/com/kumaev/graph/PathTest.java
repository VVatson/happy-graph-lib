package com.kumaev.graph;

import static org.junit.Assert.assertEquals;

import com.kumaev.graph.edge.WeightedEdge;
import org.junit.Test;

public class PathTest {

    @Test
    public void canGetZeroDistanceWhenPathIsEmpty() {
        Path<Integer> path = new Path<>();

        assertEquals(0, path.getDistance());
    }

    @Test
    public void canComputeTotalPathDistance() {
        Path<Integer> path = new Path<>();
        path.add(WeightedEdge.of(1, 2, 2));
        path.add(WeightedEdge.of(2, 3, 3));

        assertEquals(5, path.getDistance());
    }

}
