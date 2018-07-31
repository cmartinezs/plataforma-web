package cl.smartware.apps.web.platform.utils;

import java.util.ArrayList;
import java.util.List;

import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity;

public class RoleUserUtils
{
	public static List<String> asANameList(List<RoleUserEntity> roleUsers)
	{
		List<String> aList = new ArrayList<>();

		roleUsers.forEach(roleUser -> {
			aList.add(roleUser.getRole().getName());
		});

		return aList;
	}

}
