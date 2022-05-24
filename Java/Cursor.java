import java.util.*;

public class Cursor<E> {
  private final List<Vertex<E>> vertices;

  public Cursor(final List<Vertex<E>> vertices) {
    this.vertices = vertices;
  }

  @SuppressWarnings({ "unchecked", "varargs" })
  public Cursor<E> linked(E... items) {
    final List<Vertex<E>> result = new ArrayList<>();
    for (final Vertex<E> vertex : this.vertices) {
      final List<E> links = vertex.getLinks();
      if (Arrays.asList(items).stream().allMatch(links::contains)) result.add(vertex);
    }
    return new Cursor<>(result);
  }

  public List<Vertex<E>> getVertices() {
    return this.vertices;
  }
}
