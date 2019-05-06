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

    Set<V> visited = new HashSet<>();
    Map<V, Edge<V>> predecessors = new HashMap<>();

    public Optional<Path<V>> getPath(V source, V destination, Map<V, Set<Edge<V>>> verticesToEdges) {
        this.source = source;
        this.destination = destination;
        this.verticesToEdges = verticesToEdges;
        startProcess();
        return getPath();
    }

    abstract void startProcess();

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
}
