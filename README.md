# Happy Graph Library [![Build Status](https://travis-ci.org/VVatson/happy-graph-lib.svg?branch=master)](https://travis-ci.org/VVatson/happy-graph-lib)

Greetings, my future user!

I'm Happy Graph Library(HGL). And I'm so simple and intuitive, so I'm Happy.


## How to use

1. Build and deploy generated JAR in your local maven repository

```bash
mvn clean install
```
2. Add the following maven dependency to your project pom.xml

```bash
  <dependency>
      <groupId>com.kumaev.graph</groupId>
      <artifactId>happy-graph-lib</artifactId>
      <version>1.0.0</version>
 </dependency>
```
3. Use and have fun!

## About me
#### Features
- Directed and undirected graphs
- Weighted and unweighted edges
- Any type of vertex that overrides `equals` and `hashCode` methods
- Two algorithms to find a path in a graph 
  * [Breadth-first search (BFS)](https://en.wikipedia.org/wiki/Breadth-first_search) is an algorithm for traversing or searching tree or graph data structures
  * [Dijkstra's Shortest Path First (SPF)](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) is an algorithm for finding the shortest paths between nodes in a graph
- Ability to use own type of edge by extending `AbstractEdge<V>` class
- Ability to use own algorithm by implementing the `PathAlgorithm<V>` interface
- Ability to choose a type of exception to catch
  * `IllegalArgumentException` is well-known exception to indicate that a method has been passed an illegal or inappropriate argument 
  * `YouMadeGraphLibSadException` is a narrowing HGL exception with the ability to get a recipe on how to make the HGL happy

#### Assumptions
- Loops
- Negative weight of edges
- Thread-safe

## Examples
```java
import com.kumaev.graph.DirectedGraph;
import com.kumaev.graph.Graph;
import com.kumaev.graph.Path;
import com.kumaev.graph.UndirectedGraph;
import com.kumaev.graph.edge.Edge;
import com.kumaev.graph.edge.WeightedEdge;

public abstract class CommonPathAlgorithmTest  {

    static PathAlgorithm<Integer> algorithm;

    @Test
    public void canFindPathInDirectedGraph() {
        Graph<Integer, Edge<Integer>> graph = new DirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(Edge.of(1, 2));
        graph.addEdge(Edge.of(2, 3));

        Path<Integer> path = graph.getPath(1, 3, algorithm).get();

        assertEquals("[(1 - 2), (2 - 3)]", path.toString());
    }
    
    @Test
    public void canFindPathInUndirectedWeightedGraph() {
        Graph<Integer, WeightedEdge<Integer>> graph = new UndirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(WeightedEdge.of(1, 2, 3));
        graph.addEdge(WeightedEdge.of(2, 3, 5));

        Path<Integer> path = graph.getPath(3, 1, algorithm).get();

        assertEquals("[(3 -(5)- 2), (2 -(3)- 1)]", path.toString());
        assertEquals(8, path.getDistance());
    }
}
```
