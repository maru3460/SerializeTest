package logioBL.manageAdmin;

import java.util.List;

public interface ManageAdmin {
	public String getAdminName();
	
	public List<String> getAllUserName() throws Exception;
}
