package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Condiciones")

public class CondicionesPreexistentes implements Visitor,Serializable{
	
	private static final long serialVersionUID = 779689368484044234L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCondicion")
	private int idCondPreex;
	
	@NotNull(message="La condición no debe estar vacía")
	@Size(min=1, max=30,message="La complexión debe contener entre 1 y 30 caracteres")
	@Column(name="condicion")
	private String condPreex;
	
	@ManyToMany(cascade={CascadeType.ALL}, mappedBy="condiciones")
	private Set<Usuario> usuarios=new HashSet<Usuario>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="relcondpreexingnocomestible", joinColumns={@JoinColumn(name="idCond")}, inverseJoinColumns={@JoinColumn(name="idIngNoComestible")})
	private Set<Ingredientes> ingredientesNoComestible;
	
	//private ArrayList<Integer> condimentosNoComestible;
	
	public CondicionesPreexistentes() {
		// TODO Auto-generated constructor stub
	}
	public CondicionesPreexistentes(int idCondPreex, String condPreex) {
		super();
		this.idCondPreex = idCondPreex;
		this.condPreex = condPreex;
		this.ingredientesNoComestible = new HashSet<Ingredientes>();
		//this.condimentosNoComestible = new ArrayList<Integer>();
	}
	
	public Set<Ingredientes> getIngredientesNoComestible() {
		return ingredientesNoComestible;
	}

	public void setIngredientesNoComestible(Set<Ingredientes> ingredientesNoComestible) {
		this.ingredientesNoComestible = ingredientesNoComestible;
	}
	
	public void setIngredienteNoComestible(Ingredientes ingredienteNoComestible) {
		ingredientesNoComestible.add(ingredienteNoComestible);
	}
	
	/*public ArrayList<Integer> getCondimentosNoComestible() {
		return condimentosNoComestible;
	}

	public void setCondimentosNoComestible(	ArrayList<Integer> condimentosNoComestible) {
		this.condimentosNoComestible = condimentosNoComestible;
	}
	
	public void setCondimentoNoComestible(int condimentoNoComestible) {
		this.condimentosNoComestible.add(condimentoNoComestible);
	}*/

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
	public boolean visitarRecera(Receta receta) {
		
		for(Receta_Ingrediente ing : receta.getRelacionIngredientes()){
			if(getIngredientesNoComestible().contains(ing))
				return false;
		}
		return true;
	}

}
