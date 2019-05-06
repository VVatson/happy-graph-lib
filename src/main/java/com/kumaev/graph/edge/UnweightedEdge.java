package com.kumaev.graph.edge;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnweightedEdge<V> extends AbstractEdge<V> {

    private UnweightedEdge(V from, V to) {
        super(from, to);
    }

    public static <V> UnweightedEdge<V> of(V from, V to) {
        return new UnweightedEdge<>(from, to);
    }

    @Override
    public Edge<V> revert() {
        return new UnweightedEdge<>(to, from);
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
