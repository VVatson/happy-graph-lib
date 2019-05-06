package com.kumaev.graph;

import com.kumaev.graph.edge.Edge;

import java.util.ArrayList;

public class Path<V> extends ArrayList<Edge<V>> {

    public int getDistance() {
        return this.stream().mapToInt(Edge::getWeight).sum();
    }
}
