package com.kumaev.graph;

import static com.kumaev.graph.util.Preconditions.checkEdgeNotNull;

import com.kumaev.graph.edge.Edge;

public class UndirectedGraph<V, E extends Edge<V>> extends AbstractGraph<V, E> {

    @Override
    public void addEdge(E edge) {
        checkEdgeNotNull(edge);

        addEdge(edge, this);
        addEdge(edge.revert(), this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UndirectedGraph)) return false;
        return super.equals(o);
    }
}
