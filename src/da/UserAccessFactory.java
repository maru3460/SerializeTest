package da;

import bl.UserAccess;

public class UserAccessFactory {
	public static UserAccess makeUserAccess(){
		return new UserAccessImpl();
	}
}
