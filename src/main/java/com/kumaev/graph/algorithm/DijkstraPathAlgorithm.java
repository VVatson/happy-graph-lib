package com.kumaev.graph.algorithm;

import com.kumaev.graph.Path;
import com.kumaev.graph.edge.AbstractEdge;

import java.util.*;

public class DijkstraPathAlgorithm<V> extends AbstractPathAlgorithm<V> {

    private Map<V, Integer> distances = new HashMap<>();
    private Queue<V> traversalQueue = new PriorityQueue<>(Comparator.comparingInt(v -> distances.get(v)));

    void startProcess() {
        fillDistances();
        visitSourceVertex();

        while (!traversalQueue.isEmpty()) {
            V currentVertex = traversalQueue.poll();
            if (currentVertex.equals(destination)) {
                return;
            }
            Iterator<AbstractEdge<V>> edgeIterator = verticesToEdges.get(currentVertex).iterator();

            while (edgeIterator.hasNext()) {
                AbstractEdge<V> edge = edgeIterator.next();
                Integer distToCurrentVertex = distances.get(currentVertex);
                relaxEdge(edge, distToCurrentVertex);
            }
        }
    }

    private void fillDistances() {
        verticesToEdges.keySet().forEach(v -> distances.put(v, Integer.MAX_VALUE));
    }

    private void visitSourceVertex() {
        visited.add(source);
        distances.put(source, 0);
        traversalQueue.add(source);
    }

    private void relaxEdge(AbstractEdge<V> edge, Integer distToCurrentVertex) {
        V toVertex = edge.getTo();
        Integer candidateWeight = distToCurrentVertex + edge.getWeight();

        if (visited.add(toVertex)) {
            predecessors.put(toVertex, edge);
            distances.put(toVertex, candidateWeight);
            traversalQueue.add(toVertex);
        } else {
            Integer oldWeight = distances.get(toVertex);

            if (candidateWeight < oldWeight) {
                predecessors.put(toVertex, edge);
                distances.put(toVertex, candidateWeight);
                triggerSorting();
            }
        }
    }

    private void triggerSorting() {
        traversalQueue.add(traversalQueue.poll()); // hack to call the comparing process and update the top item when toVertex becomes the highest priority
    }

    Optional<Path<V>> getPath() {
        if (distances.get(destination).equals(Integer.MAX_VALUE)) {
            return Optional.empty();
        }
        Path<V> path = collectPath();
        return Optional.of(path);
    }
}
