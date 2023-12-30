package logioBL.manageAdmin;

import java.util.Optional;

import logioBL.adminAccess.AdminAccess;

public class ManageAdminFactory {
	
	public static Optional<ManageAdmin> makeManageAdmin(String name, String password, AdminAccess adminAccess)throws Exception{
		if(!adminAccess.checkAdmin(name, password)) {
			return Optional.empty();
		}else {
			return Optional.of(new ManageAdminImpl(name, adminAccess));
		}
	}
}
