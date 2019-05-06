package com.kumaev.graph;

import com.kumaev.graph.algorithm.PathAlgorithm;
import com.kumaev.graph.edge.Edge;
import com.kumaev.graph.exception.YouMadeGraphLibSadException;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Common interface for interacting with a graph.
 *
 * @param <V> the graph vertex class that must contain properly overridden {@link Object#equals(Object)}
 *           and {@link Object#hashCode()} methods.
 * @param <E> the graph edge class that must contain properly overridden {@link Object#equals(Object)}
 *           and {@link Object#hashCode()} methods.
 */
public interface Graph<V, E extends Edge<V>> {

    /**
     * Returns the graph representation as a map of all vertices and all outgoing edges.
     *
     * @return map of all vertices and all outgoing edges.
     */
    Map<V, Set<Edge<V>>> getVerticesToEdges();

    /**
     * Creates a new vertex in this graph.
     *
     * @param vertex new vertex to be added in the graph.
     *
     * @throws YouMadeGraphLibSadException if the vertex is null.
     */
    void addVertex(V vertex);

    /**
     * Creates a new edge in this graph.
     *
     * @param edge new edge to be added in the graph.
     *
     * @throws YouMadeGraphLibSadException if the edge is null.
     * @throws YouMadeGraphLibSadException if the graph does not contain the source or destination of the edge.
     */
    void addEdge(E edge);

    /**
     * Returns a path from a source vertex to a destination vertex.
     * By default, the BFS algorithm is used to compute a path. If you wish to use other algorithms
     * please use {@link Graph#getPath(Object, Object, PathAlgorithm)} method.
     *
     * @param source the source vertex.
     * @param destination the destination vertex.
     *
     * @return a path if it exists or empty if it doesn't exist.
     *
     * @throws YouMadeGraphLibSadException if the source or the destination is null.
     * @throws YouMadeGraphLibSadException if the graph does not contain the source or destination vertices.
     * @throws YouMadeGraphLibSadException if the source and the destination are the same vertices.
     */
    Optional<Path<V>> getPath(V source, V destination);

    /**
     * Returns a path from a source vertex to a destination vertex.
     *
     * @param source the source vertex.
     * @param destination the destination vertex.
     * @param pathAlgorithm the algorithm to compute a path.
     *
     * @return a path if it exists or empty if it doesn't exist.
     *
     * @throws YouMadeGraphLibSadException if the algorithm is null.
     * @throws YouMadeGraphLibSadException if the source or the destination is null.
     * @throws YouMadeGraphLibSadException if the graph does not contain the source or destination vertices.
     * @throws YouMadeGraphLibSadException if the source and the destination are the same vertices.
     */
    Optional<Path<V>> getPath(V source, V destination, PathAlgorithm<V> pathAlgorithm);
}
