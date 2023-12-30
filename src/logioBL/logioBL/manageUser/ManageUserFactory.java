package logioBL.manageUser;

import java.util.Optional;

import logioBL.user.User;
import logioBL.userAccess.UserAccess;

public class ManageUserFactory {
	public static Optional<ManageUser> 
							makeManageUser(String name, UserAccess userAccess)throws Exception{
		
		User user = userAccess.readUser(name);
		
		ManageUserImpl manageUser = new ManageUserImpl(user, userAccess);
		
		return Optional.of(manageUser);
	}
}
