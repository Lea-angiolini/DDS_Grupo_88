package ObjetosDB;

public class Condimentos extends AlimDeReceta{
	private int idCondimento;
	private String condimento;
	
	public Condimentos(int id, String cond) {
		// TODO Auto-generated constructor stub
		idCondimento = id;
		condimento = cond;
	}

	public int getIdCondimento() {
		return idCondimento;
	}

	public void setIdCondimento(int idCondimento) {
		this.idCondimento = idCondimento;
	}

	public String getCondimento() {
		return condimento;
	}

	public void setCondimento(String condimento) {
		this.condimento = condimento;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return idCondimento;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return condimento;
	}

	@Override
	public boolean visit(CondicionesPreexistentes cond) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
