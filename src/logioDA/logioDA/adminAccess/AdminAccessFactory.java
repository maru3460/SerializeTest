package logioDA.adminAccess;

import java.util.Optional;

import logioBL.adminAccess.AdminAccess;

public class AdminAccessFactory {
	@SuppressWarnings("exports")
	public static Optional<AdminAccess> makeAdminAccess(String name, String password) throws Exception{
		AdminAccess adminAccess = new AdminAccessImpl();
		if(!adminAccess.checkAdmin(name, password)){
			return Optional.empty();
		}else {
			return Optional.of(adminAccess);
		}
	}
	
	public static void createAdmin(String name, String password)throws Exception{
		AdminAccess adminAccess = new AdminAccessImpl();
		if(!adminAccess.isPrepared()) {
			adminAccess.createAdmin(name, password);
		}
	}
}
