package com.kumaev.graph;

import com.kumaev.graph.edge.AbstractEdge;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DirectedGraph<V, E extends AbstractEdge<V>> extends Graph<V, E> {

    @Override
    public void addEdge(E edge) {
        addEdge(edge, this);
    }
}
