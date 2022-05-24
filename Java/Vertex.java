
import java.util.*;

public class Vertex<E> {
  private Graph<E> graph;
  private E data;
  private List<E> links;

  public Vertex(Graph<E> graph, E data) {
    this.graph = graph;
    this.data = data;
    this.links = new ArrayList<>();
  }

  @SuppressWarnings({ "unchecked", "varargs" })
  public void link(E... items) {
    for (final E item : items) {
      if (!links.contains(item)) links.add(item);
    }
  }

  public E getData() {
    return this.data;
  }

  public List<E> getLinks() {
    return this.links;
  }
  public String toString() {
    return data.toString() + "    links: " + this.links.stream().map(Object::toString).reduce("", (s1, s2) ->  s1 + s2+ ", ");
  }
}
