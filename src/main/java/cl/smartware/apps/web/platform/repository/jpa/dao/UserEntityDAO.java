package cl.smartware.apps.web.platform.repository.jpa.dao;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;

@Repository
@Transactional
public class UserEntityDAO extends EntityDAO<UserEntity, Integer>
{
	public UserEntityDAO()
	{
		setEntityClass(UserEntity.class);
	}

	public UserEntity findByUsername(String username)
	{
		String sql = "SELECT u FROM " + UserEntity.class.getName()+ " u WHERE u.username = :username";
		
		Query query = entityManager.createQuery(sql, UserEntity.class);
        query.setParameter("username", username);
        
        return (UserEntity) query.getSingleResult();
	}
}
