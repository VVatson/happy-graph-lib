package com.kumaev.graph;

import static com.kumaev.graph.util.Preconditions.checkEdgeNotNull;

import com.kumaev.graph.edge.Edge;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UndirectedGraph<V, E extends Edge<V>> extends AbstractGraph<V, E> {

    @Override
    public void addEdge(E edge) {
        checkEdgeNotNull(edge);

        addEdge(edge, this);
        addEdge(edge.revert(), this);
    }
}
