package ObjetosDB;

public class Condimentos {
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
	
}
