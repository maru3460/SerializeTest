package logioDI.diContainer;

import java.util.Optional;

import logioBL.adminAccess.AdminAccess;
import logioBL.manageAdmin.ManageAdmin;
import logioBL.manageAdmin.ManageAdminFactory;
import logioBL.manageUser.ManageUser;
import logioBL.manageUser.ManageUserFactory;
import logioBL.user.User;
import logioBL.userAccess.UserAccess;
import logioDA.adminAccess.AdminAccessFactory;
import logioDA.userAccess.UserAccessFactory;

public class Container {
	private final boolean needInit;
	
	public Container() throws Exception{
		needInit = initDir();
	}
	
	private boolean initDir() throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		return userAccess.initDir();
	}
	
	public boolean needAdmin() {
		return needInit;
	}
	
	public void createAdmin(String name, String password) throws Exception{
		AdminAccessFactory.createAdmin(name, password);
	}
	
	@SuppressWarnings("exports")
	public Optional<ManageAdmin> adminLogin(String name, String password) throws Exception{
		Optional<AdminAccess> tmpAdminAccess = AdminAccessFactory.makeAdminAccess(name, password);
		if(tmpAdminAccess.isEmpty()) {
			return Optional.empty();
		}else {
			return ManageAdminFactory.makeManageAdmin(name, password, tmpAdminAccess.get());
		}
	}
	
	@SuppressWarnings("exports")
	public Optional<ManageUser> login(String name) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		if(!userAccess.searchUser(name)) {
			return Optional.empty();
		}
		
		return ManageUserFactory.makeManageUser(name, userAccess);
	}
	
	@SuppressWarnings("exports")
	public void logout(ManageUser manageUser) throws Exception{
		User user = manageUser.getUser();
		UserAccess userAccess = manageUser.getUserAccess();
		String bfName = manageUser.getBfName();
		
		if(bfName.equals(manageUser.getName())) {
			userAccess.writeUser(user);
		}else {
			if(userAccess.deleteUser(bfName)) {
				createUser(manageUser.getName(), manageUser.getComments());
			}else {
				throw new Exception("元のユーザーファイルが存在しません。");
			}
		}
	}
	
	public boolean searchUser(String name) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		return userAccess.searchUser(name);
	}
	
	public void createUser(String name, String comments) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		userAccess.writeUser(new User(name, comments));
		userAccess.addUser(name);
	}
}
