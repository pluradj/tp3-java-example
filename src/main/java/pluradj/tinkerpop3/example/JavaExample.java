package pluradj.tinkerpop3.example;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

import java.util.ArrayList;

import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaExample {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JavaExample.class);

    public static void main(String[] args) {
        TinkerGraph graph = TinkerGraph.open();
        GraphTraversalSource g = graph.traversal();

        // see org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory.generateClassic()
        final Vertex marko = g.addV("name", "marko", "age", 29).next();
        final Vertex vadas = g.addV("name", "vadas", "age", 27).next();
        final Vertex lop = g.addV("name", "lop", "lang", "java").next();
        final Vertex josh = g.addV("name", "josh", "age", 32).next();
        final Vertex ripple = g.addV("name", "ripple", "lang", "java").next();
        final Vertex peter = g.addV("name", "peter", "age", 35).next();
        marko.addEdge("knows", vadas, "weight", 0.5f);
        marko.addEdge("knows", josh, "weight", 1.0f);
        marko.addEdge("created", lop, "weight", 0.4f);
        josh.addEdge("created", ripple, "weight", 1.0f);
        josh.addEdge("created", lop, "weight", 0.4f);
        peter.addEdge("created", lop, "weight", 0.2f);

        Vertex fromNode = g.V().has("name", "marko").next();
        Vertex toNode = g.V().has("name", "peter").next();
        ArrayList list = new ArrayList();
        g.V(fromNode).repeat(both().simplePath()).until(is(toNode)).limit(1).path().fill(list);
        LOGGER.info(list.toString());
        System.exit(0);
    }
}
