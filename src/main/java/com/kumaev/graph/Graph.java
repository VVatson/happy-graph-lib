package com.kumaev.graph;

import static com.kumaev.graph.util.Preconditions.checkAlgorithmNotNull;
import static com.kumaev.graph.util.Preconditions.checkEdge;
import static com.kumaev.graph.util.Preconditions.checkVertexNotNull;
import static com.kumaev.graph.util.Preconditions.checkVertices;

import com.kumaev.graph.algorithm.BFSPathAlgorithm;
import com.kumaev.graph.algorithm.PathAlgorithm;
import com.kumaev.graph.edge.AbstractEdge;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
public abstract class Graph<V, E extends AbstractEdge<V>> {

    private Map<V, Set<AbstractEdge<V>>> verticesToEdges = new HashMap<>();

    public void addVertex(V vertex) {
        checkVertexNotNull(vertex);

        verticesToEdges.putIfAbsent(vertex, new HashSet<>());
    }

    public abstract void addEdge(E edge);

    public Optional<Path<V>> getPath(V source, V destination) {
        return getPath(source, destination, new BFSPathAlgorithm<>());
    }

    public Optional<Path<V>> getPath(V source, V destination, PathAlgorithm<V> pathAlgorithm) {
        return getPath(source, destination, this, pathAlgorithm);
    }

    private static <V, E extends AbstractEdge<V>> Optional<Path<V>> getPath(V source, V destination,
                                                                            Graph<V, E> graph,
                                                                            PathAlgorithm<V> algorithm) {
        checkVertices(source, destination, graph.getVerticesToEdges());
        checkAlgorithmNotNull(algorithm);

        return algorithm.getPath(source, destination, graph.getVerticesToEdges());
    }

    static <V, E extends AbstractEdge<V>> void addEdge(AbstractEdge<V> edge, Graph<V, E> graph) {
        Map<V, Set<AbstractEdge<V>>> verticesToEdges = graph.getVerticesToEdges();
        checkEdge(edge, verticesToEdges);

        Set<AbstractEdge<V>> edges = verticesToEdges.get(edge.getFrom());
        edges.add(edge);
    }
}
