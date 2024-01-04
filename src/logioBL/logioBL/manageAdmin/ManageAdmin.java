package logioBL.manageAdmin;

import java.util.List;

/**
 * 管理者アカウントでの操作を行うクラスで実装するインターフェース。
 */
public interface ManageAdmin {
	public String getAdminName();
	
	public List<String> getAllUserName() throws Exception;
}
