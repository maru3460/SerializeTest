package logioBL.manageAdmin;

import java.util.List;

import logioBL.adminAccess.AdminAccess;

class ManageAdminImpl implements ManageAdmin{
	private String adminName;
	private AdminAccess adminAccess;
	
	ManageAdminImpl(String adminName, AdminAccess adminAccess){
		this.adminName = adminName;
		this.adminAccess = adminAccess;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public List<String> getAllUserName() throws Exception{
		return adminAccess.getAllUserName();
	}
}
