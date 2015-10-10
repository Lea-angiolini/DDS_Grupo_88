package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Condimento")
public class Condimentos extends AlimDeReceta implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCondimento")
	private int idCondimento;
	
	@NotNull
	@Size(min=1, max=30)
	@Column(name="nombre")
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
	public boolean accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visitarCondimento(this);
	}

	
}
