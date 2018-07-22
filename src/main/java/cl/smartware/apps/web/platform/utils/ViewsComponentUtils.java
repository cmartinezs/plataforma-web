package cl.smartware.apps.web.platform.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ViewsComponentUtils
{
	@Value("${app.folder.theme}")
	private String folderTheme;
	
	@Value("${app.folder.admin}")
	private String folderAdmin;
	
	/**
	 * 
	 * @param view
	 * @return
	 */
	public String addThemeFolderToView(String view)
	{
		return addThemeFolderToView(view, false);
	}
	
	/**
	 * 
	 * @param view
	 * @param isAdmin
	 * @return
	 */
	public String addThemeFolderToView(String view, boolean isAdmin)
	{
		return folderTheme + "/" + addAdminFolderToView(view, isAdmin);
	}
	
	/**
	 * 
	 * @param view
	 * @param isAdmin
	 * @return
	 */
	public String addAdminFolderToView(String view, boolean isAdmin)
	{
		return (isAdmin ? folderAdmin + "/" : "" ) + view;
	}
}
