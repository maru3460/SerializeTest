package logioBL.adminAccess;

import java.util.List;

/**
 * 管理者アカウントへのアクセスを行うクラスで実装するインターフェース。
 */
public interface AdminAccess {
	public boolean isPrepared() throws Exception;
	
	public boolean checkAdmin(String name, String password) throws Exception;
	
	public void createAdmin(String name, String password) throws Exception;
	
	public List<String> getAllUserName() throws Exception;
}
