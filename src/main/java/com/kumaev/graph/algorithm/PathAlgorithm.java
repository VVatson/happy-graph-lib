package com.kumaev.graph.algorithm;

import com.kumaev.graph.edge.AbstractEdge;
import com.kumaev.graph.Path;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@FunctionalInterface
public interface PathAlgorithm<V> {

    Optional<Path<V>> getPath(V source, V destination, Map<V, Set<AbstractEdge<V>>> vertexToEdges);

}