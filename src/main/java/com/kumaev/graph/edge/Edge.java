package com.kumaev.graph.edge;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Edge<V> extends AbstractEdge<V> {

    private Edge(V from, V to) {
        super(from, to);
    }

    public static <V> Edge<V> of(V from, V to) {
        return new Edge<>(from, to);
    }

    @Override
    public AbstractEdge<V> revert() {
        return new Edge<>(to, from);
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String toString() {
        return "(" + from + " - " + to + ")";
    }
}
