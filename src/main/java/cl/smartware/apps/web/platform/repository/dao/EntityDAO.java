package cl.smartware.apps.web.platform.repository.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.smartware.apps.web.platform.repository.entity.EntityBase;

public abstract class EntityDAO<E extends EntityBase, K> implements GenericDAO<E, K>
{
	private Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.smartware.digitalpanel.repository.dao.GenericDAO#create(java.io.
	 * Serializable)
	 */
	@Override
	public void create(E entity)
	{
		entityManager.persist(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.smartware.digitalpanel.repository.dao.GenericDAO#delete(java.io.
	 * Serializable)
	 */
	@Override
	public void delete(E entity)
	{
		entityManager.remove(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.smartware.digitalpanel.repository.dao.GenericDAO#findById(java.io.
	 * Serializable)
	 */
	@Override
	public E findById(K id)
	{
		return entityManager.find(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.smartware.digitalpanel.repository.dao.GenericDAO#update(java.io.
	 * Serializable)
	 */
	@Override
	public void update(E entity)
	{
		entityManager.merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.smartware.digitalpanel.repository.dao.GenericDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll()
	{
		return entityManager.createQuery("from " + entityClass.getName()).getResultList();
	}

	protected final void setEntityClass(Class<E> entityClass)
	{
		this.entityClass = entityClass;
	}
}
