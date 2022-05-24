import java.util.function.Predicate;

public class Example {
  private static class Emperor {
    private String name;
    private String city;
    private int born;
    private String dynasty;

    public Emperor(final String name, final String city, final int born, final String dynasty) {
      this.name = name;
      this.city = city;
      this.born = born;
      this.dynasty = dynasty;
    }

    public String getName() {
      return this.name;
    }

    public String getCity() {
      return this.city;
    }

    public int getBorn() {
      return this.born;
    }

    public String getDynasty() {
      return this.dynasty;
    }

    public String toString() {
      return name;
    }
  }

  @SuppressWarnings({ "unchecked", "varargs" })
  public static void main(String[] args) {
    final Graph<Emperor> graph = new Graph<>();

    final Emperor marcus = new Emperor("Marcus Aurelius", "Rome", 121, "Antonine");
    final Emperor lucius = new Emperor("Lucius Verus", "Rome", 130, "Antonine");
    final Emperor antoninus = new Emperor("Antoninus Pius", "Lanuvium", 86, "Antonine");
    final Emperor hadrian = new Emperor("Hadrian", "Santiponce", 76, "Nerva-Trajan");
    final Emperor trajan = new Emperor("Trajan", "Sevilla", 98, "Nerva-Trajan");

    graph.insert(marcus, lucius, antoninus, hadrian, trajan);
    graph.link(marcus, lucius);
    graph.link(lucius, trajan, marcus, marcus);
    graph.link(antoninus, marcus, lucius);
    graph.link(hadrian, trajan);
    graph.link(trajan, lucius, marcus);

    System.out.println(graph);

    final Cursor<Emperor> res = graph.select(emp -> emp.getCity().equals("Rome"),
        emp -> emp.getDynasty().equals("Antonine")).linked(trajan);
        
    for (final Vertex<Emperor> vertex : res.getVertices()) {
      System.out.println(vertex.getData());
    }
  }
}
