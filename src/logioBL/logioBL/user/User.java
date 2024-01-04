package logioBL.user;

import java.io.Serializable;

/**
 * ユーザー情報を保持するクラスです。
 */
public class User implements Serializable{
	
	/** ユーザー名 */
	private String name;
	
	/** ユーザーのコメント */
	private String comments;
	
	/**
	 * クラスUserのコンストラクタです。
	 * 
	 * @param name  ユーザー名
	 * @param comments  ユーザーのコメント
	 */
	public User(String name, String comments){
		this.name = name;
		this.comments = comments;
	}
	
	/**
	 * フィールドnameのgetterです。
	 * 
	 * @return  ユーザー名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * フィールドnameのsetterです。
	 * 
	 * @param name  ユーザー名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * フィールドcommentsのgetterです。
	 * 
	 * @return コメント
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * フィールドcommentsのsetterです。
	 * 
	 * @param comments  コメント
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
}
