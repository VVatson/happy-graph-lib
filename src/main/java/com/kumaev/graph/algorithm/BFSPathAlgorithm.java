package com.kumaev.graph.algorithm;

import com.kumaev.graph.Path;
import com.kumaev.graph.edge.Edge;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class BFSPathAlgorithm<V> extends AbstractPathAlgorithm<V> {

    private Queue<V> traversalQueue;

    @Override
    void initSpecificAlgorithmTraversalData() {
        traversalQueue = new LinkedList<>();
    }

    @Override
    void startTraversalProcess() {
        visited.add(source);
        traversalQueue.add(source);

        while (!traversalQueue.isEmpty()) {
            V currentVertex = traversalQueue.poll();

            for (Edge<V> edge : verticesToEdges.get(currentVertex)) {
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

    @Override
    Optional<Path<V>> getPath() {
        if (!predecessors.containsKey(destination)) {
            return Optional.empty();
        }
        Path<V> path = collectPath();
        return Optional.of(path);
    }

    @Override
    void deinitSpecificAlgorithmTraversalData() {
        traversalQueue = null;
    }
}
