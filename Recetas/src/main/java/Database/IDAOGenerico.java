package Database;

import java.io.Serializable;
import java.util.List;

public interface IDAOGenerico<T,ID extends Serializable> {
	
	
	T create() throws Exception;
	void saveOrUpdate(T entity) throws Exception;
	T get(ID id) throws Exception;
	void delete(ID id) throws Exception;
	List<T> findAll() throws Exception;

}
