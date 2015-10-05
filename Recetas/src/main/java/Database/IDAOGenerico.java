package Database;

import java.io.Serializable;
import java.util.List;

public interface IDAOGenerico<T,ID extends Serializable> {
	
	
	T create() throws DBExeption;
	void saveOrUpdate(T entity) throws DBExeption;
	T get(ID id) throws DBExeption;
	void delete(ID id) throws DBExeption;
	List<T> findAll() throws DBExeption;

}
