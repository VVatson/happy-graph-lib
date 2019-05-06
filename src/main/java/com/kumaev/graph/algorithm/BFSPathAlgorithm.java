package com.kumaev.graph.algorithm;

import com.kumaev.graph.Path;
import com.kumaev.graph.edge.Edge;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class BFSPathAlgorithm<V> extends AbstractPathAlgorithm<V> {

    private Queue<V> traversalQueue = new LinkedList<>();

    void startProcess() {
        visited.add(source);
        traversalQueue.add(source);

        while (!traversalQueue.isEmpty()) {
            V currentVertex = traversalQueue.poll();
            Iterator<Edge<V>> edgeIterator = verticesToEdges.get(currentVertex).iterator();

            while (edgeIterator.hasNext()) {
                Edge<V> edge = edgeIterator.next();
                V toVertex = edge.getTo();

                if (visited.add(toVertex)) {
                    traversalQueue.add(toVertex);
                    predecessors.put(toVertex, edge);

                    if (toVertex.equals(destination)) {
                        return;
                    }
                }
            }
        }
    }

    Optional<Path<V>> getPath() {
        if (!predecessors.containsKey(destination)) {
            return Optional.empty();
        }
        Path<V> path = collectPath();
        return Optional.of(path);
    }
}
