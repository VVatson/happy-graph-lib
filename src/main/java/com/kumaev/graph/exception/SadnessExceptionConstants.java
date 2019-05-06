package com.kumaev.graph.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class SadnessExceptionConstants {

    private static final Map<String, String> HAPPINESS_RECIPES;

    public static final String VERTEX_IS_NULL_SADNESS_CODE = "id1";
    public static final String VERTEX_CANNOT_BE_NULL_MESSAGE = "Vertex cannot be null.";

    public static final String EDGE_IS_NULL_SADNESS_CODE = "id2";
    public static final String EDGE_CANNOT_BE_NULL_MESSAGE = "Edge cannot be null.";

    public static final String ALGORITHM_IS_NULL_SADNESS_CODE = "id3";
    public static final String ALGORITHM_CANNOT_BE_NULL_MESSAGE = "Algorithm cannot be null.";

    public static final String EDGE_WEIGHT_IS_LESS_THAN_ONE_SADNESS_CODE = "id4";
    public static final String EDGE_WEIGHT_MUST_BE_GREATER_THAN_ZERO_MESSAGE = "Weight of edge must be greater than zero.";

    public static final String EDGE_BETWEEN_SAME_VERTICES_SADNESS_CODE = "id5";
    public static final String CANNOT_CREATE_EDGE_BETWEEN_SAME_VERTICES_MESSAGE = "Cannot create the edge between two the same vertices.";

    public static final String PATH_BETWEEN_SAME_VERTICES_SADNESS_CODE = "id6";
    public static final String CANNOT_FIND_PATH_BETWEEN_SAME_VERTICES_MESSAGE = "Cannot find a path between two the same vertices.";

    public static final String EDGE_SOURCE_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE = "id7";
    public static final String CANNOT_CREATE_EDGE_WHEN_SOURCE_IS_NOT_PRESENT_MESSAGE = "Cannot create the edge because the source is not present in the graph.";

    public static final String EDGE_DESTINATION_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE = "id8";
    public static final String CANNOT_CREATE_EDGE_WHEN_DESTINATION_IS_NOT_PRESENT_MESSAGE = "Cannot create the edge because the destination is not present in the graph.";

    public static final String VERTEX_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE = "id9";
    public static final String CANNOT_FIND_PATH_WHEN_VERTEX_IS_NOT_PRESENT_MESSAGE = "Cannot find a path because the vertex is not present in the graph.";

    static {
        Map<String, String> codeToRecipe = new HashMap<>();
        codeToRecipe.put(VERTEX_IS_NULL_SADNESS_CODE, "Please specify a not null vertex.");
        codeToRecipe.put(EDGE_IS_NULL_SADNESS_CODE, "Please specify a not null edge. " +
                "You can use UnweightedEdge.of(v1, v2) and WeightedEdge.of(v1, v2) methods to unweighted and weighted edges, respectively " +
                "or implement own by implementing the Edge interface.");
        codeToRecipe.put(ALGORITHM_IS_NULL_SADNESS_CODE, "Please specify a not null algorithm. " +
                "You can use one of the presented algorithms in the happy-graph-lib (BFSPathAlgorithm/DijkstraPathAlgorithm) " +
                "or implement own by implementing the PathAlgorithm interface.");
        codeToRecipe.put(EDGE_WEIGHT_IS_LESS_THAN_ONE_SADNESS_CODE, "GraphLib does not support negative edges. " +
                "Please specify a positive weight.");
        codeToRecipe.put(EDGE_BETWEEN_SAME_VERTICES_SADNESS_CODE, "GraphLib does not support loops. " +
                "Please specify different source and destination vertices to create an edge.");
        codeToRecipe.put(PATH_BETWEEN_SAME_VERTICES_SADNESS_CODE, "It is silly to find a path between the same vertices. " +
                "Please specify different source and destination vertices to find a path.");
        codeToRecipe.put(EDGE_SOURCE_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE, "Source of edge has not been added to graph. " +
                "Please add the vertex first and try adding the edge again.");
        codeToRecipe.put(EDGE_DESTINATION_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE, "Destination of edge has not been added to graph. " +
                "Please add the vertex first and try adding the edge again.");
        codeToRecipe.put(VERTEX_IS_NOT_PRESENT_IN_GRAPH_SADNESS_CODE, "Please specify presented vertices in the graph to find a path.");

        HAPPINESS_RECIPES = Collections.unmodifiableMap(codeToRecipe);
    }

    static String getHappinessRecipe(String sadnessCode) {
        return HAPPINESS_RECIPES.get(sadnessCode);
    }
}
