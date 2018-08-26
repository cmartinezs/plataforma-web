package cl.smartware.apps.web.platform.repository.jpa.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.ManagementTypes;

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
		
		if(fieldValues != null && !fieldValues.isEmpty())
		{
			jpql.append("WHERE 1 = 1 ");
						
			fieldValues.forEach((field, value) -> {
				jpql.append("AND ")
					.append("f.").append(field)
					.append(value instanceof ManagementTypes || value instanceof FileTypes ? " = " : " LIKE ")
					.append(":").append(field)
					.append(" ");
			});
		}
		
		Query query = entityManager.createQuery(jpql.toString());
		
		if(fieldValues != null && !fieldValues.isEmpty())
		{
			fieldValues.forEach((field, value) -> { 
				query.setParameter(
						field
						, value instanceof ManagementTypes 
							|| value instanceof FileTypes ? value : "%" + value + "%"
					); 
				});
		}
		
		return (List<FileEntity>) query.getResultList();
	}
}
