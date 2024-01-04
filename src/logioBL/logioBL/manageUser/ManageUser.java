package logioBL.manageUser;

import java.util.Optional;

import logioBL.user.User;
import logioBL.userAccess.UserAccess;

/**
 * 一般アカウントの管理を行うクラスで実装するインターフェース。
 */
public interface ManageUser {
	
	String getName();
	public Optional<String> setName(String name) throws Exception;
	
	public String getComments();
	public void setComments(String comments);
	public void addComments(String comments);
	public void deleteComments();
	
	public User getUser();
	
	public UserAccess getUserAccess();
	
	public String getBfName();
}
