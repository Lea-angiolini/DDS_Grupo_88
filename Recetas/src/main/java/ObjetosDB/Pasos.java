package ObjetosDB;

public class Pasos {
	private int numPaso;
	private String descripcionPaso;
	private byte[] imagen;

	public Pasos(int num, String desc) {

		this.numPaso = num;
		this.descripcionPaso = desc;
		this.imagen = null;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
}
