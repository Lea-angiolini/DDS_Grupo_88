package ObjetosDB;

public class CondicionesPreexistentes implements Visitable{
	
	int idCondPreex;
	String condPreex;
	
	public CondicionesPreexistentes(int idCondPreex, String condPreex) {
		super();
		this.idCondPreex = idCondPreex;
		this.condPreex = condPreex;
	}
	
	public int getIdCondPreex() {
		return idCondPreex;
	}
	public void setIdCondPreex(int idCondPreex) {
		this.idCondPreex = idCondPreex;
	}
	public String getCondPreex() {
		return condPreex;
	}
	public void setCondPreex(String condPreex) {
		this.condPreex = condPreex;
	}

	@Override
	public boolean accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
}
