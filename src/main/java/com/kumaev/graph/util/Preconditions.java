package com.kumaev.graph.util;

import static com.kumaev.graph.exception.SadnessExceptionConstants.*;

import com.kumaev.graph.algorithm.PathAlgorithm;
import com.kumaev.graph.edge.AbstractEdge;
import com.kumaev.graph.exception.YouMadeGraphLibSadException;

import java.util.Map;
import java.util.Set;

public final class Preconditions {
    private Preconditions() {
    }

    public static <V> void checkVertexNotNull(V vertex) {
        if (vertex == null) {
            throw new YouMadeGraphLibSadException(VERTEX_CANNOT_BE_NULL_MESSAGE, VERTEX_IS_NULL_SADNESS_CODE);
        }
    }

    public static <V> void checkVertex(V vertex, Map<V, Set<AbstractEdge<V>>> verticesToEdges) {
        checkVertexNotNull(vertex);

        if (!verticesToEdges.containsKey(vertex)) {
            throw new YouMadeGraphLibSadException(CANNOT_FIND_PATH_WHEN_VERTEX_IS_NOT_PRESENT_MESSAGE, VERTEX_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE);
        }
    }

    public static <V> void checkVertices(V source, V destination,
                                         Map<V, Set<AbstractEdge<V>>> verticesToEdges) {
        checkVertex(source, verticesToEdges);
        checkVertex(destination, verticesToEdges);
        if (source.equals(destination)) {
            throw new YouMadeGraphLibSadException(CANNOT_FIND_PATH_BETWEEN_SAME_VERTICES_MESSAGE, PATH_BETWEEN_SAME_VERTICES_SADNESS_CODE);
        }
    }

    public static <V> void checkEdge(V from, V to) {
        checkVertexNotNull(from);
        checkVertexNotNull(to);

        if (from.equals(to)) {
            throw new YouMadeGraphLibSadException(CANNOT_CREATE_EDGE_BETWEEN_SAME_VERTICES_MESSAGE, EDGE_BETWEEN_SAME_VERTICES_SADNESS_CODE);
        }
    }

    public static <V> void checkEdge(AbstractEdge<V> edge, Map<V, Set<AbstractEdge<V>>> verticesToEdges) {
        checkEdgeNotNull(edge);
        if (!verticesToEdges.containsKey(edge.getFrom())) {
            throw new YouMadeGraphLibSadException(CANNOT_CREATE_EDGE_WHEN_SOURCE_IS_NOT_PRESENT_MESSAGE, EDGE_SOURCE_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE);
        }
        if (!verticesToEdges.containsKey(edge.getTo())) {
            throw new YouMadeGraphLibSadException(CANNOT_CREATE_EDGE_WHEN_DESTINATION_IS_NOT_PRESENT_MESSAGE, EDGE_DESTINATION_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE);
        }
    }

    public static <V> void checkEdgeNotNull(AbstractEdge<V> edge) {
        if (edge == null) {
            throw new YouMadeGraphLibSadException(EDGE_CANNOT_BE_NULL_MESSAGE, EDGE_IS_NULL_SADNESS_CODE);
        }
    }

    public static void checkEdgeWeight(int weight) {
        if (weight < 1) {
            throw new YouMadeGraphLibSadException(EDGE_WEIGHT_MUST_BE_GREATER_THAN_ZERO_MESSAGE, EDGE_WEIGHT_IS_LESS_THAN_ONE_SADNESS_CODE);
        }
    }

    public static <V> void checkAlgorithmNotNull(PathAlgorithm<V> algorithm) {
        if (algorithm == null) {
            throw new YouMadeGraphLibSadException(ALGORITHM_CANNOT_BE_NULL_MESSAGE, ALGORITHM_IS_NULL_SADNESS_CODE);
        }
    }
}
