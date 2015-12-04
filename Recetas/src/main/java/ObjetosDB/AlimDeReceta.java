package ObjetosDB;

import java.io.Serializable;


public abstract class AlimDeReceta implements Visitable,Serializable{

	private static final long serialVersionUID = 7963942566772976073L;
	public abstract int getId();
	public abstract String getName();
}
