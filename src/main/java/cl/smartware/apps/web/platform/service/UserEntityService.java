package cl.smartware.apps.web.platform.service;

import java.util.List;
import java.util.Optional;

import cl.smartware.apps.web.platform.controller.web.form.UserForm;
import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;
import cl.smartware.apps.web.platform.service.exception.RoleNotFoundUserEntityServiceException;
import cl.smartware.apps.web.platform.service.exception.UserNotFoundUserEntityServiceException;

public interface UserEntityService {

	public List<UserEntity> findAll();
	
	public Optional<UserEntity> findById(Integer id);

	public UserForm entityToForm(UserEntity userEntity);

	public UserEntity save(UserForm userForm, UserEntity userEntity) throws RoleNotFoundUserEntityServiceException, UserNotFoundUserEntityServiceException;
}
