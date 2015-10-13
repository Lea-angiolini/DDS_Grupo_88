package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Condiciones")

public class CondicionesPreexistentes implements Visitor,Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCondicion")
	private int idCondPreex;
	
	@NotNull
	@Size(min=1, max=30)
	@Column(name="condicion")
	private String condPreex;
	
	@ManyToMany(cascade={CascadeType.ALL}, mappedBy="condiciones")
	private Set<Usuario> usuarios=new HashSet<Usuario>();
	
	private ArrayList<Integer> ingredientesNoComestible;
	private ArrayList<Integer> condimentosNoComestible;
	
	public CondicionesPreexistentes(int idCondPreex, String condPreex) {
		super();
		this.idCondPreex = idCondPreex;
		this.condPreex = condPreex;
		this.ingredientesNoComestible = new ArrayList<Integer>();
		this.condimentosNoComestible = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getIngredientesNoComestible() {
		return ingredientesNoComestible;
	}

	public void setIngredientesNoComestible(ArrayList<Integer> ingredientesNoComestible) {
		this.ingredientesNoComestible = ingredientesNoComestible;
	}
	
	public void setIngredienteNoComestible(int ingredienteNoComestible) {
		ingredientesNoComestible.add(ingredienteNoComestible);
	}
	
	public ArrayList<Integer> getCondimentosNoComestible() {
		return condimentosNoComestible;
	}

	public void setCondimentosNoComestible(	ArrayList<Integer> condimentosNoComestible) {
		this.condimentosNoComestible = condimentosNoComestible;
	}
	
	public void setCondimentoNoComestible(int condimentoNoComestible) {
		this.condimentosNoComestible.add(condimentoNoComestible);
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
	public boolean visitarIngrediente(Ingredientes ing) {
		// TODO Auto-generated method stub
		for(int ingNocomestible : ingredientesNoComestible){
		if(ingNocomestible == ing.getId())
			return false;
		}
		return true;

	}

	@Override
	public boolean visitarCondimento(Condimentos cond) {
		// TODO Auto-generated method stub
		for(int condNocomestible : condimentosNoComestible){
		if(condNocomestible == cond.getId())
			return false;
		}
		return true;
	}

}
