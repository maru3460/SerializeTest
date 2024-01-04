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

/**
 * 依存性の注入+αを行うクラスです。
 * 
 * インスタンスの作成後、needInitの値を確認し、trueであれば管理者を作成してください。
 */
public class Container {
	
	/** 初期化の必要性 */
	private final boolean needInit;
	
	/**
	 * コンストラクタです。
	 * 
	 * 初期化の必要性を判定し、needInitに格納します。
	 * 
	 * @throws Exception
	 */
	public Container() throws Exception{
		needInit = UserAccessFactory.makeUserAccess().initDir();
	}
	
	/**
	 * フィールドneedInitのgetterです。
	 * 
	 * @return needInit
	 */
	public boolean getNeedInit() {
		return needInit;
	}
	
	/**
	 * 管理者を作成するメソッドです。
	 * 
	 * @param name 名前
	 * @param password パスワード
	 * @throws Exception 例外
	 */
	public void createAdmin(String name, String password) throws Exception{
		AdminAccessFactory.createAdmin(name, password);
	}
	
	/**
	 * 管理者のログインを行うメソッドです。
	 * 
	 * @param name 名前
	 * @param password パスワード
	 * @return ログインできればManageAdminを持ったOptional、出来なければ空のOptionalを返します。
	 * @throws Exception 例外
	 */
	@SuppressWarnings("exports")
	public Optional<ManageAdmin> adminLogin(String name, String password) throws Exception{
		Optional<AdminAccess> tmpAdminAccess = AdminAccessFactory.makeAdminAccess(name, password);
		if(tmpAdminAccess.isEmpty()) {
			return Optional.empty();
		}else {
			return ManageAdminFactory.makeManageAdmin(name, password, tmpAdminAccess.get());
		}
	}
	
	/**
	 * 一般ユーザーのログインを行うメソッドです。
	 * 
	 * @param name 名前
	 * @return ログインできればManageUserを持ったOptional、出来なければ空のOptionalを返します。
	 * @throws Exception 例外
	 */
	@SuppressWarnings("exports")
	public Optional<ManageUser> login(String name) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		if(!userAccess.searchUser(name)) {
			return Optional.empty();
		}
		
		return Optional.of(ManageUserFactory.makeManageUser(name, userAccess));
	}
	
	/**
	 * 一般ユーザーのログアウトを行うメソッドです。
	 * 
	 * ManageUserクラスで処理を行うように変更予定です。
	 * 
	 * @param manageUser 一般ユーザーの管理用クラス
	 * @throws Exception 例外
	 */
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
	
	/**
	 * 名前を受け取り、その名前のユーザーが存在するかどうかを判定するメソッドです。
	 * 
	 * @param name 名前
	 * @return 存在すればtrue、存在しなければfalseを返します。
	 * @throws Exception 例外
	 */
	 public boolean searchUser(String name) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		return userAccess.searchUser(name);
	}
	
	 /**
	  * 新しい一般ユーザーを作成するメソッドです。
	  * 
	  * @param name 名前
	  * @param comments コメント
	  * @throws Exception 例外
	  */
	public void createUser(String name, String comments) throws Exception{
		UserAccess userAccess = UserAccessFactory.makeUserAccess();
		userAccess.writeUser(new User(name, comments));
		userAccess.addUser(name);
	}
}
