package com.kumaev.graph.algorithm;

import com.kumaev.graph.Path;
import com.kumaev.graph.edge.Edge;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraPathAlgorithm<V> extends AbstractPathAlgorithm<V> {

    private Map<V, Integer> distances;
    private Queue<V> traversalQueue;

    @Override
    void initSpecificAlgorithmTraversalData() {
        distances = new HashMap<>(verticesToEdges.size());
        verticesToEdges.keySet().forEach(v -> distances.put(v, Integer.MAX_VALUE));
        traversalQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
    }

    @Override
    void startTraversalProcess() {
        visitSourceVertex();

        while (!traversalQueue.isEmpty()) {
            V currentVertex = traversalQueue.poll();
            if (currentVertex.equals(destination)) {
                return;
            }

            for (Edge<V> edge : verticesToEdges.get(currentVertex)) {
                Integer distToCurrentVertex = distances.get(currentVertex);
                relaxEdge(edge, distToCurrentVertex);
            }
        }
    }

    private void visitSourceVertex() {
        visited.add(source);
        distances.put(source, 0);
        traversalQueue.add(source);
    }

    private void relaxEdge(Edge<V> edge, Integer distToCurrentVertex) {
        V toVertex = edge.getTo();
        Integer candidateWeight = distToCurrentVertex + edge.getWeight();

        if (visited.add(toVertex)) {
            visitVertexByIncomingEdge(edge, candidateWeight);
        } else {
            Integer oldWeight = distances.get(toVertex);

            if (candidateWeight < oldWeight) {
                traversalQueue.remove(toVertex);
                visitVertexByIncomingEdge(edge, candidateWeight);
            }
        }
    }

    private void visitVertexByIncomingEdge(Edge<V> incomingEdge, Integer weight) {
        V toVertex = incomingEdge.getTo();
        predecessors.put(toVertex, incomingEdge);
        distances.put(toVertex, weight);
        traversalQueue.add(toVertex);
    }

    @Override
    Optional<Path<V>> getPath() {
        if (distances.get(destination).equals(Integer.MAX_VALUE)) {
            return Optional.empty();
        }
        Path<V> path = collectPath();
        return Optional.of(path);
    }

    @Override
    void deinitSpecificAlgorithmTraversalData() {
        this.distances = null;
        this.traversalQueue = null;
    }
}
