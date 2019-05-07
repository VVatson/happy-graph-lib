package com.kumaev.graph;

import static com.kumaev.graph.util.Preconditions.checkAlgorithmNotNull;
import static com.kumaev.graph.util.Preconditions.checkEdge;
import static com.kumaev.graph.util.Preconditions.checkVertexNotNull;
import static com.kumaev.graph.util.Preconditions.checkVertices;

import com.kumaev.graph.algorithm.BFSPathAlgorithm;
import com.kumaev.graph.algorithm.PathAlgorithm;
import com.kumaev.graph.edge.Edge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

abstract class AbstractGraph<V, E extends Edge<V>> implements Graph<V, E> {

    private Map<V, Set<Edge<V>>> verticesToEdges = new HashMap<>();

    @Override
    public Map<V, Set<Edge<V>>> getVerticesToEdges() {
        return verticesToEdges;
    }

    @Override
    public void addVertex(V vertex) {
        checkVertexNotNull(vertex);

        verticesToEdges.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public Optional<Path<V>> getPath(V source, V destination) {
        return getPath(source, destination, new BFSPathAlgorithm<>());
    }

    @Override
    public Optional<Path<V>> getPath(V source, V destination, PathAlgorithm<V> pathAlgorithm) {
        return getPath(source, destination, this, pathAlgorithm);
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

    private static <V, E extends Edge<V>> Optional<Path<V>> getPath(V source, V destination,
                                                                    Graph<V, E> graph,
                                                                    PathAlgorithm<V> algorithm) {
        checkVertices(source, destination, graph.getVerticesToEdges());
        checkAlgorithmNotNull(algorithm);

        return algorithm.getPath(source, destination, graph.getVerticesToEdges());
    }

    static <V, E extends Edge<V>> void addEdge(Edge<V> edge, Graph<V, E> graph) {
        Map<V, Set<Edge<V>>> verticesToEdges = graph.getVerticesToEdges();
        checkEdge(edge, verticesToEdges);

        Set<Edge<V>> edges = verticesToEdges.get(edge.getFrom());
        edges.add(edge);
    }
}
