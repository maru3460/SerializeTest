package logioDA.userAccess;

import logioBL.userAccess.UserAccess;

/**
 * UserAccessImplのファクトリクラスです。
 */
public class UserAccessFactory {
	
	/**
	 * UserAccessImplのファクトリメソッド。
	 * 
	 * @return UserAccessImpl
	 */
	@SuppressWarnings("exports")
	public static UserAccess makeUserAccess(){
		return new UserAccessImpl();
	}
}
