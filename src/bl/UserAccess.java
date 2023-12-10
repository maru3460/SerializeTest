package bl;

public interface UserAccess{
	public void initDir() throws Exception;
	
	public boolean searchUser(String name) throws Exception;
	
	public User readUser(String name)throws Exception;
	
	public void writeUser(User user)throws Exception;
	
	public void addUser(String name)throws Exception;
	
	public boolean deleteUser(String name)throws Exception;
}
