package logioDA.adminAccess;

import java.util.Optional;

import logioBL.adminAccess.AdminAccess;

/**
 * AdminAccessImplのファクトリクラスです。
 */
public class AdminAccessFactory {
	
	/**
	 * AdminAccessImplのファクトリメソッド。
	 * 
	 * @param name  名前
	 * @param password  パスワード
	 * @return 入力された名前とパスワードが管理者のものと一致していればOptionalに入ったAdminAccessImpl, 一致しなければ空のOptionalを返します。
	 * @throws Exception  例外
	 */
	@SuppressWarnings("exports")
	public static Optional<AdminAccess> makeAdminAccess(String name, String password) throws Exception{
		AdminAccess adminAccess = new AdminAccessImpl();
		if(!adminAccess.checkAdmin(name, password)){
			return Optional.empty();
		}else {
			return Optional.of(adminAccess);
		}
	}
	
	/**
	 * 管理者を作成するメソッド。
	 * 
	 * 管理者が未作成であれば作成します。
	 * 
	 * @param name 名前
	 * @param password パスワード
	 * @throws Exception 例外
	 */
	public static void createAdmin(String name, String password)throws Exception{
		AdminAccess adminAccess = new AdminAccessImpl();
		if(!adminAccess.isPrepared()) {
			adminAccess.createAdmin(name, password);
		}
	}
}
