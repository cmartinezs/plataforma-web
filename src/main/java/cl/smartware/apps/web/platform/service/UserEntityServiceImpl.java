package cl.smartware.apps.web.platform.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import cl.smartware.apps.web.platform.controller.web.form.UserForm;
import cl.smartware.apps.web.platform.repository.jpa.crud.RoleEntityCrudRepository;
import cl.smartware.apps.web.platform.repository.jpa.crud.UserEntityCrudRepository;
import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity.RoleUserId;
import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;
import cl.smartware.apps.web.platform.service.exception.RoleNotFoundUserEntityServiceException;
import cl.smartware.apps.web.platform.service.exception.UserNotFoundUserEntityServiceException;

@Service
public class UserEntityServiceImpl implements UserEntityService 
{
	@Autowired
	private UserEntityCrudRepository userEntityCrudRepository;
	
	@Autowired
	private RoleEntityCrudRepository roleEntityCrudRepository;
	
	@Autowired
	private RoleUserEntityService roleUserEntityService;
	
	@Autowired
	private FilerEntityService filerEntityService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<UserEntity> findAll() 
	{
		List<UserEntity> list = new ArrayList<>();
		userEntityCrudRepository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Optional<UserEntity> findById(Integer id) 
	{
		return userEntityCrudRepository.findById(id);
	}

	@Override
	public UserForm entityToForm(UserEntity userEntity) 
	{
		UserForm userForm = new UserForm();
		userForm.setId(userEntity.getId());
		userForm.setUsername(userEntity.getUsername());
		userForm.setEmail(userEntity.getEmail());
		userForm.setRoleId(userEntity.getRoleUsers().get(0).getRoleUserId().getRoleId());
		userForm.setActive(userEntity.getActive());
		userForm.setCreatedAt(new SimpleDateFormat("dd-MM-yyyy").format(userEntity.getCreatedAt()));
		return userForm;
	}

	@Override
	public UserEntity save(UserForm userForm, UserEntity loggedUserEntity) throws RoleNotFoundUserEntityServiceException, UserNotFoundUserEntityServiceException 
	{
		Integer roleId = userForm.getRoleId();
		Optional<RoleEntity> optionalRoleEntity = roleEntityCrudRepository.findById(roleId);
		
		if(!optionalRoleEntity.isPresent())
		{
			throw new RoleNotFoundUserEntityServiceException(MessageFormat.format("Could not find role with identifier {}", roleId));
		}
		
		Integer userId = userForm.getId();
		UserEntity newUserEntity = null;
		RoleUserEntity newRoleUserEntity = null;
		List<RoleUserEntity> roleUsers = null;
		
		if(!Objects.isNull(userId))
		{
			Optional<UserEntity> optionalUserEntity = userEntityCrudRepository.findById(userId);
			
			if(optionalUserEntity.isPresent()) 
			{
				newUserEntity = optionalUserEntity.get();
				roleUsers = newUserEntity.getRoleUsers();
				
				Optional<RoleUserEntity> optionalRoleUserEntity = 
						roleUsers
							.stream()
							.filter(roleUser -> roleUser.getRoleUserId().getRoleId().equals(roleId))
							.findFirst();
				
				if(optionalRoleUserEntity.isPresent())
				{
					newRoleUserEntity = optionalRoleUserEntity.get();
				}
			}
			else
			{
				throw new UserNotFoundUserEntityServiceException(MessageFormat.format("Could not find user with identifier {}", userId));
			}
		}

		if(Objects.isNull(newUserEntity))
		{
			newUserEntity = new UserEntity();
			newUserEntity.setUsername(userForm.getUsername());
		}
		
		newUserEntity.setActive(userForm.getActive());
		newUserEntity.setEmail(userForm.getEmail());
		
		if(!Objects.isNull(StringUtils.trim(userForm.getPassword()))
				&& !StringUtils.trim(userForm.getPassword()).isEmpty())
		{
			newUserEntity.setPassword(passwordEncoder.encode(userForm.getPassword()));
		}

		newUserEntity = userEntityCrudRepository.save(newUserEntity);
		
		if(Objects.isNull(newRoleUserEntity))
		{
			RoleUserId roleUserId = new RoleUserId();
			roleUserId.setRoleId(optionalRoleEntity.get().getId());
			roleUserId.setUserId(newUserEntity.getId());
			
			newRoleUserEntity = new RoleUserEntity();
			newRoleUserEntity.setRoleUserId(roleUserId);
			
			roleUserEntityService.save(newRoleUserEntity);
			
			if(!Objects.isNull(roleUsers) && !roleUsers.isEmpty())
			{
				roleUsers.forEach( roleUser -> { roleUserEntityService.delete(roleUser); } );
			}
		}
		
		return userEntityCrudRepository.findById(newUserEntity.getId()).get();
	}

	@Override
	public void delete(UserEntity userEntity) 
	{
		List<FileEntity> files = filerEntityService.findByCreatedBy(userEntity);
		
		files.forEach(file -> { file.setCreatedBy(null); });
		
		userEntityCrudRepository.delete(userEntity);
	}
}
