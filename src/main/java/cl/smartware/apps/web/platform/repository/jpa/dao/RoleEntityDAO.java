package cl.smartware.apps.web.platform.repository.jpa.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.smartware.apps.web.platform.repository.jpa.entity.RoleEntity;

@Repository
@Transactional
public class RoleEntityDAO extends EntityDAO<RoleEntity, Integer>
{
	public RoleEntityDAO()
	{
		setEntityClass(RoleEntity.class);
	}

	@SuppressWarnings("unchecked")
	public List<String> getRoleNames(Integer userId)
	{
		String sql = "SELECT r.name FROM " + RoleEntity.class.getName() + " r JOIN r.users u where u.id = :userId ";

		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}
