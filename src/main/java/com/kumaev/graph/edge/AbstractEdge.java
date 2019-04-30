package com.kumaev.graph.edge;

import static com.kumaev.graph.util.Preconditions.checkEdge;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class AbstractEdge<V> implements Comparable<AbstractEdge<V>> {

    final V from;
    final V to;

    AbstractEdge(V from, V to) {
        checkEdge(from, to);

        this.from = from;
        this.to = to;
    }

    public abstract AbstractEdge<V> revert();

    public abstract int getWeight();

    public int compareTo(AbstractEdge<V> another) {
        return this.getWeight() - another.getWeight();
    }
}
