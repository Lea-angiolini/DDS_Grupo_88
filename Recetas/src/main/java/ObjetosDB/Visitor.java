package ObjetosDB;

public interface Visitor {
	boolean visitarIngrediente(Ingredientes cond);
	boolean visitarCondimento(Condimentos cond);
}
