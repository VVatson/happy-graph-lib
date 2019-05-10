package com.kumaev.graph.algorithm;

import com.kumaev.graph.Path;
import com.kumaev.graph.edge.Edge;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

abstract class AbstractPathAlgorithm<V> implements PathAlgorithm<V> {

    V source;
    V destination;
    Map<V, Set<Edge<V>>> verticesToEdges;

    Set<V> visited;
    Map<V, Edge<V>> predecessors;

    public Optional<Path<V>> getPath(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges) {
        initTraversalData(source, destination, verticesToEdges);

        startTraversalProcess();
        Optional<Path<V>> path = getPath();

        deinitTraversalData();
        return path;
    }

    private void initTraversalData(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges) {
        this.source = source;
        this.destination = destination;
        this.verticesToEdges = verticesToEdges;

        int verticesNumber = verticesToEdges.size();
        this.visited = new HashSet<>(verticesNumber);
        this.predecessors = new HashMap<>(verticesNumber);

        initSpecificAlgorithmTraversalData();
    }

    abstract void initSpecificAlgorithmTraversalData();

    abstract void startTraversalProcess();

    abstract Optional<Path<V>> getPath();

    Path<V> collectPath() {
        Path<V> path = new Path<>();
        V current = destination;

        while (!current.equals(source)) {
            Edge<V> edge = predecessors.get(current);
            path.add(edge);
            current = edge.getFrom();
        }
        Collections.reverse(path);
        return path;
    }

    private void deinitTraversalData() {
        this.source = null;
        this.destination = null;
        this.verticesToEdges = null;
        this.visited = null;
        this.predecessors = null;

        deinitSpecificAlgorithmTraversalData();
    }

    abstract void deinitSpecificAlgorithmTraversalData();
}
