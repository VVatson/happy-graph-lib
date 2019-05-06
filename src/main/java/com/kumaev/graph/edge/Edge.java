package com.kumaev.graph.edge;

/**
 * Graph's edge interface that represents some connection between two vertices in a graph.
 *
 * @param <V> the graph vertex class that must contain properly overridden {@link Object#equals(Object)}
 *           and {@link Object#hashCode()} methods.
 */
public interface Edge<V> extends Comparable<Edge<V>> {

    /**
     * Returns the vertex from which this edge originates.
     *
     * @return the from vertex.
     */
    V getFrom();

    /**
     * Returns the vertex to which this edge is pointing.
     *
     * @return the to vertex.
     */
    V getTo();

    /**
     * Reverts this edge.
     *
     * @return a new edge whose from and to vertices swapped.
     */
    Edge<V> revert();

    /**
     * Returns the weight of this edge.
     *
     * @return the edge's weight.
     */
    int getWeight();
}
