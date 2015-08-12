package ObjetosDB;

public class Pasos {
	private int numPaso;
	private String descripcionPaso;

	public Pasos(int num, String desc) {

		this.numPaso = num;
		this.descripcionPaso = desc;
	}

	public int getNumPaso() {
		return numPaso;
	}

	public void setNumPaso(int numPaso) {
		this.numPaso = numPaso;
	}

	public String getDescripcionPaso() {
		return descripcionPaso;
	}

	public void setDescripcionPaso(String descripionPaso) {
		this.descripcionPaso = descripionPaso;
	}
	
	
}
