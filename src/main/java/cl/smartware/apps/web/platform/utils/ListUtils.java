package cl.smartware.apps.web.platform.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListUtils 
{
	public static <T> List<T> setToList(Set<T> set)
	{
		List<T> columnNames = new ArrayList<>();
		
		set.forEach(key -> {
			columnNames.add(key);
		});
		
		return columnNames;
	}
}
