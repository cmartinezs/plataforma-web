package cl.smartware.apps.web.platform.utils;

import java.util.ArrayList;
import java.util.List;

import cl.smartware.apps.web.platform.repository.entity.RoleEntity;

public class RoleUtils
{

	public static List<String> asANameList(List<RoleEntity> roles)
	{
		List<String> aList = new ArrayList<>();

		roles.forEach(role -> {
			aList.add(role.getName());
		});

		return aList;
	}

}
