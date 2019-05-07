package com.kumaev.graph.edge;

import static com.kumaev.graph.util.Preconditions.checkEdge;

import java.util.Objects;

abstract class AbstractEdge<V> implements Edge<V> {

    final V from;
    final V to;

    AbstractEdge(V from, V to) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEdge<?> that = (AbstractEdge<?>) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
