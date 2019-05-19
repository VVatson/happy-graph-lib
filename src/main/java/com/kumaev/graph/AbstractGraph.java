package com.kumaev.graph;

import static com.kumaev.graph.util.Preconditions.checkAlgorithmNotNull;
import static com.kumaev.graph.util.Preconditions.checkEdge;
import static com.kumaev.graph.util.Preconditions.checkVertexNotNull;
import static com.kumaev.graph.util.Preconditions.checkVertices;

import com.kumaev.graph.algorithm.BFSPathAlgorithm;
import com.kumaev.graph.algorithm.PathAlgorithm;
import com.kumaev.graph.edge.Edge;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

abstract class AbstractGraph<V, E extends Edge<V>> implements Graph<V, E> {

    private Map<V, Set<Edge<V>>> verticesToEdges = new HashMap<>();

    Map<V, Set<Edge<V>>> getVerticesToEdges() {
        return verticesToEdges;
    }

    @Override
    public void addVertex(V vertex) {
        checkVertexNotNull(vertex);

        verticesToEdges.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public void addEdge(E edge) {
        checkEdge(edge, verticesToEdges);

        verticesToEdges.compute(edge.getFrom(), (vertex, edges) -> {
            edges.add(edge);
            return edges;
        });
    }

    @Override
    public Optional<Path<V>> getPath(V source, V destination) {
        return getPath(source, destination, new BFSPathAlgorithm<>());
    }

    @Override
    public Optional<Path<V>> getPath(V source, V destination, PathAlgorithm<V> algorithm) {
        checkVertices(source, destination, verticesToEdges);
        checkAlgorithmNotNull(algorithm);

        return algorithm.getPath(source, destination, Collections.unmodifiableMap(verticesToEdges));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractGraph)) return false;
        AbstractGraph<?, ?> that = (AbstractGraph<?, ?>) o;
        return Objects.equals(verticesToEdges, that.verticesToEdges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(verticesToEdges);
    }
}
