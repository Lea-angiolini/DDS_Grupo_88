package ObjetosDB;

public class itemsABuscar {
	
	public itemsABuscar() {
		calificacion = 0;
		grupoAlimenticio = new GruposAlimenticios(-1, "");
		ingredientePrincipal = new Ingredientes(-1, "", 0, 0);
		temporada = new Temporadas(-1, "");
		dificultad = new Dificultades(-1, "");
		caloriasMax = 3000;
		
	}
	private int caloriasMin;
	private int caloriasMax;
	
	private GruposAlimenticios grupoAlimenticio;
	private Ingredientes ingredientePrincipal;
	private Temporadas temporada;
	private Dificultades dificultad;
	
	private int calificacion;
	
	
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public int getCaloriasMin() {
		return caloriasMin;
	}
	public void setCaloriasMin(int caloriasMin) {
		this.caloriasMin = caloriasMin;
	}
	public int getCaloriasMax() {
		return caloriasMax;
	}
	public void setCaloriasMax(int caloriasMax) {
		this.caloriasMax = caloriasMax;
	}
	public GruposAlimenticios getGrupoAlimenticio() {
		return grupoAlimenticio;
	}
	public void setGrupoAlimenticio(GruposAlimenticios grupoAlimenticio) {
		this.grupoAlimenticio = grupoAlimenticio;
	}
	public Ingredientes getIngredientePrincipal() {
		return ingredientePrincipal;
	}
	public void setIngredientePrincipal(Ingredientes ingredientePrincipal) {
		this.ingredientePrincipal = ingredientePrincipal;
	}
	public Temporadas getTemporada() {
		return temporada;
	}
	public void setTemporada(Temporadas temporada) {
		this.temporada = temporada;
	}
	public Dificultades getDificultad() {
		return dificultad;
	}
	public void setDificultad(Dificultades dificultad) {
		this.dificultad = dificultad;
	}


}
