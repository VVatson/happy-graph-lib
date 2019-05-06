package com.kumaev.graph.edge;

import static com.kumaev.graph.util.Preconditions.checkEdgeWeight;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class WeightedEdge<V> extends AbstractEdge<V> {

    private int weight;

    private WeightedEdge(V from, V to, int weight) {
        super(from, to);
        this.weight = weight;
    }

    public static <V> WeightedEdge<V> of(V from, V to, int weight) {
        checkEdgeWeight(weight);
        return new WeightedEdge<>(from, to, weight);
    }

    @Override
    public Edge<V> revert() {
        return new WeightedEdge<>(to, from, weight);
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + from + " -(" + weight + ")- " + to + ")";
    }
}
