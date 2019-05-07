package com.kumaev.graph;

import com.kumaev.graph.edge.Edge;

public class DirectedGraph<V, E extends Edge<V>> extends AbstractGraph<V, E> {

    @Override
    public void addEdge(E edge) {
        addEdge(edge, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectedGraph)) return false;
        return super.equals(o);
    }
}
