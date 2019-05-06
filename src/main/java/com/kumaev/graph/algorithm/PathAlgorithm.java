package com.kumaev.graph.algorithm;

import com.kumaev.graph.Path;
import com.kumaev.graph.edge.Edge;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * An algorithm which computes a path between vertices.
 *
 * @param <V> the graph vertex class.
 */
@FunctionalInterface
public interface PathAlgorithm<V> {

    /**
     * Returns a path from a source vertex to a destination vertex.
     *
     * @param source the source vertex.
     * @param destination the destination vertex.
     * @param verticesToEdges the map of all vertices and all outgoing edges.
     *
     * @return a path if it exists or empty if it doesn't exist.
     */
    Optional<Path<V>> getPath(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges);
}