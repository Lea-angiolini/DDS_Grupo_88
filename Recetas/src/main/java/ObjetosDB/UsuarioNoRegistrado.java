package ObjetosDB;

public class UsuarioNoRegistrado extends Usuario {

	private static final long serialVersionUID = 6272009841008946206L;

	@Override
	public boolean puedeComer(Receta receta) {
		return true;
	}
	
	@Override
	public boolean tineCondPreex(CondicionesPreexistentes cond) {
		return false;
	}
}
