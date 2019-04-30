package com.kumaev.graph;

import com.kumaev.graph.edge.AbstractEdge;

import java.util.ArrayList;

public class Path<V> extends ArrayList<AbstractEdge<V>> {

    public int getDistance() {
        return this.stream().mapToInt(AbstractEdge::getWeight).sum();
    }
}
