package logioBL.manageUser;

import java.util.Optional;

import logioBL.user.User;
import logioBL.userAccess.UserAccess;

/**
 * 一般アカウントの管理を行うクラス。
 */
class ManageUserImpl implements ManageUser{
	
	/** Userクラス */
	private User user;
	
	/** データアクセス用クラス */
	private UserAccess userAccess;
	
	/** ログイン時でのユーザー名 */
	private final String bfName;
	
	/**
	 * クラスManageUserImplのコンストラクタです。
	 * 
	 * ログイン時でのユーザー名とログアウト時のユーザー名が異なる場合は、
	 * 元のユーザーファイルを削除してから新しいファイルを生成してください。
	 * 
	 * @param user Userクラス
	 * @param userAccess  データアクセス用クラス
	 */
	ManageUserImpl(User user, UserAccess userAccess) {
		this.user = user;
		this.userAccess = userAccess;
		bfName = user.getName();
	}
	
	/**
	 * ユーザー名のgetterです。
	 * 
	 * @return ユーザー名
	 */
	public String getName() {
		return user.getName();
	}
	
	/**
	 * ユーザー名のsetterです。
	 * 
	 * @param name セットするユーザー名
	 * @return 	変更できれば空のOptional、変更できなければエラーメッセージを持つOptionalを返します。
	 */
	public Optional<String> setName(String name) throws Exception{
		if(userAccess.searchUser(name) || (name.length() == 0)) {
			String msg = "その名前は使用できません。";
			return Optional.of(msg);
		}
		user.setName(name);
		return Optional.empty();
	}
	
	/**
	 * コメントのgetterです。
	 * 
	 * @return コメント
	 */
	public String getComments() {
		return user.getComments();
	}
	
	/**
	 * コメントのセッターです。
	 * 前のコメントを破棄する場合に使用してください。
	 * 
	 * @param comments  新しいコメント
	 */
	public void setComments(String comments) {
		user.setComments(comments);
	}
	
	/**
	 * コメントの追記をするメソッドです。
	 * 前のコメントに追記したい場合に使用してください。
	 * 
	 * @param comments  追記するコメント
	 */
	public void addComments(String comments) {
		user.setComments(user.getComments() + comments);
	}
	
	/**
	 * コメントを初期化するメソッドです。
	 */
	public void deleteComments() {
		user.setComments("");
	}
	
	/**
	 * Userクラスのgetterです。
	 * 
	 * @return user ユーザー用クラス
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * データアクセス用クラスのgetterです。
	 * 
	 * @return userAccess データアクセス用クラス
	 */
	public UserAccess getUserAccess() {
		return userAccess;
	}
	
	/**
	 * ログイン時でのユーザー名のgetterです。
	 * 
	 * @return bfName  ログイン時でのユーザー名
	 */
	public String getBfName() {
		return bfName;
	}
}
