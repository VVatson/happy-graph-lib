package com.kumaev.graph;

import com.kumaev.graph.edge.Edge;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DirectedGraph<V, E extends Edge<V>> extends AbstractGraph<V, E> {

    @Override
    public void addEdge(E edge) {
        addEdge(edge, this);
    }
}
