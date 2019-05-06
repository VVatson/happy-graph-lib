package com.kumaev.graph.edge;

import static com.kumaev.graph.util.Preconditions.checkEdge;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class AbstractEdge<V> implements Edge<V> {

    final V from;
    final V to;

    protected AbstractEdge(V from, V to) {
        checkEdge(from, to);

        this.from = from;
        this.to = to;
    }

    @Override
    public V getFrom() {
        return from;
    }

    @Override
    public V getTo() {
        return to;
    }

    @Override
    public int compareTo(Edge<V> another) {
        return this.getWeight() - another.getWeight();
    }
}
