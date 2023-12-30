package logioDA.userAccess;

import logioBL.userAccess.UserAccess;

public class UserAccessFactory {
	@SuppressWarnings("exports")
	public static UserAccess makeUserAccess(){
		return new UserAccessImpl();
	}
}
