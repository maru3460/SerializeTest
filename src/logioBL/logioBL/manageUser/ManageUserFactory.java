package logioBL.manageUser;

import logioBL.user.User;
import logioBL.userAccess.UserAccess;

/**
 * ManageUserImplのファクトリクラスです。
 */
public class ManageUserFactory {
	
	/**
	 * ManageUserImplのファクトリメソッドです。
	 * 
	 * @param name  ユーザー名
	 * @param userAccess  データアクセス用クラス
	 * @return  ManageUserの実現クラス
	 * @throws Exception  例外
	 */
	public static ManageUser makeManageUser(String name, UserAccess userAccess)throws Exception{
		
		User user = userAccess.readUser(name);
		
		ManageUserImpl manageUser = new ManageUserImpl(user, userAccess);
		
		return manageUser;
	}
}
