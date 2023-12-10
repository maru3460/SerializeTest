package di;

import java.util.Optional;

import bl.ManageUser;
import bl.ManageUserFactory;
import bl.User;
import bl.UserAccess;
import da.UserAccessFactory;

public class ManageUserContainer {
	public static void initDir() throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		userAccess.initDir();
	}
	
	public static Optional<ManageUser> login(String name) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		if(!userAccess.searchUser(name)) {
			return Optional.empty();
		}
		
		return ManageUserFactory.makeManageUser(name, userAccess);
	}
	
	public static void logout(ManageUser manageUser) throws Exception{
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
	
	public static boolean searchUser(String name) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		return userAccess.searchUser(name);
	}
	
	public static void createUser(String name, String comments) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		userAccess.writeUser(new User(name, comments));
		userAccess.addUser(name);
	}
}
