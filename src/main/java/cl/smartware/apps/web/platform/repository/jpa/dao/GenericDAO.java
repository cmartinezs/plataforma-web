package cl.smartware.apps.web.platform.repository.jpa.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<E extends Serializable, K>
{
	void create(E entity);
	
	void update(E entity);

	void delete(E entity);

	E findById(K id);

	public List<E> findAll();
}
