package logioBL.manageAdmin;

import java.util.Optional;

import logioBL.adminAccess.AdminAccess;

/**
 * ManageAdminImplのファクトリクラスです。
 */
public class ManageAdminFactory {
	
	/**
	 * ManageAdminImplのファクトリメソッドです。
	 * 管理者名、パスワードをチェックし、正確であればManageAdminImplを返します。
	 * 
	 * @param name  管理者名
	 * @param password  管理者のパスワード
	 * @param adminAccess  データアクセス用クラス
	 * @return  正確な管理者名、パスワードであればOptional<ManageAdmin>、間違っていれば空のOptionalを返します。
	 * @throws Exception  例外
	 */
	public static Optional<ManageAdmin> makeManageAdmin(String name, String password, AdminAccess adminAccess)throws Exception{
		if(!adminAccess.checkAdmin(name, password)) {
			return Optional.empty();
		}else {
			return Optional.of(new ManageAdminImpl(name, adminAccess));
		}
	}
}
