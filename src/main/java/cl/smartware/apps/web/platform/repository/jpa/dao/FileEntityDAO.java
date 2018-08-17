package cl.smartware.apps.web.platform.repository.jpa.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;

@Repository
public class FileEntityDAO extends EntityDAO<FileEntity, Integer> {

	/**
	 * 
	 * @param fieldValues map of field and values for restrictions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileEntity> find(Map<String, Object> fieldValues) {

		StringBuilder jpql = new StringBuilder("SELECT f ") 
					.append("FROM ")
					.append(FileEntity.class.getName())
					.append(" f ");
		
		if(fieldValues != null)
		{
			jpql.append("WHERE 1 = 1 ");
						
			fieldValues.forEach((field, value) -> {
				jpql.append("and ")
					.append("f.").append(field)
					.append("= ")
					.append(":").append(field)
					.append(" ");
			});
		}
		
		Query query = entityManager.createQuery(jpql.toString());
		
		if(fieldValues != null)
		{
			fieldValues.forEach((field, value) -> {
				query.setParameter(field, value);
			});
		}
		
		return (List<FileEntity>) query.getResultList();
	}
}
