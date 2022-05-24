import java.util.*;
import java.util.function.Predicate;

public class Graph<E> {
  private final List<Vertex<E>> vertices;
  public Graph() {
    this.vertices = new ArrayList<>();
  }
  public void add(final E item) {
    if (!this.vertices.stream().anyMatch(v -> v.getData() == item)) {
      final Vertex<E> vertex = new Vertex<>(this, item);
      this.vertices.add(vertex);
    }
  }

  @SuppressWarnings({ "unchecked", "varargs" })
  public void insert(final E... entries) {
    for (final E entry : entries) this.add(entry);
  }

  private Vertex<E> findVertex(final E data) {
    for (final Vertex<E> vertex : this.vertices) {
      if (vertex.getData() == data) return vertex;
    }
    return null;
  }

  @SuppressWarnings({ "unchecked", "varargs" })
  public void link(E from, E... to) {
    final Vertex<E> vertex = this.findVertex(from);
    if (vertex != null) vertex.link(to);
  }

  public void remove(E item) {
    final Vertex<E> vertex = this.findVertex(item);
    if (vertex != null) {
      vertices.remove(vertex);
      for (final Vertex<E> vert : vertices) vert.getLinks().remove(item);
    }
  }
 
  @SuppressWarnings({ "unchecked", "varargs" })
  public Cursor<E> select(final Predicate<? super E>... predicates) {
    final List<Vertex<E>> vertices = new ArrayList<>();
    for (final Vertex<E> vertex : this.vertices) {
      if (Arrays.asList(predicates).stream().allMatch(pr -> pr.test(vertex.getData()))) vertices.add(vertex);
    }
    return new Cursor<>(vertices);
  }

  public String toString() {
    String str = "";
    for (final Vertex<E> vertex: this.vertices) str += vertex.toString() + "\n";
    return str;
  }

}
