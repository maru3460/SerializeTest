package bl;

import java.util.Optional;

public interface ManageUser {
	
	public String getName();
	public Optional<String> setName(String name) throws Exception;
	
	public String getComments();
	public void setComments(String comments);
	public void addComments(String comments);
	public void deleteComments();
	
	public User getUser();
	
	public UserAccess getUserAccess();
	
	public String getBfName();
}
