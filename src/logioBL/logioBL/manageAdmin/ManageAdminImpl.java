package logioBL.manageAdmin;

import java.util.List;

import logioBL.adminAccess.AdminAccess;

/**
 * 管理者アカウントでの操作を行うクラスです。
 */
class ManageAdminImpl implements ManageAdmin{
	
	/** 管理者名 */
	private String adminName;
	
	/** データアクセス用クラス */
	private AdminAccess adminAccess;
	
	/**
	 * クラスManageAdminImplのコンストラクタです。
	 * 
	 * @param adminName  管理者名
	 * @param adminAccess  データアクセス用クラス
	 */
	ManageAdminImpl(String adminName, AdminAccess adminAccess){
		this.adminName = adminName;
		this.adminAccess = adminAccess;
	}
	
	/**
	 * フィールドadminNameのgetterです。
	 * 
	 * @return 管理者名
	 */
	public String getAdminName() {
		return adminName;
	}
	
	/**
	 * 全ての一般ユーザーの名前を取得するクラスです。
	 * 
	 * @return 一般ユーザーの名前のリスト
	 * @throws Exception 例外
	 */
	public List<String> getAllUserName() throws Exception{
		return adminAccess.getAllUserName();
	}
}
